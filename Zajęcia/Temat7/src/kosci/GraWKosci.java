package kosci;

import java.util.*;
import java.util.stream.Collectors;

public class GraWKosci {

    private static final Scanner cin = new Scanner(System.in);

    private static List<Integer> getInts(String komunikat) { // cała ta funkcja pozwala wpisać liczby przez użytkownika
        komunikat += ": ";
        List<Integer> nums = new ArrayList<>();
        while (true) {
            try {
                System.out.print(komunikat);
                nums.addAll(Arrays.asList(cin.nextLine().split(" ")).stream().mapToInt(Integer::parseInt).boxed()
                        .collect(Collectors.toList()));
                if (!nums.isEmpty())
                    break;
            } catch (NumberFormatException e) {
                nums.clear();
            }
            System.out.println("Podano niepoprawne liczby!");
        }
        return nums;
    }

    private static int getInt(String komunikat) {
        komunikat += ": ";
        int x;
        while (true) {
            System.out.print(komunikat);
            try {
                x = Integer.parseInt(cin.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidłową liczbę!");
            }
        }
        return x;
    }

    private static String getString(String komunikat) {
        komunikat += ": ";
        String x;
        while (true) {
            System.out.print(komunikat);
            x = cin.nextLine();
            if (!x.trim().equals(""))
                break;
            System.out.println("Musisz cos podac!");
        }
        return x;
    }

    public static void main(String[] args) {
        final int liczbaGraczy = getInt("Podaj liczbe graczy");

        final Gracz[] gracze = new Gracz[liczbaGraczy];

        for (int i = 0; i < liczbaGraczy; i++) {
            final String imie = getString("Podaj imie gracza nr " + (i + 1));
            gracze[i] = new Gracz(imie);
        }

        final List<Integer> kosci = getInts("Podaj liczby oczek na kazdej kosci oddzielone spacja");

        final int tury = getInt("Podaj liczbe tur");

        Gra gra1 = new Gra(gracze, kosci, tury);

        final int opoznienie = getInt("Podaj opoznienie pomiedzy rzutami w milisekunach");

        // opcjonalnie mozna podac opoznienie miedzy rzutami
        gra1.graj(opoznienie);

        // ---RZECZY OPCJONALNE NAWET WSROD OPCJONALNYCH--- (nie bylo takiego polecenia)

        if (Gra.SAVE_THROWS) {
            // Kto wykonal najwiecej maksymalnych rzutow kostka

            int[] perfekcyjneRzutyKoscia = gra1.countForEachPlayerDiceInThrows(rzut -> {
                List<Integer> wyniki = rzut.wyniki();
                int suma = 0;
                for (int i = 0; i < kosci.size(); i++) {
                    if (wyniki.get(i).equals(kosci.get(i)))
                        suma++;
                }
                return suma;
            });

            int indeksPerfRzutu = GraWKosci.findMaxPositiveIndex(perfekcyjneRzutyKoscia);

            System.out.println("Najwiecej maksymalnych rzutow pojedyncza kostka ("
                    + perfekcyjneRzutyKoscia[indeksPerfRzutu] + ") wykonal(a): " + gracze[indeksPerfRzutu].getName());

            // Kto wykonal maksymalna ilosc rzutow, gdzie wszystkie kosci mialy maksymalna
            // wartosc

            int[] perfekcyjneRzutyWszystkim = gra1.countForEachPlayerThrows(rzut -> {
                List<Integer> wyniki = rzut.wyniki();
                for (int i = 0; i < kosci.size(); i++) {
                    if (!wyniki.get(i).equals(kosci.get(i)))
                        return false;
                }
                return true;
            });

            int indeksPerfRzutowCalych = GraWKosci.findMaxPositiveIndex(perfekcyjneRzutyWszystkim);

            if (perfekcyjneRzutyWszystkim[indeksPerfRzutowCalych] == 0) {
                System.out.println("Nikt nie wykonal idealnego rzutu wszystkimi koscmi!");
            } else {
                System.out.println("Najwiecej IDEALNYCH rzutow wszystkimi koscmi ("
                        + perfekcyjneRzutyWszystkim[indeksPerfRzutowCalych] + ") wykonal(a): "
                        + gracze[indeksPerfRzutowCalych].getName());
            }

            System.out.println();
        } else System.out.println("Funkcja zapisywania rzutow w pamieci jest wylaczana. Wlacz ja zmieniajac wartosc pola Gra.SAVE_THROWS, aby zobaczyc dodatkowe statystyki (mocno zwieksza zuzycie pamieci)");

    }

    private static int findMaxPositiveIndex(int[] tab) {
        int maxIndex = 0;
        int maxVal = 0;
        for (int i = 0; i < tab.length; i++) {
            if (maxVal <= tab[i]) {
                maxVal = tab[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
