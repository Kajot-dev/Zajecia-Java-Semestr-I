package kosci;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;  

class Rzut {
    
    private static final ThreadLocalRandom randHelp = ThreadLocalRandom.current();

    final List<Integer> kosci;
    final List<Integer> wyniki = new ArrayList<>();

    Rzut(List<Integer> kosci) {
        this.kosci = kosci;
        for (int w : this.kosci) {
            wyniki.add(Rzut.randInt(w));
        }
    }

    int suma() {
        int suma = 0;
        for (int w : this.wyniki) {
            suma += w;
        }
        return suma;
    }

    List<Integer> wyniki() {
        return wyniki;
    }

    private static int randInt(int wielkosc) {
        return Rzut.randHelp.nextInt(1, wielkosc + 1);
    }
}
