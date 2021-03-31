package kredyty;

class Rata {

    private double kapital;
    private double odsetki;

    public Rata(double kapital, double odsetki) {
        this.kapital = kapital;
        this.odsetki = odsetki;
    }

    public double getOdsetki() {
        return this.odsetki;
    }

    public double getKapital() {
        return this.kapital;
    }

    public double getWartosc() {
        return this.kapital + this.odsetki;
    }
    
}
