import java.util.*;

public class TestListy {
    private static final List<String> slowa = new LinkedList<>();
    public static void main(String[] args) {
        slowa.add("test1");
        slowa.add("test2");
        slowa.add("test3");
        printSlowa();
        slowa.add(1, "nowy");
        printSlowa();
        //slowa.add(5, "problem");
        /**
         * To nie dziala, poniewaz lista nie ma min 5 pozycji
         */
        slowa.remove(2);
        printSlowa();
        //slowa.remove(5);
        /**
         * To nie dziala, poniewaz nie mozna usunac czego, co nie istnieje
         */
        final String[] slowaWTablicy = new String[] {"Jeden", "Dwa", "Trzy", "Cztery"};
        slowa.addAll(Arrays.asList(slowaWTablicy));
        printSlowa();
        slowa.addAll(0, Arrays.asList(slowaWTablicy));
        printSlowa();
        slowa.set(2, "Piec"); //jako 5
        printSlowa();
        Collections.sort(slowa);
        printSlowa();
        Collections.reverse(slowa); //juz byla posortowana
        printSlowa();
        Collections.shuffle(slowa);
        printSlowa();
        System.out.println("\"Jeden\" jest na pozycji: " + slowa.indexOf("Jeden"));
        Collections.replaceAll(slowa, "Dwa", "2");
        printSlowa();
        slowa.clear();
        printSlowa();
    }

    private static void printSlowa() {
        System.out.println("Slowa: " + slowa);
    }
}
