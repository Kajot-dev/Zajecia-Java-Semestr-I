package kosci;

import java.util.*;

public class Gracz {

    

    private String imie;
    private List<Rzut> rzuty = new ArrayList<>();

    private int wynik = 0;

    public Gracz(String imie) {
        this.imie = imie;
    }

    void rzuc(List<Integer> kosci) {
        Rzut r = new Rzut(kosci);
        if (Gra.SAVE_THROWS) this.rzuty.add(r);
        

        if (Gra.LOG) {
            System.out.println(this.imie + " rzuca: " + r.wyniki().toString());
        }
        

        this.wynik += r.suma();
    }

    String getName() {
        return this.imie;
    }

    int wynik() {
        return this.wynik;
    }

    List<Rzut> getRzuty() {
        return this.rzuty;
    }
}
