import java.util.*;

public class Zawody {
    private static boolean jestWlaczony = false;
    private static List<Zawodnik> listaZawodnikow = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    private static final String[] OPCJE = new String[] {
        "DODAJ ZAWODNIKA",
        "WYPISZ ZAWODNIKOW",
        "POKAZ NAJDLUZSZY SKOK",
        "USUN ZAWODNIKA",
        "ZAKONCZ"
    };
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        if (jestWlaczony) return;
        jestWlaczony = true;
        while(jestWlaczony) {
            wypiszOpcje(OPCJE);
            int wybor = askInt(1, OPCJE.length, "?");
            switch(wybor) {
                case 1:
                    dodajZawodnika();
                    break;
                case 2:
                    wypiszZawodnikow();
                    break;
                case 3:
                    wypiszNajdluzszy();
                    break;
                case 4:
                    usunZawodnika();
                    break;
                case 5:
                    System.out.println("Koncze program...");
                    jestWlaczony = false;
                    break;
                default:
                    System.out.println("nieprawidlowy nr");
                    break;
            }
        }
    }

    private static void usunZawodnika() {
        int nr = askInt(0, Integer.MAX_VALUE, "Podaj nr startowy zawodnika");
        for (Zawodnik z : listaZawodnikow) {
            if (z.getNumerStartowy() == nr) {
                listaZawodnikow.remove(z);
                System.out.println("usunieto zawodnika: " + z);
                return;
            }
        }
        System.out.println("Nie znaleziono zawodnika z takim numerem!");
    }

    private static void wypiszNajdluzszy() {
        Zawodnik najlepszy = null;
        for (Zawodnik z : listaZawodnikow) {
            if (najlepszy == null || najlepszy.getDlugoscSkoku() < z.getDlugoscSkoku()) {
                najlepszy = z;
            }
        }
        if (najlepszy == null) {
            System.out.println("Nie ma najlepszego zawodnika!");
        } else {
            System.out.println("Najdluzszy skok wynosi: " + najlepszy.getDlugoscSkoku());
            System.out.println("Wykonal go zawodnik: " + najlepszy);
        }
    }

    private static void dodajZawodnika() {
        String nazwisko = askString("Podaj nazwisko zawodnika");
        int nr = askInt(0, Integer.MAX_VALUE, "Podaj nr zawodnika");
        double skok = askDouble(0, "Podaj dlugosc skoku");
        listaZawodnikow.add(new Zawodnik(nazwisko, nr, skok));
    }

    private static void wypiszZawodnikow() {
        if (listaZawodnikow.isEmpty()) {
            System.out.println("Nie ma zadnych zawodnikow");
            return;
        }
        for (int i = 0; i < listaZawodnikow.size(); i++) {
            System.out.println((i+1) + ". " + listaZawodnikow.get(i));
        }
    }

    private static void wypiszOpcje(String[] opcje) {
        StringBuilder sb = new StringBuilder();
        sb.append("Wybierz opcje:\n");
        for (int i=0; i < opcje.length; i++) {
            sb.append("\t(" + (i+1) + ") " + opcje[i] + '\n');
        }
        System.out.println(sb);
    }

    private static int askInt(int min, int max, String message) {
        int x;
        while (true) {
            try {
                System.out.print(message + ": ");
                x = Integer.parseInt(in.nextLine());
                if (x >= min && x <= max) break;
                System.out.println("Podana liczba musi byc niemniejsza niz " + min + " i niewieksza niz " + max + "!");
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidlowy numer!");
            }
        }
        return x;
    }

    private static double askDouble(double min, String message) {
        double x;
        while (true) {
            try {
                System.out.print(message + ": ");
                x = Double.parseDouble(in.nextLine());
                if (x >= min) break;
                System.out.println("Podana liczba musi byc niemniejsza niz " + min);
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidlowy numer!");
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
            System.out.println("Odpowiedz nie moze byc pusta!");
        }
        return x;
    }
}
