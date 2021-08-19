import java.util.HashMap;

public class Karta {
    private static HashMap<String, Integer> punkty = new HashMap<>();
    static {
        punkty.put("A", 11);
        punkty.put("K", 10);
        punkty.put("Q", 9);
        punkty.put("J", 8);
        punkty.put("10", 10);
        punkty.put("9", 9);
    }
    private char symbol;
    private String wartosc;

    public Karta(String data) {
        String[] dane = data.split("_");
        this.wartosc = dane[0];
        this.symbol = dane[1].charAt(0);
    }

    public char getSymbol() {
        return symbol;
    }

    public String getWartosc() {
        return wartosc;
    }

    public int getPunkty() {
        return punkty.get(this.wartosc);
    }

    @Override
    public String toString() {
        return "Karta{" +
                "symbol=" + symbol +
                ", wartosc='" + wartosc + '\'' +
                '}';
    }
}
