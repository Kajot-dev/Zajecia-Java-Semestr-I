import java.util.*;
import java.util.function.IntPredicate;

public class WykresB {

    //Tutaj przechowywane sa znaki, z ktorych sklada sie wykres, mozesz je pozmieniac

    private static final char WHITESPACE = ' '; 
    private static final char HORIZONTAL_LINE = '-';
    private static final char VERTICAL_LINE = '|';
    private static final char CORNER = '+';

    //Skaner, ktorego uzywan przez caly program
    private static final Scanner cin = new Scanner(System.in);


    /**
     * @param komunikat - Komuniakt, ktory jest wyswietlany jako prosba o podanie liczby
     * @param blednyKomunikat - Komunikat, ktory jest wyswietlany, kiedy liczba nie spelnia podanego przez nas warunku
     * @param assertion - Funkcja przyjmujaca jeden argument typu int i zwracajaca boolean (true/false). Sluzy do sprawdzenia czy liczba nam pasuje, np. sprawdza, czy liczba jest wieksza od 0 (zwrava true jezeli liczba jest ok)
     * @description Funkcja sluzy do pobrania liczby od uzytkownika. Umozliwia ustawienie wlasnych komunikatow oraz wlasnego warunku dla liczby
     */
    private static int getInt(String komunikat, String blednyKomunikat, IntPredicate assertion) {

        komunikat += ": ";

        //deklarujemy zmienna x, aby byla dostepna poza petla
        int x;

        //dopoki uzytkownik nie poda poprawnej liczby program bedzie pytac w nieskonczonosc
        while (true) {
            try {
                //wypisujemy komunikat
                System.out.print(komunikat);

                //probujemy pobrac liczbe od uzytkownika
                //Jezeli nie da sie przerobic tesktu na liczbe, wyrzucany jest wyjatek NumberFormatException, ktory jest lapany przez catch
                x = Integer.parseInt(cin.nextLine());

                //uruchamiam
                if (assertion.test(x)) break;
                else System.out.println(blednyKomunikat);
            } catch (NumberFormatException e) {
                System.out.println("Podano nieprawidlowa liczbe!");
            }
        }
        return x;
    }

    /**
     * @param wysokosci - Tablica wysokosci wszystki slupkow
     * @description Ta funkcja tworzy wykres dla podanych wysokosci slupkow i zwraca go w formace String
     */
    private static String wykres(int[] wysokosci) {

        //Tworzymy nowy obiekt StringBuilder
        StringBuilder sb = new StringBuilder();

        //Wybieramy najwyzszy slupek
        final int maxWys = Arrays.stream(wysokosci).max().getAsInt();

        //Tworzymy podstawe do uzywania (domyslnie) "+-+"
        final String podstawa = "" + CORNER + HORIZONTAL_LINE + CORNER; 
        final String srodek = "" + VERTICAL_LINE + WHITESPACE + VERTICAL_LINE;
        final String linia = "" + HORIZONTAL_LINE + HORIZONTAL_LINE;
        final String przerwa = "" + WHITESPACE + WHITESPACE;
        final String pustak = przerwa + WHITESPACE;
        // "" na poczatku jest po to, zeby Java potraktowala dodawanie jako tekst, a nie jako dodawanie liczb

        //Tworzymy pierwsza wartwe, zawsze jest taka sama
        sb.append(linia)
        .append(podstawa)
        .append(linia)
        .append(podstawa)
        .append(linia)
        .append(podstawa)
        .append(linia);

        for (int i = 0; i <= maxWys; i++) {
            sb.append('\n').append(przerwa);

            for (int w : wysokosci) {
                if (i < w) sb.append(srodek);
                else if (i == w && w != 0) sb.append(podstawa);
                else sb.append(pustak);
                sb.append(przerwa);
            }
        }

        return sb.toString();

    }

    public static void main(String[] args) {

        //wysokosci pierwszego i drugiego slupka
        //podajemy tutaj nasze warunki np i -> i > 0 co sprawdzi czy liczba jest wieksza od 0 etc.
        //Jezeli chcesz sie dowiedziec wiecej o tym zapisie ze strzalka zobacz "Java lambdas"
        final int a = getInt("Podaj wysokosc pierwszego slupka", "Liczba musi byc niemniejsza niz 0 i mniejsza od 9!", i -> i >= 0 && i < 9);
        final int b = getInt("Podaj wysokosc drugiego slupka", "Liczba musi byc niemniejsza niz 0, mniejsza niz 9 i rozna niz " + a + "!", i -> i >= 0 && i < 9 && i != a);

        //obliczam wysokosc ostatniego slupka
        final int c = Math.abs(a-b);

        //przygotowuje tablice z wysokosciami wszystkich slupkow
        final int[] tab = {a, b, c};
        
        //funkcja wykres zwraca gotowy wykres jako String
        final String w = wykres(tab);

        //wypisujemy wykres
        System.out.println(w);
    }
}
