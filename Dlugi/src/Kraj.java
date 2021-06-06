import java.util.Comparator;

public class Kraj {
    private String nazwa;
    private long dlug;
    private long ludnosc;

    public static final Comparator<Kraj> comp = Comparator.comparing(Kraj::getLudnosc).thenComparing(Kraj::getDlug).thenComparing(Kraj::getNazwa);
    
    public Kraj(String nazwa, long dlug, long ludnosc) {
        this.nazwa = nazwa;
        this.dlug = dlug;
        this.ludnosc = ludnosc;
    }

    public String getNazwa() {
        return nazwa;
    }


    public long getDlug() {
        return dlug;
    }


    public long getLudnosc() {
        return ludnosc;
    }

    public long dlugNaJednego() {
        return (this.dlug * 1000)/this.ludnosc;
    } 

    @Override
    public String toString() {
        return String.format("%30s %20stys %20s", this.nazwa, this.dlugNaJednego(), this.ludnosc);
    }
}
