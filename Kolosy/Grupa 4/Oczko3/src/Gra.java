import java.util.*;
import java.io.*;

public class Gra {
    private static Scanner cin = new Scanner(System.in);
    private int monety;
    private List<Karta> talia = new ArrayList<>();
    public static void main(String[] args) {
        var oczko = new Gra();
        oczko.graj();
    }
    public Gra() {
        this.monety = 50;
    }
    public void graj() {
        do {
            grajRunde();
        } while (askifContinue());
        System.out.println("DO WIDZENIA posiadasz " + this.monety + " monet");
        System.exit(0);
    }
    public void grajRunde() {
        //bez sensu wczytywac talie, ktora jest taka sama z, ktorej zawartosc sie nie zmienia
        //za kazdym razem
        //ale tak jest w poleceniu
        this.talia = Gra.wczytajTalie();
        if (this.monety >= 5) {
            this.monety -= 5;
            Collections.shuffle(this.talia);
            Karta karta1 = this.talia.get(0);
            Karta karta2 = this.talia.get(1);
            Karta karta3 = this.talia.get(this.talia.size() - 1);
            System.out.println(karta1);
            System.out.println(karta2);
            System.out.println(karta3);
            int suma = karta1.getWartosc() + karta2.getWartosc() + karta3.getWartosc();
            System.out.println("SUMA PUNKTOW: " + suma);
            if (suma == 21) {
                System.out.println("***ZWYCIĘSTWO***");
                this.monety += 25;
            } else {
                System.out.println("***PORAŻKA***");
            }
        } else {
            System.out.println("SKOŃCZYŁY CI SIĘ MOENTY - KONIEC GRY");
            System.exit(0);
        }
    }

    private static List<Karta> wczytajTalie() {
        List<Karta> tempTalia = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream("data.csv");
                Scanner fic = new Scanner(fis);
        ) {
            while (fic.hasNextLine()) {
                var line = fic.nextLine().trim();
                if (line.length() == 0) continue;
                tempTalia.add(new Karta(line));
            }
        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();
        }
        return tempTalia;
    }

    private static boolean askifContinue() {
        String ans;
        while (true) {
            System.out.println("Czy chcesz zagrać jeszcze raz? (T/N)");
            ans = cin.nextLine().trim();
            if (ans.length() > 0) {
                if (ans.charAt(0) == 'T') {
                    return true;
                } else if (ans.charAt(0) == 'N') {
                    return false;
                }
            }
            System.out.println(ans + " to niepoprawna decyzja!");
        }
    }
}
