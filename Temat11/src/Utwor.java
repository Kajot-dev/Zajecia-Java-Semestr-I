import java.util.*;

public class Utwor implements Comparable<Utwor> {
    private String tytul;
    private String[] wykonawcy;
    private int rokWydania;
    
    //komparatory
    protected static final Comparator<Utwor> compPoAutorze = Comparator.comparing(Utwor::getWykonawcyJoined)
    .thenComparing(Utwor::getTytul)
    .thenComparing(Utwor::getRokWydania);
    protected static final Comparator<Utwor> compPoTytule = Comparator.comparing(Utwor::getTytul)
    .thenComparing(Utwor::getWykonawcyJoined)
    .thenComparing(Utwor::getRokWydania);
    protected static final Comparator<Utwor> compPoRoku = Comparator.comparing(Utwor::getRokWydania)
    .thenComparing(Utwor::getTytul)
    .thenComparing(Utwor::getWykonawcyJoined);

    public Utwor(String tytul, String[] wykonawcy, int rokWydania) throws IllegalArgumentException {
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

    private String getWykonawcyJoined() {
        return String.join("", this.wykonawcy);
    }

    public int getRokWydania() {
        return rokWydania;
    }

    public String toString() {
        return this.tytul + " (" + String.join(", ", this.wykonawcy) + ") " + "[" + this.rokWydania + "]";
    }

    @Override
    public int compareTo(Utwor u) {
        return Utwor.compPoTytule.compare(this, u);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
  
        if (!(o instanceof Utwor)) return false;
          
        Utwor u = (Utwor) o;
          
        return this.compareTo(u) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.tytul, Objects.hash((Object[]) this.wykonawcy), this.rokWydania);
    }

}
