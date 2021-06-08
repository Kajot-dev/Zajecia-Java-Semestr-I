import java.util.*;

public class ZarzadzanieWydatkami {
    private static boolean jestWlaczony = false;
    private static final String[] OPCJE = new String[] {
        "DODAJ WYDATEK",
        "WYPISZ WYDATKI",
        "WYPISZ NAJWYZSZA SUME",
        "USUN WYDATEK",
        "ZAMKNIJ"
    };
    private static final String NIE_MA_WYDATKOW = "Nie ma zadnych wydatkow!";
    private static Scanner in = new Scanner(System.in);
    private static List<Wydatek> listaWydatkow = new ArrayList<>();

    public static void main(String[] args) {
        wyswietlajMenu();
    }

    public static void wyswietlajMenu() {
        if (jestWlaczony) return;
        jestWlaczony = true;
        while (jestWlaczony) {
            wypiszOpcje();
            int wybor = askInt(1, OPCJE.length, "Wybierz opcje");
            switch(wybor) {
                case 1:
                    dodajWydatek();
                    break;
                case 2:
                    wypiszWydatki();
                    break;
                case 3:
                    wyswietlSume();
                    break;
                case 4:
                    usunWydatek();
                    break;
                case 5:
                    jestWlaczony = false;
                    break;
                default:
                    System.err.println("Nieprawidlowy wybor!");
                    break;
            }       
        }
    }

    private static void usunWydatek() {
        if (listaWydatkow.isEmpty()) {
            System.out.println(NIE_MA_WYDATKOW);
            return;
        }

        int numer = askInt(0, Integer.MAX_VALUE, "Podaj numer wydatku");

        for (Wydatek w : listaWydatkow) {
            if (w.getNrWydatku() == numer) {
                listaWydatkow.remove(w);
                System.out.println("Usunieto wydatek " + w);
                break;
            }
        }        
    }

    private static void wyswietlSume() {
        if (listaWydatkow.isEmpty()) {
            System.out.println(NIE_MA_WYDATKOW);
            return;
        }
        Wydatek najdrozszy = listaWydatkow.get(0);

        for (int i = 1; i < listaWydatkow.size(); i++) {
            Wydatek aktualny = listaWydatkow.get(i);
            if (najdrozszy.getSuma() < aktualny.getSuma()) {
                najdrozszy = aktualny;
            }
        }

        System.out.println("Najwieszka suma to: " + najdrozszy.getSuma());
    }

    private static void wypiszWydatki() {
        if (listaWydatkow.isEmpty()) {
            System.out.println(NIE_MA_WYDATKOW);
            return;
        }
        for (int i = 0; i < listaWydatkow.size(); i++) {
            System.out.println((i+1) + ". " + listaWydatkow.get(i));
        }
    }

    private static void dodajWydatek() {
        int numer = askInt(0, Integer.MAX_VALUE, "Podaj nr wydatku");
        String nazwa = askString("Podaj nazwe wydatku");
        double suma = askDouble(0, Double.MAX_VALUE, "Podaj sume wydatku");
        Wydatek w = new Wydatek(nazwa, numer, suma);
        listaWydatkow.add(w);
        System.out.println("Dodano " + w.toString());
    }

    private static void wypiszOpcje() {
        for (int i = 0; i < OPCJE.length; i++) {
            System.out.println("\t (" + (i+1) + ") " + OPCJE[i]);
        }
    }

    private static int askInt(int min, int max, String pytanie) {
        int x;
        while (true) {
            try {
                System.out.print(pytanie + ": ");
                x = Integer.parseInt(in.nextLine());
                if (x <= max && x >= min) break;
                System.out.println("Liczba musi byc z przedzialu " + min + "-" + max + '!');
            } catch (NumberFormatException e) {
                System.err.println("Podano nieprawidlowa liczbe!");
            }
        }
        return x;
    }

    private static double askDouble(double min, double max, String pytanie) {
        double x;
        while (true) {
            try {
                System.out.print(pytanie + ": ");
                x = Double.parseDouble(in.nextLine());
                if (x <= max && x >= min) break;
                System.out.println("Liczba musi byc z przedzialu " + min + "-" + max + '!');
            } catch (NumberFormatException e) {
                System.err.println("Podano nieprawidlowa liczbe!");
            }
        }
        return x;
    }

    private static String askString(String pytanie) {
        String x;
        while (true) {
            System.out.print(pytanie + ": ");
            x = in.nextLine();
            if (!pytanie.isBlank()) break;
            System.out.println("Odpowiedz nie moze byc pusta!");
        }
        return x;
    }
}
