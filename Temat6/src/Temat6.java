import java.util.*;

public class Temat6 {

    private static final Scanner cin = new Scanner(System.in);
    private static final String FORMAT_HELPER = "Para (%s, %s) -> %s + %s = %s, K: %s";

    private static int getInt(String komunikat) {
        komunikat += ": ";
        int x;
        while (true) {
            try {
                System.out.print(komunikat);
                x = cin.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Podano nieprawidlowa liczbe!");
                cin.nextLine(); //odrzuc nieprawidlowy wpis
            }
        }
        return x;
    }

    private static int[] getInts(int x) {
        int[] tab = new int[x];
        for (int i = 0; i < x; i++) {
            tab[i] = getInt("Podaj liczbe calkowita nr " + (i+1));
        }
        return tab;
    }

    private static int suma(int[] tab) {
        int sum = 0;
        for (int i : tab) sum += i;
        return sum;
    }

    private static int pary(int[] myArray, int k, boolean log) {
        int sum = 0;
        for (int i = 0; i < myArray.length-1; i++) {
            for (int j = i+1; j < myArray.length; j++) {
        
                int suma = myArray[i] + myArray[j];

                if (suma % k == 0) {
                    sum++; //tutaj sumowanie

                    if (log) { //opcjonalne wypisywanie
                        System.out.println(String.format(FORMAT_HELPER, i, j, myArray[i], myArray[j], suma, k));
                    }

                }
                
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        int rozmiar = getInt("Podaj wielkosc tablicy");

        System.out.print('\n');

        int[] tab = getInts(rozmiar);

        System.out.print('\n');

        int dzielnik = getInt("Podaj wielkosc dzielnika");

        System.out.print('\n');

        //WYPISYWANIE
        System.out.print("Wypisuje liczby: ");
        for (int i : tab) System.out.print(i + " ");
        System.out.print('\n');

        //SUMA
        System.out.println("Suma: " + suma(tab));

        //PARY
        int p = pary(tab, dzielnik, true);
        System.out.println("Liczba par: " + p);
    }
}
