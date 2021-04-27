public class Utwor implements Comparable<Utwor> {
    private String tytul;
    private String[] wykonawcy;
    private int rokWydania;

    public Utwor(String tytul, String[] wykonawcy, int rokWydania) {
        this.tytul = tytul;
        this.wykonawcy = wykonawcy;
        if (rokWydania < 0) throw new IllegalArgumentException("Rok wydania nie moze byc ujemny!");
        this.rokWydania = rokWydania;
    }

    public Utwor(String tytul, String wykonawca, int rokWydania) {
        this(tytul, new String[] { wykonawca }, rokWydania);
    }

    public String getTytul() {
        return tytul;
    }

    public String[] getWykonawcy() {
        return wykonawcy;
    }

    public int getRokWydania() {
        return rokWydania;
    }

    public String toString() {
        return this.tytul + " (" + String.join(", ", this.wykonawcy) + ") " + "[" + this.rokWydania + "]";
    }

    public int compareTo(Utwor u) {
        return this.getTytul().compareTo(u.getTytul());
    }

}
