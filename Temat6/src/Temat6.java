import java.util.*;

public class Temat6 {

    private static final Scanner cin = new Scanner(System.in);
    private static final String FORMAT_HELPER = "Para (%s, %s) -> %s + %s = %s, K: %s";
    private static final boolean LOG = true;

    private static int getInt(String komunikat) {
        komunikat += ": ";
        int x;
        while (true) {
            try {
                System.out.print(komunikat);
                x = Integer.parseInt(cin.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidlowa liczbe!");
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

    private static int pary(int[] myArray, int k) {
        int licznik = 0;
        for (int i = 0; i < myArray.length-1; i++) {
            for (int j = i+1; j < myArray.length; j++) {
        
                int suma = myArray[i] + myArray[j];

                if (suma % k == 0) {
                    licznik++; //tutaj liczenie

                    if (LOG) { //opcjonalne wypisywanie
                        System.out.println(String.format(FORMAT_HELPER, i, j, myArray[i], myArray[j], suma, k));
                    }

                }
                
            }
        }
        return licznik;
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
        int p = pary(tab, dzielnik);
        System.out.println("Liczba par: " + p);
    }
}
