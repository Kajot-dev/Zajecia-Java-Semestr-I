
import java.util.Arrays;

public class ZarzadzaniePlaylistami {
    public static void main(String[] args) {
        Playlista rock = new Playlista("Metal", Arrays.asList("Bfg Division", "Open Ocean", "Animal"));
        Playlista pop = new Playlista("Pop", Arrays.asList("Problem", "Dance", "Geronimo"));

        System.out.println("PIERWSZE WYPISANIE");
        rock.addUtwor("A Like Supreme");
        pop.addUtwor("Positions");

        rock.print();
        pop.print();

        System.out.println("POSORTOWANE");
        rock.sort();
        pop.sort();
        rock.print();
        pop.print();


        System.out.println("USUWANIE");
        pop.removeUtwor(1);
        pop.print();

        System.out.println("KOPIOWANIE");
        pop.addUtwor(rock.getUtwor(2));
        pop.print();

        System.out.println("POSORTOWANE 2");
        pop.sort();
        pop.print();
    }
}
