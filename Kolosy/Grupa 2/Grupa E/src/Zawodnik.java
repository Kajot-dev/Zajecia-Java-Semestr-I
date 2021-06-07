public class Zawodnik {
    private String nazwisko;
    private int numerStartowy;
    private double dlugoscSkoku;

    public Zawodnik(String nazwisko, int numerStartowy, double dlugoscSkoku) {
        this.nazwisko = nazwisko;
        this.numerStartowy = numerStartowy;
        this.dlugoscSkoku = dlugoscSkoku;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getNumerStartowy() {
        return numerStartowy;
    }

    public double getDlugoscSkoku() {
        return dlugoscSkoku;
    }

    @Override
    public String toString() {
        return String.format("Nazwisko: %s, Nr startowy: %s, Dlugosc skoku: %s", this.nazwisko, this.numerStartowy, this.dlugoscSkoku);
    }

}
