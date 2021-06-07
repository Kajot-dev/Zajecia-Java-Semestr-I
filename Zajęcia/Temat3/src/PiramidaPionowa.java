import java.util.*;

public class PiramidaPionowa {

    /**
     * Nie byłem pewien czy wrzucić to w zadanie domowe, czy w material z zajęć, więc wrzucam w obydwa zadania
     */

    private static final Scanner cin = new Scanner(System.in);
    public static void main(String[] args) {
        int wys;
        while (true) {
            System.out.print("Podaj wysokość piramidy: ");
            try {
                wys = cin.nextInt();
                if (wys >= 1) break;
            } catch (InputMismatchException e) {
                cin.next();
            }
            System.out.println("Podano nieprawidłową liczbę!");
        }
        
        final int max = (wys - 1) * 2 + 1;
        for (int i=1; i <= max; i+= 2) {
            System.out.println(" ".repeat((max - i)/2) + "#".repeat(i));
        }
    }
}
