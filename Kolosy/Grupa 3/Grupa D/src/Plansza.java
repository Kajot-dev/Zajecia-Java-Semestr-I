import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Plansza {
    private static final Statek[] POLA = new Statek[8];
    private static final String[] OPCJE = new String[] {
        "DODAJ STATEK",
        "ZESTRZEL STATEK",
        "POKAZ NIEPARZYSTE STATKI",
        "POKAZ SUME STATKOW > 50",
        "WYJDZ"
    };
    private static Scanner in = new Scanner(System.in);
    private static boolean jestWlaczony = false;
    private static int sumaPunktow = 0;

    public static void main(String[] args) {
        wypiszMenu();
    }

    private static void wypiszMenu() {
        if (jestWlaczony) return;
        jestWlaczony = true;
        while (jestWlaczony) {
            wypiszOpcje();
            int wybor = askInt(1, OPCJE.length, "Wybierz czynnosc");

            switch(wybor) {
                case 1:
                    dodajStatek();
                    break;
                case 2:
                    zestrzelStatek();
                    break;
                case 3:
                    nieparzysteStatki();
                    break;
                case 4:
                    sumaStatkow50();
                    break;
                case 5:
                    jestWlaczony = false;
                    break;
                default:
                    System.out.println("Nieprawidlowy wybor");
                    break;
            }
        }
    }

    private static void sumaStatkow50() {
        int suma = 0;
        for (Statek pole : POLA) {
            if (pole instanceof Statek && pole.getPunkty() > 50) {
                suma += pole.getPunkty();
            }
        }

        System.out.println("Suma punktow statkow z punktami za trafienie > 50: " + suma);
    }

    private static void nieparzysteStatki() {
        List<Statek> statkiNieparzyste = Arrays.stream(POLA)
        .filter(pole -> pole instanceof Statek && pole.getId() % 2 == 1)
        .collect(Collectors.toList());

        if (statkiNieparzyste.isEmpty()) {
            System.out.println("Nie ma statkow spelniajacych kryteria!");
            return;
        }

        System.out.println("Statki z nieparzystym id:");
        for (Statek s : statkiNieparzyste) {
            System.out.println(s);
        }
    }

    private static void dodajStatek() {
        int id = askInt(0, Integer.MAX_VALUE, "Podaj id statku");
        String nazwa = askString("Podaj nazwe statku");
        int punkty = askInt(0, Integer.MAX_VALUE, "Podaj ilosc punktow za zestrzelenie statku");
        int miejsce = askInt(1, 8, "Podaj pole statku");
        Statek s = new Statek(id, nazwa, punkty);
        POLA[miejsce-1] = s;
        System.out.println("Dodano statek: " + s);
    }

    private static void zestrzelStatek() {
        int pole = askInt(1, 8, "Podaj pole");
        Statek s = POLA[pole-1];
        if (s instanceof Statek) {
            System.out.println("Trafiony " + s);
            System.out.println("Otrzymujesz " + s.getPunkty() + " punktow!");
            sumaPunktow += s.getPunkty();
            System.out.println("Twoja suma punktow wynosi: " + sumaPunktow);
            POLA[pole-1] = null;
        } else {
            System.out.println("Nie trafiles!");
        }
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

    private static String askString(String pytanie) {
        String x;
        while (true) {
            System.out.print(pytanie + ": ");
            x = in.nextLine();
            if (!x.isBlank()) break;
            System.out.println("Odpowiedz nie moze byc pusta!");
        }
        return x;
    }
}
