import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javazoom.jl.player.Player;

public class Utwor implements Comparable<Utwor> {

    private String tytul;
    private String[] wykonawcy;
    private int rokWydania;
    private File source;
    private Player odtwarzacz;
    private BufferedInputStream buffStream;
    private static Utwor odtwarzany = null;
    
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

    public Utwor(String tytul, String[] wykonawcy, int rokWydania, File plik) throws IllegalArgumentException {

        //sprawdzam tytul
        tytul = tytul.trim();
        if (tytul.isEmpty()) throw new IllegalArgumentException("Tytul nie moze byc pusty!");
        if (tytul.contains(";")) throw new IllegalArgumentException("Tytul nie moze zawierac srednika!");
        this.tytul = tytul;

        //sprawdzam wszystkich wykonawcow
        if (wykonawcy.length == 0) throw new IllegalArgumentException("Utwor musi miec co najmniej jednego wykonawce!");
        Arrays.asList(wykonawcy).stream().map(w -> {
            w = w.trim();
            if (w.isEmpty()) throw new IllegalArgumentException("Zaden wykonawca nie moze byc pusty!");
            return w;
        }).collect(Collectors.toList()).toArray(wykonawcy);
        this.wykonawcy = wykonawcy;

        //sprawdzam rok
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (rokWydania < 1900 || rokWydania > currentYear) throw new IllegalArgumentException("Rok wydania nie moze byc mniejszy niz 1900, ani nie moze pochodzic z przyszlosci!");
        this.rokWydania = rokWydania;

        this.source = plik;
        
    }

    public Utwor(String tytul, String wykonawca, int rokWydania, File plik) {
        this(tytul, new String[] { wykonawca }, rokWydania, plik);
    }
    public Utwor(String tytul, String[] wykonawcy, int rokWydania, String plik) {
        this(tytul, wykonawcy, rokWydania, new File(plik));
    }

    public static Utwor from(String csv) throws IllegalArgumentException {
        String[] data = csv.split(";");
        try {
            int rok = Integer.parseInt(data[2]);
            return new Utwor(data[0], data[1].split(","), rok, data[3]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Nieprawidlowy rok wydania!");
        }
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

    public void odtwarzaj() {
        if (Utwor.odtwarzany == this) return;
        Utwor.anulujOdtwarzanie();
        try {
            this.buffStream = new BufferedInputStream(new FileInputStream(this.source));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Podany plik nie istnieje!");
        }
    }

    public static void anulujOdtwarzanie() {
        if (Utwor.odtwarzany != null) {
            try {
                Utwor.odtwarzany.odtwarzacz.close();
                Utwor.odtwarzany.buffStream.close();
            } catch (IOException e) {
                //buffStream is already closed
            }
            
        }
    }

    @Override
    protected void finalize() {
        try {
            this.odtwarzacz.close();
            this.buffStream.close();
        } catch (IOException e) {
            //do nothing
        }
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
