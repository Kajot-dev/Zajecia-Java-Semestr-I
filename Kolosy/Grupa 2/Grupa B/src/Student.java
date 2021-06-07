public class Student {
    private String nazwisko;
    private int nrAlbumu;
    private double srednia;

    public Student(String nazwisko, int nrAlbumu, double srednia) {
        this.nazwisko = nazwisko;
        this.nrAlbumu = nrAlbumu;
        this.srednia = srednia;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getNumerAlbumu() {
        return nrAlbumu;
    }

    public double getSrednia() {
        return srednia;
    }

    @Override
    public String toString() {
        return String.format("Nazwisko=%s, Nr albumu=%s, Srednia=%s", nazwisko, nrAlbumu, srednia);
    }

}
