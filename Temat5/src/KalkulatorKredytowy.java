import kredyty.*;
import java.util.*;

/**
 * Ulepszyłem formatowanie używając Formattera zamiast String.format + StringBuilder
 */


public class KalkulatorKredytowy {
    
    private static final Scanner cin = new Scanner(System.in);

    private static int getInt(String komunikat) {
        komunikat += ": ";
        int x;
        while (true) {
            System.out.print(komunikat);
            try {
                x = Integer.parseInt(cin.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłową liczbę!");
            }
        }
        return x;
    }

    private static double getDouble(String komunikat) {
        komunikat += ": ";
        double x;
        while (true) {
            System.out.print(komunikat);
            try {
                x = Double.parseDouble(cin.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłową liczbę!");
            }
        }
        return x;
    }

    public static void main(String[] args) {

        System.out.println("Do podawania ułamków używaj kropki!\n"); //dodatkowy enter

        final int type = getInt("Podaj typ kredytu (1 - malejący/2 - stały)");

        final double kwota = getDouble("Podaj kwotę kredytu");
        final double procent = getDouble("Podaj oprocentowanie w formie ułamka - 1 -> 100%");
        final int miesiace = getInt("Podaj liczbę miesięcy");

        Kredyt kredyt;

        System.out.println();

        if (type == 1) {
            kredyt = new KredytMalejacy(kwota, miesiace, procent);
        } else if (type == 2) {
            kredyt = new KredytStaly(kwota, miesiace, procent);
        } else throw new IllegalArgumentException("Nieprawidłowy typ kredytu!");

        System.out.print(kredyt);
    }
}
