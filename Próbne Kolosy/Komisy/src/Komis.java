import java.util.LinkedList;
import java.util.List;

public class Komis {
    private String nazwaKomisu;
    private List<Auto> auta = new LinkedList<>();
    private Auto okazja = null;

    public Komis(String nazwa) {
        this.nazwaKomisu = nazwa;
    }

    public String getNazwaKomisu() {
        return nazwaKomisu;
    }

    public List<Auto> getAuta() {
        return auta;
    }

    public Auto getOkazja() {
        return okazja;
    }

    public void dodajAuto(Auto samochod) {
        this.auta.add(samochod);
    }

    public void usunAuto(Auto samochod) {
        if (!this.auta.contains(samochod)) {
            System.err.println(samochod + " nie znajduje sie w ofercie komisu " + this.nazwaKomisu);
        } else if (samochod == null) {
            System.err.println("Nie mozna usunac pustego obiektu!");
        } else {
            this.auta.remove(samochod);
            if (this.okazja == samochod) this.okazja = null;
        }
    }

    public void wyznaczOkazje() {
        Auto samochod = null;
        for (Auto aktualne : this.auta) {
            if (samochod == null || aktualne.wspolczynnik() < samochod.wspolczynnik()) {
                samochod = aktualne;
            }
        }
        this.okazja = samochod;
    }

    
}
