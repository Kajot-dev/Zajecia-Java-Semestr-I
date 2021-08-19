import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Oczko {
    private static final String TALIA = "A_H,A_D,A_C,A_S,K_H,K_D,K_C,K_S,Q_H,Q_D,Q_C,Q_S,J_H,J_D,J_C,J_S,10_H,10_D,10_C,10_S,9_H,9_D,9_C,9_S,";
    private static Scanner cin = new Scanner(System.in);
    private Random random = new Random();
    private int zetony;
    private List<Karta> taliaKart;

    public Oczko() {
        this.zetony = 100;
        this.taliaKart = new ArrayList<>();
    }
    protected void wczytajKarty() {
        String[] karty = TALIA.split(",");
        taliaKart.clear();
        for (String k : karty) {
            taliaKart.add(new Karta(k));
        }
    }
    public void graj() {
        while (true) {
            this.wczytajKarty();
            this.zetony -= 10;
            int los;
            Karta karta1;
            Karta karta2;
            los = random.nextInt(this.taliaKart.size());
            karta1 = taliaKart.get(los);
            taliaKart.remove(karta1);
            los = random.nextInt(this.taliaKart.size());
            karta2 = taliaKart.get(los);
            taliaKart.remove(karta2);
            System.out.println("Wylosowane karty:");
            System.out.println(karta1);
            System.out.println(karta2);
            int sumaPunktow = karta1.getPunkty() + karta2.getPunkty();
            System.out.println("Suma punktów: " + sumaPunktow);
            if (sumaPunktow == 21 || (karta1.getWartosc().equals("A") && karta2.getWartosc().equals("A"))) {
                System.out.println("WYGRANA");
                this.zetony += 40;
            } else {
                System.out.println("PRZEGRANA");
            }

            if (this.zetony <= 0) {
                System.out.println("KONIEC ROZGRYWKI! NIE MASZ ŻETONÓW");
                break;
            } else if (!Oczko.askEnd()) {
                System.out.println("KONIEC ROZGRYWKI! Masz " + this.zetony + " żetonów!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Oczko gra = new Oczko();
        gra.graj();
    }

    private static boolean askEnd() {
        String x;
        while (true) {
            System.out.println("Czy zagrać ponownie?");
            x = cin.nextLine();
            if (x.equals("TAK")) {
                return true;
            } else if (x.equals("NIE")) {
                return false;
            } else {
                System.out.println(x + " to niepoprawne dane!");
            }
        }
    }
}
