import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Kolorki {
    private static final String[] KOLORY = new String[] {
        "FIOLETOWY",
        "ZIELONY",
        "ZOLTY",
        "BORDOWY",
        "BIALY",
        "CZARNY",
        "ROZOWY",
        "POMARANCZOWY"
    };
    private static final Random losowy = new Random();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        zgadujKolory();
    }

    private static void zgadujKolory() {
        wypiszKolory();

        String ans;
        int i = 0;

        String wylosowanyKolor = KOLORY[losowy.nextInt(KOLORY.length)];

        while (true) {
            i++;
            System.out.print("Zgaduj! ");
            ans = input.nextLine().trim().toUpperCase();

            if (!Arrays.asList(KOLORY).contains(ans)) {
                System.out.println("Podany kolor nie istnieje!");
            }
            if (wylosowanyKolor.equals(ans)) break;
            System.out.println("Nie uidalo sie, sprobuj jeszcze raz!");
        }
        System.out.println("Udalo ci sie zgadnac: " + wylosowanyKolor + "!");
        System.out.println("Potrzebowales/as " + i + " prob!");
    }

    private static void wypiszKolory() {
        System.out.println("Mozliwe kolory:");
        for (int i=0;i < KOLORY.length; i++) {
            System.out.println("\t- " + KOLORY[i]);
        }
    }
}
