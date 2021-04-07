import java.io.IOException;
import java.util.*;
import java.util.function.IntPredicate;

public class WykresA {

    //Tutaj przechowywane sa znaki, z ktorych sklada sie wykres, mozesz je pozmieniac

    private static final char WHITESPACE = ' '; 
    private static final char HORIZONTAL_LINE = '-';
    private static final char VERTICAL_LINE = '|';
    private static final char CORNER = '+';

    //Skaner, ktorego uzywan przez caly program
    private static final Scanner cin = new Scanner(System.in);

    /**
     * @param wysokosci - Tablica wysokosci wszystki slupkow
     * @description Ta funkcja tworzy wykres dla podanych wysokosci slupkow i zwraca go w formace String
     */
    private static String wykres(int[] wysokosci) {

        //Tworzymy nowy obiekt StringBuilder
        StringBuilder sb = new StringBuilder();

        //Tutaj generalnie wybieramy najwieksza wartosc (wysokosc) wenatrz tablicy dodajemy 2 ze wzgledu na rogi ('+')
        //Jest to zrobione za pomoca strumienia, ale rownie dobrze mozna zrobic for'a i znalezc najwieksza wartosc i dodac 2
        //Jest to odleglosc podlogi slupka od lewej
        final int maxOdstep = Arrays.stream(wysokosci).max().getAsInt() + 2; //+2 bo jeszcze rogi
        
        //jest to ilosc spacji jaka trzeba podstawic pomiedzy slupkami, aby podloga byla na wlasciwym miejscu
        final int odstepPomiedzy = maxOdstep - 1;

        //dodajemy spacje i gorny '|'
        multiAppend(sb, WHITESPACE, odstepPomiedzy);
        sb.append(VERTICAL_LINE);

        //dla kazdego slupka
        for (int i=0; i < wysokosci.length; i++) {

            //zaczynamy od nowej lini i wypisujemy slupek
            sb.append('\n').append(slupek(wysokosci[i], maxOdstep));

            //odstep pomiedzy slupkami, zaczynamy od nowej lini
            sb.append('\n');
            //dodajemy spacje i '|' pomiedzy slupkami (albo i po ostatnim, jezeli to ostatnie powtorzenie petli ;))
            multiAppend(sb, WHITESPACE, odstepPomiedzy);
            sb.append(VERTICAL_LINE);
        }
        return sb.toString(); //zwracamy gotowy wykres
    }

    /**
     * @param wys - wysokosc slupka, ktory bedzie wygenerowany, czyli ilosc '-'
     * @param maxOdstep - maksymalny odstep (wysokosc najwyzszego slupka w wykresie razem z "rogami"), przesuwa slupek na prawo, po to zeby wszystkie slupki w wykresie sie zmiescily. Inaczej odleglosc podlogi od lewej :)
     * @description Generuje pojedynczy slupek o podanej wysokosci i odleglosc jego "podlogi" od lewej jest rowna maksymalnemu odstepowi
     */
    private static String slupek(int wys, int maxOdstep) {

        //Tworzymy nowy StringBuilder
        final StringBuilder sb = new StringBuilder();

        //Obliczamy odstep od lewej (musimy odjac wysokosc slupa razem z rogami)
        final int odstep = maxOdstep - wys - 2;
        
        //pierwsza linia
        multiAppend(sb, WHITESPACE, odstep); //najpierw odstep od lewej
        sb.append(CORNER); //rog
        multiAppend(sb, HORIZONTAL_LINE, wys); //poziome linie
        sb.append(CORNER).append('\n'); //rog

        //druga linia
        multiAppend(sb, WHITESPACE, odstep); //najpierw odstep od lewej
        sb.append(VERTICAL_LINE); //daszek slupka
        multiAppend(sb, WHITESPACE, wys); //wnetrze slupka
        sb.append(VERTICAL_LINE).append('\n'); //dol slupka

        //trzecia linia
        multiAppend(sb, WHITESPACE, odstep); //najpierw odstep od lewej
        sb.append(CORNER); //rog
        multiAppend(sb, HORIZONTAL_LINE, wys); //poziome linie
        sb.append(CORNER); //rog

        return sb.toString(); //zwracamy gotowy slupek jako String
    }

    /**
     * @param sb - obiekt typu Appendable, czyli taki do, ktorego mozna cos "dolozyc" (ma metode append()), na przyklad StringBuilder
     * @param znak - znak, ktory bedzie dolozony do obiektu (w tym programie do StringBuildera)
     * @param ilosc - ile razy podany znak zostanie dolozony do StringBuildera
     * @description Funkcja doklada dany znak do StringBuildera (w tym programie) okreslona ilosc razy. Zrobione po to, zebym za kazdym razem kiedy chce dolozyc iles '-' nie musial pisac fora.
     */
    private static void multiAppend(Appendable sb, char znak, int ilosc) {
        try {
            //dodajemy znak do obiektu
            for (int i = 0; i < ilosc; i++) sb.append(znak);
        } catch (IOException e) {
            //w razie gdyby dodawanie nie powiodlo sie, aczkolwiek przy StringBuilderze raczej nie bedzie to mialo miejsca
            //generalnie nie trzeba sie tym przejmowac
            System.err.println("Wystapil blad przy dodawaniu znaku!");
            System.exit(1);
        } 
        //zwroccie uwage, ze funkcja nic nie zwraca, uzywamy jej podobnie jak Formattera na lekcji
    }

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

    public static void main(String[] args){

        //wysokosci pierwszego i trzeciego slupka
        //podajemy tutaj nasze warunki np i -> i > 0 co sprawdzi czy liczba jest wieksza od 0 etc.
        //Jezeli chcesz sie dowiedziec wiecej o tym zapisie ze strzalka zobacz "Java lambdas"
        final int a = getInt("Podaj wysokosc pierwszego slupka", "Liczba musi byc wieksza od 0 i mniejsza od 20!", i -> i > 0 && i < 20);
        final int c = getInt("Podaj wysokosc trzeciego slupka", "Liczba musi byc wieksza od 0, mniejsza niz 20 i rozna niz " + a + "!", i -> i > 0 && i < 20 && i != a);

        //obliczam wysokosc srodkowego slupka
        final int b = (int)Math.round((a + c)/2.0);

        //przygotowuje tablice z wysokosciami wszystkich slupkow
        final int[] tab = {a, b, c};
        
        //funkcja wykres zwraca gotowy wykres jako String
        final String w = wykres(tab);

        //wypisujemy wykres
        System.out.println(w);

    }
}
