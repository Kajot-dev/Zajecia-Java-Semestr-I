class Karta {

    private char symbol;
    private char kolor;
    private int wartosc;

    @Override
    public String toString() {
        return "Karta{" +
                "symbol=" + symbol +
                ", kolor=" + kolor +
                ", wartosc=" + wartosc +
                '}';
    }

    Karta(String csvLine) {

        String[] data = csvLine.split(";");
        this.symbol = data[0].charAt(0);
        this.kolor = data[1].charAt(0);
        this.wartosc = Integer.parseInt(data[2]);
    }

    public char getSymbol() {
        return symbol;
    }

    public char getKolor() {
        return kolor;
    }

    public int getWartosc() {
        return wartosc;
    }
}
