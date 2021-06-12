import java.util.*;
import java.io.*;


public record Song(String title, String[] authors, int year,
                   File songFile) implements Comparable<Song> {

    protected static final Comparator<Song> compByAuthor = Comparator.comparing(Song::getJoinedAuthors)
            .thenComparing(Song::getTitle)
            .thenComparing(Song::getYear);
    protected static final Comparator<Song> compByTitle = Comparator.comparing(Song::getTitle)
            .thenComparing(Song::getJoinedAuthors)
            .thenComparing(Song::getYear);
    protected static final Comparator<Song> compByYear = Comparator.comparing(Song::getYear)
            .thenComparing(Song::getTitle)
            .thenComparing(Song::getJoinedAuthors);


    public Song(String title, String[] authors, int year, File songFile) {

        title = title.trim();
        if (title.isEmpty()) throw new IllegalArgumentException("Title cannot be empty!");
        if (title.contains(";")) throw new IllegalArgumentException("Title cannot contain semicolon!");
        this.title = title;

        if (authors.length == 0) throw new IllegalArgumentException("Song has to have at least one author!");
        authors = Arrays.stream(authors).map(w -> {
            w = w.trim();
            if (w.isEmpty()) throw new IllegalArgumentException("Empty author names are forbidden!");
            return w;
        }).toArray(String[]::new);
        this.authors = authors;

        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year < 1900 || year > currentYear)
            throw new IllegalArgumentException("Year cannot be smaller than 1900 or come from the future!");
        this.year = year;

        if (songFile == null) throw new IllegalArgumentException("Empty file!");
        if (!songFile.exists()) throw new IllegalArgumentException("File does not exist!");
        this.songFile = songFile;
    }

    public static Song from(String csv) throws IllegalArgumentException {
        String[] data = csv.split(";");
        try {
            int rok = Integer.parseInt(data[2]);
            return new Song(data[0], data[1].split(","), rok, new File(data[3]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Wrong year!");
        }
    }

    public String getTitle() {
        return title;
    }

    public File getFile() {
        return songFile;
    }


    private String getJoinedAuthors() {
        return String.join("", this.authors);
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return this.title + " (" + String.join(", ", this.authors) + ") " + "[" + this.year + "]";
    }

    public String serialise() {
        return this.title + ';' + String.join(",", this.authors) + ';' + this.year + ';' + this.songFile.getAbsolutePath();
    }


    @Override
    public int compareTo(Song u) {
        return Song.compByTitle.compare(this, u);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;

        if (!(o instanceof Song u)) return false;

        return this.compareTo(u) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, Objects.hash((Object[]) this.authors), this.year);
    }

}
