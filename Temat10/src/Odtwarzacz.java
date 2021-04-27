import java.util.*;
import java.util.stream.Collectors;

public class Odtwarzacz {

    private static final Scanner cin = new Scanner(System.in);
    private final Playlista uno = new Playlista("Rap");
    private final Playlista dos = new Playlista("Metal");
    private static final  String[] MAIN_OPTS = new String[] { 
        "Wyswietl playliste", 
        "Dodaj nowy utwor", 
        "Przenies utwor",
        "Skopiuj utwor", 
        "Skasuj utwor", 
        "Wylacz odtwarzacz" 
    };
    private static final String EMPTY_PLAYLIST = "Ta playlista jest pusta!";

    public static void main(String[] args) {
        Odtwarzacz o = new Odtwarzacz();
        o.mainMenu();
    }

    public Odtwarzacz() {
        uno.addUtwory(
            new Utwor("Drunk", "TheLivingTombstone", 2020), 
            new Utwor("Paper", "2Scratch", 2019),
            new Utwor("Venom", "Eminem", 2018), 
            new Utwor("Demony", "Dziarma", 2019)
        );
        dos.addUtwory(
            new Utwor("The Moth Song", "House of Hatchets", 2019), 
            new Utwor("Run You", "The Quemists", 2016),
            new Utwor("Night of Fire", new String[] { "RichaadEB", "Caleb Hyles", "FamilyJules" }, 2015)
        );
    }

    private void mainMenu() {
        boolean doLoop = true;
        while (doLoop) {
            System.out.println("Wybierz opcje:");
            printOptions(Odtwarzacz.MAIN_OPTS);
            int choice = askInt(1, Odtwarzacz.MAIN_OPTS.length);
            Playlista p;
            int nr;
            Utwor u;
            switch (choice) {
                case 1:
                    p = choosePlaylista();
                    p.print();
                    break;
                case 2:
                    p = choosePlaylista();
                    final String tytul = askString("Podaj tytul utworu");
                    final String[] wykonawcy = askStrings("Podaj wykonawcow rodzielonych przecinkiem");
                    final int rok = askInt(0, 10000, "Podaj rok wykonania");
                    p.addUtwory(new Utwor(tytul, wykonawcy, rok));
                    break;
                case 3:
                    p = choosePlaylista();
                    if (p.size() == 0) {
                        System.out.println(Odtwarzacz.EMPTY_PLAYLIST);
                        break;
                    }
                    nr = askInt(1, p.size(), "Podaj nr utwory, ktory chcesz przeniesc");
                    u = p.getUtwor(nr);
                    p.removeUtwor(nr);
                    this.other(p).addUtwory(u);
                    break;
                case 4:
                    p = choosePlaylista();
                    if (p.size() == 0) {
                        System.out.println(Odtwarzacz.EMPTY_PLAYLIST);
                        break;
                    }
                    nr = askInt(1, p.size(), "Podaj nr utworu, ktory chcesz skopiowac");
                    u = p.getUtwor(nr);
                    this.other(p).addUtwory(u);
                    break;
                case 5:
                    p = choosePlaylista();
                    if (p.size() == 0) {
                        System.out.println(Odtwarzacz.EMPTY_PLAYLIST);
                        break;
                    }
                    nr = askInt(1, p.size(), "Podaj nr utworu, ktory chcesz usunac");
                    p.removeUtwor(nr);
                    break;
                case 6:
                    doLoop = false;
                    break;
                default:
                    break;
            }
            System.out.println();
        }
        System.exit(0);

    }

    private Playlista choosePlaylista() {
        System.out.println("Wybierz playliste:");
        printOptions(new String[] { uno.getNazwa(), dos.getNazwa() });
        int c = askInt(1, 2);
        if (c == 1)
            return uno;
        return dos;
    }

    private Playlista other(Playlista p) {
        if (p == uno)
            return dos;
        return uno;
    }

    private void printOptions(String[] options) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            sb.append("\t(").append(i + 1).append(") ").append(options[i]).append('\n');
        }
        System.out.print(sb.toString());
    }

    private static String[] askStrings(String k) {
        k += ": ";
        String[] x;
        while (true) {
            System.out.print(k);
            x = cin.nextLine().trim().split(",");
            x = Arrays.asList(x).stream().map(String::trim).collect(Collectors.toList()).toArray(x);
            if (x.length > 0)
                break;
            System.out.println("Nie mozna podac pustej odpowiedzi!");
        }
        return x;
    }

    private static String askString(String k) {
        k += ": ";
        String x;
        while (true) {
            System.out.print(k);
            x = cin.nextLine().trim();
            if (!x.isEmpty())
                break;
            System.out.println("Nie mozna podac pustej odpowiedzi!");
        }
        return x;
    }

    private static int askInt(int min, int max) {
        return Odtwarzacz.askInt(min, max, "?");
    }

    private static int askInt(int min, int max, String k) {
        k += ": ";
        int x;
        while (true) {
            try {
                System.out.print(k);
                x = Integer.parseInt(Odtwarzacz.cin.nextLine());
                if (x >= min && x <= max)
                    break;
            } catch (NumberFormatException e) {
                // do nothing
            }
            System.out.println("Podano nieprawidlowa liczbe, musi byc z zakresu " + min + '-' + max + '.');
        }
        return x;
    }
}
