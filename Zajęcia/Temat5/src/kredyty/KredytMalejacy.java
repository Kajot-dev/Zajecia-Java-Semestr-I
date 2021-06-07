package kredyty;


public class KredytMalejacy extends Kredyt {

    private final double kwotaKapitalowa;

    public KredytMalejacy(double kwota, int miesiace, double procent) {
        super(kwota, miesiace, procent);

        this.kwotaKapitalowa = kwota / miesiace;
        
        double dlug = kwota;

        for (int i = 0; i < miesiace; i++) {
            Rata r = new Rata(kwotaKapitalowa, dlug * this.procentMiesiecznie);
            raty[i] = r;
            dlug -= kwotaKapitalowa;
        }
    }
    public double getKwotaKapitalowa() {
        return this.kwotaKapitalowa;
    }
}
