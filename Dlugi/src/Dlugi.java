import java.io.*;
import java.util.*;

public class Dlugi {
    private static List<Kraj> kraje = new ArrayList<>();
    private static boolean jestWlaczony = false;
    private static final String[] OPCJE = new String[] {
        "WYSWIETL LISTE",
        "SORTUJ",
        "DODAJ KRAJ",
        "ZAKONCZ"
    };
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        try (
           BufferedInputStream buff = new BufferedInputStream(new FileInputStream("pliki/debt.txt"));
           Scanner input = new Scanner(buff); 
        ) {
            while(input.hasNextLine()) {
                String linia = input.nextLine();
                if (linia.isBlank()) continue;
                String[] dane = linia.split(";");
                dodajKraj(dane);                
            }
        } catch (IOException e) {
            System.err.println("Blad odczytu pliku!");
        }
        Dlugi.uruchom();
    }

    public static void dodajKraj(String[] dane) {
        try {
            Dlugi.kraje.add(new Kraj(dane[0], Long.parseLong(dane[1]), Long.parseLong(dane[2])));
        } catch (NumberFormatException e) {
            System.err.println("Ten krj ma niepoprawne dane!");
        }
    }

    public static void uruchom() {
        if (jestWlaczony) return;
        jestWlaczony = true;

        while (jestWlaczony) {
            wypiszOpcje(OPCJE);
            int wybor = askInt(1, 4);
            switch (wybor) {
                case 1:
                    System.out.println(String.format("%30s %20stys %20s", "Nazwa kraju", "Dlug na mieszkanca", "Ludnosc"));
                    for (Kraj k : Dlugi.kraje) System.out.println(k);
                    break;
                case 2:
                    Collections.sort(Dlugi.kraje, Kraj.comp);
                    System.out.println("Posortowano!");
                    break;
                case 3:
                    String nazwa = askString("Podaj nazwe kraju");
                    System.out.println("Podaj wysokosc dlugu w milionach USD");
                    long dlug = askLong(0);
                    System.out.println("Podaj liczbe mieszkancow");
                    long ludnosc = askLong(1);
                    Dlugi.kraje.add(new Kraj(nazwa, dlug, ludnosc));
                    System.out.println("Dodano kraj!");
                    break;
                case 4:
                    System.out.println("Koncze program...");
                    jestWlaczony = false;
                    break;
                default:
                    System.out.println("Nieprawidlowa liczba!");
                    break;
            }
        }
    }

    public static void wypiszOpcje(String[] opcje) {
        System.out.println("WYBIERZ OPCJE:");
        for (int i=0; i < opcje.length; i++) {
            System.out.println("\t(" + (i+1) + ") " + opcje[i]);
        }

    }


    private static int askInt(int min, int max) {
        int x;
        while (true) {
            try {
                System.out.print("?: ");
                x = Integer.parseInt(in.nextLine());
                if (x >= min && x <= max) break;
                System.out.println("Liczba musi byc z zakresu " + min + "-" + max + "!");
            } catch (NumberFormatException e) {
                System.out.println("Podano niepoprawna liczbe!");
            }
        }
        return x;
    }

    private static long askLong(long min) {
        long x;
        while (true) {
            try {
                System.out.print("?: ");
                x = Long.parseLong(in.nextLine());
                if (x >= min) break;
                System.out.println("Liczba musi byc wieksza niz" + min + "!");
            } catch (NumberFormatException e) {
                System.out.println("Podano niepoprawna liczbe!");
            }
        }
        return x;
    }

    private static String askString(String message) {
        String x;
        while (true) {
            System.out.print(message + ": ");
            x = in.nextLine();
            if (!x.isBlank()) break;
            System.out.println("Nie mozna podac pustej odpowiedzi!");
        }
        return x;
    }
}
