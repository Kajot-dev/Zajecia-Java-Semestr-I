public class Auto {
    private String marka;
    private String model;
    private int rocznik;
    private int przebieg;
    private double cena;

    public Auto(String marka, String model, int rocznik, int przebieg, double cena) throws WrongvalueException {
        this.marka = marka;
        this.model = model;
        if (rocznik < 1900 || rocznik > 2018) throw new WrongvalueException("Samochod musi byc wyprodukowany pomiedzy rokiem 1900, a 2018!");
        this.rocznik = rocznik;
        if (przebieg < 0) throw new WrongvalueException("Przebieg musi byc wartoscia dodatnia!");
        this.przebieg = przebieg;
        this.cena = cena;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public int getRocznik() {
        return rocznik;
    }

    public int getPrzebieg() {
        return przebieg;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    
    protected double wspolczynnik() {
        return (this.cena*this.przebieg)/(this.rocznik - 1899);
    }

    @Override
    public String toString() {
        return String.format("Auto [marka=%s, model=%s, rocznik=%s, przebieg=%s, cena=%s]", 
            this.marka,
            this.model,
            this.rocznik,
            this.przebieg,
            this.cena
        );
    }
    
}
