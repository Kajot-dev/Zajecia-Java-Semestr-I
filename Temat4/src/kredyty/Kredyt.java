package kredyty;

public abstract class Kredyt {

    protected static final int M = 12;

    private static final String FORMAT_HELPER = ".2f | %";
    private static final String FORMAT_HELPER_2 = "s | %-";

    protected final Rata[] raty;
    protected final double kwota; 
    protected final int miesiace;
    protected final double procent;
    protected final double procentMiesiecznie;

    protected Kredyt(double kwota, int miesiace, double procent) {
        this.kwota = kwota;
        this.miesiace = miesiace;
        this.procent = procent;
        this.procentMiesiecznie = this.procent / Kredyt.M;
        this.raty = new Rata[miesiace];
    }

    public double getKwota() {
        return this.kwota;
    }

    public double getOprocentowanie() {
        return this.procent;
    }

    public int getMiesiace() {
        return this.miesiace;
    }

    public double laczneOdsetki() {
        double o = 0.0;
        for (Rata r: raty) {
            o += r.getOdsetki();
        }
        return o;
    }

    public Rata getRata(int i) {
        return raty[i];
    }

    public String toString() {

        int nrLength = String.valueOf(this.raty.length - 1).length();
        int kpLength = String.valueOf((int)this.raty[raty.length - 1].getKapital()).length() + 3;
        int odsLength = String.valueOf((int)this.raty[0].getOdsetki()).length() + 3;
        int rLength = String.valueOf((int)this.raty[0].getWartosc()).length() + 3;
        int kwLength = String.valueOf((int)this.kwota).length() + 3;

        nrLength = nrLength >= 2 ? nrLength : 2;
        kpLength = kpLength >= 7 ? kpLength : 7;
        odsLength = odsLength >= 7 ? odsLength : 7;
        rLength = rLength >= 7 ? rLength : 7;
        kwLength = kwLength >= 4 ? kwLength : 4;
        
        final String f = "| %" + nrLength + "d | %" + kpLength + FORMAT_HELPER + odsLength + FORMAT_HELPER + rLength + FORMAT_HELPER + kwLength + ".2f |";

        final String f2 = "| %-" + nrLength + FORMAT_HELPER_2 + kpLength + FORMAT_HELPER_2 + odsLength + FORMAT_HELPER_2 + rLength + FORMAT_HELPER_2 + kwLength + "s |";

        StringBuilder sb = new StringBuilder();

        sb.append("Kwota kredytu: ").append(String.format("%.2f", this.kwota)).append('\n');
        sb.append("Liczba miesiecy (rat): ").append(this.miesiace).append('\n');
        sb.append("Oprocentowanie: ").append(String.format("%.2f", this.procent * 100)).append('%').append('\n');
        sb.append("Tabela amortyzacji kredytu:\n");


        sb.append(String.format(f2, "Nr", "Kapital", "Odsetki", "Wartosc", "Dlug")).append('\n');


        double dlug = this.kwota;
        double wartoscKredytu = 0;

        for (int i = 0; i < raty.length; i++) {

            final Rata r = raty[i];
            dlug -= r.getKapital();

            final double wartosc = r.getWartosc();
            wartoscKredytu += wartosc;

            sb.append(String.format(f, i+1, r.getKapital(), r.getOdsetki(), wartosc, dlug)).append('\n');
        }

        sb.append("Laczna kwota do zaplaty: ").append(String.format("%.2f", wartoscKredytu)).append('\n');

        return sb.toString();
    }
}
