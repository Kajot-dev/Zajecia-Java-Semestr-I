import java.util.*;

public class PiramidaPozioma {

    /**
     * Okazuje się, że nie muszę nic zmieniać, bo zrobiłem podawanie przez użytkownika już poprzednio :D
     */
    
    private static final String BLOCK = "#";
    
    private static final Scanner cin = new Scanner(System.in);

    private static int getInt() {
        int x;
        while (true) {
            System.out.print("Podaj wysokość piramidy: ");
            try {
                x = cin.nextInt();
                if (x >= 1) break;
            } catch (InputMismatchException e) {
                cin.next();
            }
            System.out.println("Podano nieprawidłową liczbę!");
        }
        return x;
    }

    public static String piramida(int wys) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= wys; i++) sb.append(BLOCK.repeat(i)).append('\n');
        for (int i = wys - 1; i > 0; i--) sb.append(BLOCK.repeat(i)).append('\n');

        return sb.toString();
    }
    public static void main(String[] args) {
        final int wys = getInt();
        final String p = piramida(wys);

        System.out.print(p);
    }
}