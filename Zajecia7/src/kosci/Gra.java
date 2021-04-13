package kosci;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

public class Gra {

    protected static final boolean SAVE_THROWS = false;
    protected static final boolean LOG = true;

    private final Gracz[] gracze;
    private final List<Integer> kosci;
    private final int tury;
    private boolean ended = false;

    public Gra(Gracz[] gracze, List<Integer> kosci, int tury) {
        if (tury <= 0)
            throw new IllegalArgumentException("Ilosc tur musi byc wieksza niz 0");
        if (kosci.isEmpty())
            throw new IllegalArgumentException("W grze musi byc conajmniej jedna kostka!");
        else {
            for (int k : kosci)
                if (k < 2)
                    throw new IllegalArgumentException("Kazda z kosci musi miec conajmniej 2 scianki!");
        }
        if (gracze.length < 2)
            throw new IllegalArgumentException("W grze musi vyc conajmniej 2 graczy!");

        this.gracze = gracze;
        this.kosci = kosci;
        this.tury = tury;
    }

    public String status() {
        StringBuilder sb = new StringBuilder();
        List<Gracz> zwyciezcy = getZwyciezcy();

        if (zwyciezcy.size() == 1) {
            if (!this.ended)
                sb.append("Aktualnie zwyciezca ");
            else
                sb.append("Zwyciezca ");
            sb.append("jest: ");
        } else {
            if (!this.ended)
                sb.append("Aktualnie jest ");
            else
                sb.append("Jest ");
            sb.append("remis pomiędzy: ");
        }

        final int zs = zwyciezcy.size();

        for (int i = 0; i < zs; i++) {
            sb.append(zwyciezcy.get(i).getName());
            if (i < zs - 1)
                sb.append(", ");
        }

        sb.append(" z wynikiem ").append(zwyciezcy.get(0).wynik()).append('!');
        return sb.toString();
    }

    public List<Gracz> getZwyciezcy() {
        List<Gracz> zwyciezcy = new ArrayList<>();
        int max = 0;
        for (Gracz g : this.gracze) {
            final int w = g.wynik();
            if (w > max) {
                zwyciezcy.clear();
                zwyciezcy.add(g);
                max = w;
            } else if (w == max) {
                zwyciezcy.add(g);
            }
        }
        return zwyciezcy;
    }

    public void graj() {
        graj(0);
    }

    public void graj(int milis) {
        if (this.ended) {
            System.out.println(this.wyniki());
            return;
        }
        System.out.println("---POCZĄTEK GRY---");
        for (int i = 1; i <= tury; i++) {
            if (Gra.LOG) System.out.println("\nTURA " + i + ":\n");
            for (Gracz g : gracze) {
                g.rzuc(kosci);
                if (milis > 0)
                    this.await(milis);
            }

            if (Gra.LOG) {
                System.out.println();
                System.out.println(this.status());
            }
            

            if (milis > 0)
                this.await(milis);

        }
        this.ended = true;
        System.out.println();
        System.out.println(this.wyniki());
    }

    public String wyniki() {
        StringBuilder sb = new StringBuilder();
        sb.append("---WYNIKI---\n");
        sb.append("W grze brali udzial: ");
        for (int i = 0; i < this.gracze.length; i++) {
            sb.append(this.gracze[i].getName());
            if (i < this.gracze.length - 1)
                sb.append(", ");
        }
        sb.append("\nZa pomocą ").append(this.kosci.size()).append(" kosci: ").append(this.kosci.toString());
        sb.append("\nGra trwala ").append(this.tury).append(" tur(y)!");
        sb.append("\n\n").append("Wyniki: \n");
        for (Gracz g : this.gracze) {
            sb.append(g.getName()).append(": ").append(g.wynik()).append('\n');
        }
        sb.append(this.status()).append('\n');
        return sb.toString();
    }

    private void await(int milis) {
        try {
            TimeUnit.MILLISECONDS.sleep(milis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Gracz[] getGracze() {
        return this.gracze;
    }

    public int[] countForEachPlayerThrows(Predicate<Rzut> check) {
        int[] output = new int[this.gracze.length];
        for (int i = 0; i < this.gracze.length; i++) {
            final Gracz g = this.gracze[i];
            int suma = 0;
            for (Rzut r : g.getRzuty()) {
                if (check.test(r))
                    suma++;
            }
            output[i] = suma;
        }
        return output;
    }

    public int[] countForEachPlayerDiceInThrows(ToIntFunction<Rzut> check) {
        int[] output = new int[this.gracze.length];
        for (int i = 0; i < this.gracze.length; i++) {
            final Gracz g = this.gracze[i];
            int suma = 0;
            for (Rzut r : g.getRzuty()) {
                suma += check.applyAsInt(r);
            }
            output[i] = suma;
        }
        return output;
    }
}
