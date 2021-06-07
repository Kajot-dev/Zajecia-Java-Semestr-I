import java.util.*;

public class ParzystaNieparzysta {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int max;
        while (true) {
            System.out.print("Podaj górny zakres: ");
            try {
                max = s.nextInt();
                if (max >= 1) break;
            } catch (InputMismatchException e) {
                s.next();
            }
            System.out.println("Podano nieprawidłową liczbę");
        }
        s.close();

        //ciekawe, bo mój sposób nie sprawdza parzystości, tylko wykorzustuje jej naprzemienność
        //niemniej napisałem dodatkowo metodę czyParzyste, na potrzeby tego zadania, chociaż nie była ona bezpośrednio używana
        boolean parity = false;
        for (int i = 1; i <= max; i++) {
            System.out.println("Liczba " + i + " jest " + (parity ? "parzysta!" : "nieparzysta!"));
            System.out.println("Wynik metody: " + czyParzyste(i));
            parity = !parity;
        }
    }

    private static boolean czyParzyste(int liczba) {
        return liczba % 2 == 0;
    }
}
