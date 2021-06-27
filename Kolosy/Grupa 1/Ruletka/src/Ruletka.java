import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ruletka {

    private static String pola = "0_G;1_R;2_B;3_R;4_R;5_B;6_B;7_R;8_B;9_R;10_B;11_B;12_R;13_B;14_R;15_R;16_B;17_B;18_R;19_R;20_B";
    private static Scanner skaner = new Scanner(System.in);
    private List<Pole> listaPol = new ArrayList<>();
    private int zetony = 250;
    private Random rand = new Random();


    public Ruletka() {
        String[] danePol = pola.split(";");
        for (String p : danePol) {
            if (!p.isBlank()) {
                this.listaPol.add(new Pole(p));
            }
        }
    }

    public void graj() {
        for (;;) {
            if (this.zetony >= 25) {
                this.zetony -= 25;
                System.out.println("Pobrano wpisowe");
                char kolor = zapytaj("Obstaw kolor (B/R)", "B", "R").charAt(0);
                String parzystosc = zapytaj("Obstaw parzystosc (PARZYSTE/NIEPARZYSTE)", "PARZYSTE", "NIEPARZYSTE");
                Pole wylosowane = wylosujPole();
                System.out.println("Wypadło [" + wylosowane.getNumer() + "_" + wylosowane.getKolor() + "]");
                boolean parzystoscOK;
                if ((parzystosc.equals("PARZYSTE") && wylosowane.getNumer() % 2 == 0) || (parzystosc.equals("NIEPARZYSTE") && wylosowane.getNumer() % 2 == 1)) {
                    parzystoscOK = true;
                } else {
                    parzystoscOK = false;
                }
                if (parzystoscOK && kolor == wylosowane.getKolor()) {
                    System.out.println("WYGRANA 75 żetonów");
                    this.zetony += 75;
                } else {
                    System.out.println("PRZEGRANA");
                }

                char czyDalej = zapytaj("Czy chcesz dalej obstawiać? (T/N)", "T", "N").charAt(0);
                if (czyDalej == 'N') {
                    System.out.println("KONIEC! Masz " + this.zetony + " żetonów");
                    break;
                }
            } else {
                System.out.println("KONIEC! Nie masz już żetonów");
                break;
            }
        }
    }

    private Pole wylosujPole() {
        return this.listaPol.get(rand.nextInt(this.listaPol.size()));
    }



    public static String zapytaj(String pytanie, String opcja1, String opcja2) {
        String s;
        for (;;) {
            System.out.print(pytanie + ": ");
            s = skaner.nextLine().trim().toUpperCase();
            if (s.equalsIgnoreCase(opcja1)) return opcja1;
            else if (s.equalsIgnoreCase(opcja2)) return opcja2;
            System.out.println("Podaj prawidłowe dane wejściowe!");
        }
    }

    public static void main(String[] args) {
	    Ruletka gra = new Ruletka();
	    gra.graj();
    }
}
