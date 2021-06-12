import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

//Todo: rename all names to EN
//Todo: comments
//Todo: merge multiplied accept methods
//Todo: Playlists should still load when there are SOME missing files

public class MusicPlayer {

    private final HashMap<String, Playlist> playlists = new HashMap<>();
    private final OptionRunner options = new OptionRunner();

    private static final String[] SORT_OPTS = new String[] { "SORT BY TITLE", "SORT BY AUTHOR(S)",
            "SORT BY YEAR" };
    private static final String INTO_PLAYLIST = " into playlist ";
    private static final String FROM_PLAYLIST = " from playlist ";
    private static final String SPECIFY_SOURCE_PLAYLIST = "Specify source playlist";
    private static final String SPECIFY_TARGET_PLAYLIST = "Specify target playlist";
    private static final String NO_PLAYLISTS_TO_LOAD = "There are no playlists available for loading";

    private static final File SAVE_DIR = new File("saved_playlists");


    private Thread currentThread = null;
    private Player currentPlayer = null;
    private File currentFile = null;

    static {
        if (!SAVE_DIR.exists() && !SAVE_DIR.mkdir()) {
            System.err.println("Could not create playlists folder!");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        var player = new MusicPlayer();
        player.enable();
    }

    protected static void errorHandler(Exception e) {
        if (e instanceof NoPlaylistException || e instanceof EmptyPlaylistException || e instanceof UserAbortedException) {
            MusicPlayer.printYellow(e.getMessage());
        } else  {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public MusicPlayer() {
        var self = this;

        this.options.defineOption("Show playlist", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                final var p1 = self.choosePlaylist();
                p1.print();
            }

            @Override
            public boolean accept() {
                return !self.playlists.isEmpty();
            }
        });

        this.options.defineOption("Create playlist", () -> {
            final var name = OptionRunner.askString("New playlist's name");
            if (this.playlists.containsKey(name)) {
                MusicPlayer.printYellow("Playlist " + name + " already exists!");
            } else {
                this.playlists.put(name, new Playlist(name));
                MusicPlayer.printGreen("Creating " + name + " playlist was successful!");
            }

        });

        this.options.defineOption("Delete playlist", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                var p1 = self.choosePlaylist();

                final String name = p1.getName();

                self.playlists.remove(name);
                MusicPlayer.printGreen("Playlist " + name + "was successfully deleted!");
            }

            @Override
            public boolean accept() {
                return !self.playlists.isEmpty();
            }
        });

        this.options.defineOption("Add a song", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                var p1 = self.choosePlaylist();

                final var title = OptionRunner.askString("Title");
                final String[] authors = OptionRunner.askAuthors();
                final var year = OptionRunner.askInt(0, 10000, "Year");
                final var musicFile = OptionRunner.askMusicFile();

                try {
                    var song = new Song(title, authors, year, musicFile);

                    p1.addSong(song);
                    MusicPlayer.printGreen("Added song " + song + INTO_PLAYLIST + p1.getName());
                } catch (IllegalArgumentException e) {

                    MusicPlayer.printYellow(e.getMessage());
                }
            }

            @Override
            public boolean accept() {
                return !self.playlists.isEmpty();
            }
        });

        this.options.defineOption("Transfer a song", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                System.out.println(SPECIFY_SOURCE_PLAYLIST);
                var p1 = self.choosePlaylist();

                int songNumber = p1.chooseSongIndex();
                var song = p1.getSong(songNumber);

                System.out.println(SPECIFY_TARGET_PLAYLIST);
                var p2 = self.choosePlaylist(p1);

                p1.removeSong(songNumber);
                p2.addSong(song);
                MusicPlayer.printGreen("Transferred song " + song.toString() + FROM_PLAYLIST + p1.getName() + INTO_PLAYLIST
                        + p2.getName() + "!");
            }

            @Override
            public boolean accept() {
                return self.playlists.size() >= 2 && self.playlists.values().stream().anyMatch(Playlist::hasSongs);
            }
        });

        this.options.defineOption("Copy a song", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                System.out.println(SPECIFY_SOURCE_PLAYLIST);
                var p1 = self.choosePlaylist();

                int songNumber = p1.chooseSongIndex();
                final var song = p1.getSong(songNumber);

                System.out.println(SPECIFY_TARGET_PLAYLIST);
                var p2 = self.choosePlaylist(p1);

                p2.addSong(song);
                MusicPlayer.printGreen("Copied song" + song.toString() + FROM_PLAYLIST + p1.getName() + INTO_PLAYLIST
                        + p2.getName() + "!");
            }

            @Override
            public boolean accept() {
                return self.playlists.size() >= 2 && self.playlists.values().stream().anyMatch(Playlist::hasSongs);
            }
        });

        this.options.defineOption("Delete a song", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                var p1 = self.choosePlaylist();

                int songNumber = p1.chooseSongIndex();
                var song = p1.getSong(songNumber);

                p1.removeSong(songNumber);
                MusicPlayer.printGreen("Deleted song " + song.toString());
            }

            @Override
            public boolean accept() {
                return !self.playlists.isEmpty() && self.playlists.values().stream().anyMatch(Playlist::hasSongs);
            }
        });

        this.options.defineOption("Sort a playlist", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                Playlist[] wrongPlaylists = self.playlists.values().stream().filter(p -> p.size() < 2).toArray(Playlist[]::new);
                var p1 = self.choosePlaylist(wrongPlaylists);

                System.out.println("Choose option:");
                OptionRunner.printOptions(MusicPlayer.SORT_OPTS);

                var choice = OptionRunner.askInt(MusicPlayer.SORT_OPTS.length + 1);


                //Todo: ascending and descending
                switch (choice) {
                    case 1:
                        p1.sortByTitle();
                        break;
                    case 2:
                        p1.sortByAuthors();
                        break;
                    case 3:
                        p1.sortByYear();
                        break;
                    case 4:
                        throw new UserAbortedException();
                    default:
                        break;
                }
                MusicPlayer.printGreen("Sorted " + p1.getName() + " playlist!");
            }

            @Override
            public boolean accept() {
                return !self.playlists.isEmpty() && self.playlists.values().stream().anyMatch(p -> p.size() > 1);
            }
        });

        this.options.defineOption("Save playlist to a file", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                var p1 = self.choosePlaylist();
                var songFile = new File(SAVE_DIR , p1.getName()+".csv");
                if (!songFile.exists() || OptionRunner.askBoolean("Do you want to proceed? Save playlist with the same name already exists!")) {
                    try (
                            final var writer = new FileWriter(songFile);
                            final var buff = new BufferedWriter(writer)
                    ) {
                        p1.serialise(buff);
                        MusicPlayer.printGreen("Saving playlist " + p1.getName() + " was successful!");
                    } catch (IOException e) {
                        MusicPlayer.printYellow("Error saving playlist!");
                    }
                } else {
                    throw new UserAbortedException();
                }
            }

            @Override
            public boolean accept() {
                File[] acceptableFiles = SAVE_DIR.listFiles(f -> !f.isDirectory() && f.getName().toUpperCase().endsWith(".CSV"));
                return acceptableFiles != null && acceptableFiles.length > 0;
            }
        });

        this.options.defineOption("Load playlist from a file", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                File[] acceptableFiles = SAVE_DIR.listFiles(f -> !f.isDirectory() && f.getName().toUpperCase().endsWith(".CSV"));
                if (acceptableFiles == null || acceptableFiles.length == 0) {
                    throw new NoPlaylistException(NO_PLAYLISTS_TO_LOAD);
                }
                final String[] fileNames = Arrays.stream(acceptableFiles).map(f -> f.getName().substring(0, f.getName().length() - 4)).toArray(String[]::new);
                System.out.println(SPECIFY_SOURCE_PLAYLIST);
                OptionRunner.printOptions(fileNames);
                final var choice = OptionRunner.askInt(fileNames.length + 1);
                if (choice == fileNames.length + 1) throw new UserAbortedException();
                if (!self.playlists.containsKey(fileNames[choice - 1]) || OptionRunner.askBoolean("Playlist with this name already exists. Do you want to overwrite it?")) {
                    try {
                        var p = new Playlist(fileNames[choice - 1], acceptableFiles[choice - 1]);
                        self.playlists.put(fileNames[choice - 1], p);
                        MusicPlayer.printGreen("Loaded playlist " + p.getName() + '!');
                    } catch (IOException e) {
                        throw new NoPlaylistException("Corrupted playlist!");
                    }
                } else throw new UserAbortedException();
            }

            @Override
            public boolean accept() {
                File[] acceptableFiles = SAVE_DIR.listFiles(f -> !f.isDirectory() && f.getName().toUpperCase().endsWith(".CSV"));
                return acceptableFiles != null && acceptableFiles.length > 0;
            }
        });

        this.options.defineOption("Delete playlist file", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                File[] acceptableFiles = SAVE_DIR.listFiles(f -> !f.isDirectory() && f.getName().toUpperCase().endsWith(".CSV"));
                if (acceptableFiles == null || acceptableFiles .length == 0) {
                    throw new NoPlaylistException("There are no playlists available to delete!");
                }
                final String[] fileNames = Arrays.stream(acceptableFiles).map(f -> f.getName().substring(0, f.getName().length() - 4)).toArray(String[]::new);
                System.out.println(SPECIFY_TARGET_PLAYLIST);
                OptionRunner.printOptions(fileNames);
                final var choice = OptionRunner.askInt(fileNames.length + 1);
                if (choice == fileNames.length + 1) throw new UserAbortedException();
                try {
                    Files.delete(acceptableFiles[choice - 1].toPath());
                    MusicPlayer.printGreen("File deleted successfully");
                } catch (IOException e) {
                    MusicPlayer.printYellow("Error deleting a file!");
                }
            }

            @Override
            public boolean accept() {
                File[] acceptableFiles = SAVE_DIR.listFiles(f -> !f.isDirectory() && f.getName().toUpperCase().endsWith(".CSV"));
                return acceptableFiles != null && acceptableFiles.length > 0;
            }
        });

        this.options.defineOption("Play a song", new Option() {
            @Override
            public void run() throws NoPlaylistException, EmptyPlaylistException, UserAbortedException {
                var p1 = self.choosePlaylist();
                int i = p1.chooseSongIndex();
                var u = p1.getSong(i);
                self.play(u);
            }

            @Override
            public boolean accept() {
                return !self.playlists.isEmpty() && self.playlists.values().stream().anyMatch(Playlist::hasSongs);
            }
        });
    }

    public void enable() {
        this.options.cyclingMenu();
    }

    //Utils


    private void play(Song u) {
        if (!u.getFile().exists()) {
            MusicPlayer.printYellow("File does not exist!");
        }
        if (this.currentPlayer != null) {
            this.currentPlayer.close();
            if (this.currentThread != null) {
                this.currentThread = null;
            }
        }
        this.currentFile = u.getFile();
        this.currentThread = new Thread(this::playTarget);
        this.currentThread.start();
        MusicPlayer.printGreen("Playing: " + u);
    }

    private void playTarget() {
        try (
            final var buff = new BufferedInputStream(new FileInputStream(this.currentFile))
        ) {
            this.currentPlayer = new Player(buff);
            this.currentPlayer.play();
        } catch (JavaLayerException|IOException e) {
            MusicPlayer.printYellow("Error playing!");
        }
    }
    


    private Playlist choosePlaylist(Playlist... exceptions) throws NoPlaylistException, UserAbortedException {
        List<Playlist> opts = new ArrayList<>(this.playlists.values());
        for (Playlist p : exceptions)
            opts.remove(p);
        if (opts.isEmpty())
            throw new NoPlaylistException(); 
        String[] nameArr = opts.stream().map(Playlist::getName).toArray(String[]::new);
        System.out.println("Choose playlist:");
        OptionRunner.printOptions(nameArr);
        final var c = OptionRunner.askInt(nameArr.length + 1);
        if (c == nameArr.length + 1) throw new UserAbortedException();
        return this.playlists.get(nameArr[c - 1]);
    }

    protected static void printYellow(Object o) {
        System.out.println("\u001B[33m" + o + "\u001B[0m");
    }

    protected static void printGreen(Object o) {
        System.out.println("\u001B[32m" + o + "\u001B[0m");
    }
}
