import java.io.*;
import java.util.*;

public class Playlist {
    private final String name;
    private final List<Song> songs = new LinkedList<>();
    private static final String SONG_LIST = "Song list:";

    public Playlist(String name, File f) throws IOException {
        this.name = name;
        //we assume that file is correct
        try (
            final var sc = new Scanner(f)
        ) {
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                if (s.isBlank()) continue;
                this.addSong(Song.from(s));
            }
        } catch (IllegalArgumentException e) {
            throw new IOException();
        }
    }

    public Playlist(String name) {
        this.name = name;
    }

    public void addSong(Song... songIterator) {
        for (Song u : songIterator) {
            if (u == null) throw new NullPointerException("Song cannot be empty object!");
            if (this.songs.contains(u)) System.err.println("This song is already on this playlist!");
            else this.songs.add(u);
        }
    }

    public Song getSong(int index) {
        return this.songs.get(index);
    }

    public String[] getSongList() {
        return this.songs.stream().map(Song::toString).toArray(String[]::new);
    }

    protected int chooseSongIndex() throws UserAbortedException, EmptyPlaylistException {
        if (this.songs.isEmpty()) throw new EmptyPlaylistException();
        System.out.println(SONG_LIST);
        OptionRunner.printOptions(this.getSongList());
        var out = OptionRunner.askInt(this.songs.size() + 1);
        if (out == this.songs.size() + 1) throw new UserAbortedException();
        return out - 1;
    }

    public void removeSong(int index) {
        this.songs.remove(index);
    }

    public void sortByTitle(boolean reverse) {
        var comp = Song.compByTitle;
        if (reverse) comp = comp.reversed();
        this.songs.sort(comp);
    }

    public void sortByAuthors(boolean reverse) {
        var comp = Song.compByAuthor;
        if (reverse) comp = comp.reversed();
        this.songs.sort(comp);
    }

    public void sortByYear(boolean reverse) {
        var comp = Song.compByYear;
        if (reverse) comp = comp.reversed();
        this.songs.sort(comp);
    }

    public int size() {
        return this.songs.size();
    }

    public void print() {
        System.out.print(this);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        var sb = new StringBuilder();
        sb.append("Playlist \"").append(this.name).append("\":\n");
        if (this.songs.isEmpty()) {
            sb.append("Playlist is empty!\n");
            return sb.toString();
        }
        int nLen = getStrLen(this.songs.size());
        if (nLen < 3) nLen = 3;
        final var formatString = "%" + nLen + "s. %s\n";
        var fm = new Formatter(sb);
        fm.format(formatString, "N.", "Name");
        for (var i = 1; i <= this.songs.size(); i++) {
            fm.format(formatString, i, this.songs.get(i-1));
        }
        fm.close();
        return sb.toString();
    }

    protected void serialise(Writer w) throws IOException {
        for (Song u : this.songs) {
            w.write(u.serialise() + '\n');
        }
    }

    public boolean hasSongs() {
        return !this.songs.isEmpty();
    }

    private static int getStrLen(int x) {
        var sum = 0;
        while (x > 0) {
            sum++;
            x /= 10;
        }
        return sum;
    }
}
