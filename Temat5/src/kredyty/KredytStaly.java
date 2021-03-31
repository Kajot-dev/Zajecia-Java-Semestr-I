package kredyty;



public class KredytStaly extends Kredyt{

    private final double wysokoscRaty;

    public KredytStaly(double kwota, int miesiace, double procent) {
        super(kwota, miesiace, procent);
        this.wysokoscRaty = rataStala(kwota, miesiace, procent);

        double dlug = this.kwota;
        for (int i = 0; i < raty.length; i++) {
            final double odsetki = dlug * this.procentMiesiecznie;
            final double rataKapitalowa = this.wysokoscRaty - odsetki;
            this.raty[i] = new Rata(rataKapitalowa, odsetki);
            dlug -= rataKapitalowa;
        }

    }

    public double getwysokoscRaty() {
        return this.wysokoscRaty;
    }

    private double rataStala(double kwota, int miesiace, double procent) {
        final double Q = 1 + procent/Kredyt.M;
        final double Qn = Math.pow(Q, miesiace);
        return kwota * Qn * ((Q - 1) / (Qn - 1));
    }

}
