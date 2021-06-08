public class Wydatek {
    private String nazwa;
    private int nrWydatku;
    private double suma;
    
    public Wydatek(String nazwa, int nrWydatku, double suma) {
        this.nazwa = nazwa;
        this.nrWydatku = nrWydatku;
        this.suma = suma;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getNrWydatku() {
        return nrWydatku;
    }

    public double getSuma() {
        return suma;
    }

    @Override
    public String toString() {
        return "Wydatek [nazwa=" + nazwa + ", nrWydatku=" + nrWydatku + ", suma=" + suma + "]";
    } 

}
