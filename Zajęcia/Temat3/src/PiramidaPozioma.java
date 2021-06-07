import java.util.*;

public class PiramidaPozioma {

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
        final String block = "#";
        for (int i = 1; i <= wys; i++) System.out.println(block.repeat(i));
        for (int i = wys - 1; i > 0; i--) System.out.println(block.repeat(i));
    }
}