import java.util.InputMismatchException;
import java.util.Scanner;

public class LiczbaPierwsza {

    public static final Scanner cin = new Scanner(System.in);

    public static int getInt() { //cała ta funkcja pozwala wpisać liczbę przez użytkownika
        int liczba;
        while (true) {
            try {
                System.out.print("Podaj liczbe calkowita: ");
                liczba = cin.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Podano niepoprawną liczbę!");
                cin.next(); //odrzuć błędną liczbę
            }
        }
        return liczba;
    }

    public static void isPrime(int liczba) {
        for (int dzielnik = 2; dzielnik*dzielnik <= liczba; dzielnik++) { //skrócono pętle do pierwiastka z liczby
            if (liczba % dzielnik == 0) {
                printPrime(liczba, false);
                /** Można użyć zamiast:
                 * System.out.println("Liczba " + liczba + " NIE jest liczbą pierwszą!")
                 */ 
                return;
            }
        }
        printPrime(liczba, true);
        /** Można użyć zamiast:
         * System.out.println("Liczba " + liczba + " JEST liczbą pierwszą!")
         */ 
    }

    public static void printPrime(int liczba, boolean is) {
        System.out.println("Liczba " + liczba + (is ? " " : " NIE ") + "JEST liczbą pierwszą");
    }

    public static void main(String[] args) { 
        final int liczba = getInt(); //tak naprawdę ważna jest tylko funkcja isPrime
        isPrime(liczba);
    }
}
