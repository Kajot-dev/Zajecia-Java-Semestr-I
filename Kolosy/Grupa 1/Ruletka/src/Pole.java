public class Pole {
    public char kolor;
    public int numer;

    public Pole(String pole) {
        String[] dane = pole.trim().split("_");
        this.numer = Integer.parseInt(dane[0]);
        this.kolor = dane[1].charAt(0);
    }

    public char getKolor() {
        return kolor;
    }

    public int getNumer() {
        return numer;
    }
}
