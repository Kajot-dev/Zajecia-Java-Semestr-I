public class Skarbonka {
    private double stan;
    private String owner;

    public Skarbonka(String wlasciciel, double kwota) {
        this.owner = wlasciciel;
        this.stan = kwota;
    }

    public void dodajOszczednosci(double kwota) {
        if (kwota < 0) throw new IllegalArgumentException("Kwota do dodania nie moze byc ujemna!");
        this.stan += kwota;
    }

    public void pobierzOszczednosci(double kwota) {
        if (kwota < 0) throw new IllegalArgumentException("Kwota do pobrania nie moze byc ujemna!");
        this.stan -= kwota;
    }

    public String getOwner() {
        return this.owner;
    }

    public double getOszczednosci() {
        return this.stan;
    }

    public void print() {
        System.out.println(this.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Wlasciciel: ").append(this.owner).append('\n').append("Kwota: ").append(String.format("%.2f", this.stan)).append(" PLN");
        return sb.toString();
    }
}
