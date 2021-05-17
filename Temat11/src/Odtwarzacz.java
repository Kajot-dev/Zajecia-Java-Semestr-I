import java.util.*;
import java.util.stream.Collectors;

public class Odtwarzacz {

    private static final Scanner cin = new Scanner(System.in);
    private final HashMap<String, Playlista> playlisty = new HashMap<>();
    private boolean jestWlaczony = false;
    private static final  String[] MAIN_OPTS = new String[] { 
        "Wyswietl playliste", 
        "Utworz playliste",
        "Usun playliste",
        "Dodaj nowy utwor", 
        "Przenies utwor",
        "Skopiuj utwor", 
        "Skasuj utwor", 
        "Posortuj playliste",
        "Wylacz odtwarzacz" 
    };
    private static final String[] SORT_OPTS = new String[] {
        "Posortuj po tytule",
        "Posortuj po wykonawcach",
        "Posortuj po roku wydania"
    };
    private static final String EMPTY_PLAYLIST = "Ta playlista jest pusta!";
    private static final String NO_LISTS = "Nie ma juz zadnych playlist, sprobuj jakas dodac!";

    public static void main(String[] args) {
        Odtwarzacz o = new Odtwarzacz();
        o.wlacz();
    }


    public Odtwarzacz() {
        this.dodajPlayliste(new Playlista(
            "Rap",
            new Utwor("Drunk", "TheLivingTombstone", 2020), 
            new Utwor("Paper", "2Scratch", 2019),
            new Utwor("Venom", "Eminem", 2018), 
            new Utwor("Demony", "Dziarma", 2019)
        ));
        this.dodajPlayliste(new Playlista(
            "Metal",
            new Utwor("The Moth Song", "House of Hatchets", 2019), 
            new Utwor("Run You", "The Quemists", 2016),
            new Utwor("Night of Fire", new String[] { "RichaadEB", "Caleb Hyles", "FamilyJules" }, 2015)
        ));
    }

    
    public void wlacz() {
        if (!jestWlaczony) this.mainMenu();
    }

    private void mainMenu() {
        this.jestWlaczony = true;
        while (jestWlaczony) {
            System.out.println("Wybierz opcje:");
            printOptions(Odtwarzacz.MAIN_OPTS);
            int choice = askInt(1, Odtwarzacz.MAIN_OPTS.length);
            Playlista p1;
            Playlista p2;
            int nr;
            Utwor u;
            switch (choice) {
                case 1: //wyswietl
                    p1 = this.choosePlaylista();
                    if (!this.sprawdz(p1)) break; 
                    p1.print();
                    break;
                case 2: //dodaj playliste
                    String nazwa = askString("Podaj nazwe nowej playlisty");
                    this.dodajPlayliste(new Playlista(nazwa));
                    break;
                case 3: //usun playliste
                    p1 = this.choosePlaylista();
                    if (!this.sprawdz(p1)) break;
                    this.playlisty.remove(p1.getNazwa());
                    break;
                case 4: //dodaj utwor
                    p1 = choosePlaylista();
                    if (!this.sprawdz(p1)) break;
                    final String tytul = askString("Podaj tytul utworu");
                    final String[] wykonawcy = askStrings("Podaj wykonawcow rodzielonych przecinkiem");
                    final int rok = askInt(0, 10000, "Podaj rok wykonania");
                    p1.addUtwory(new Utwor(tytul, wykonawcy, rok));
                    break;
                case 5:  //przenies utwor
                    System.out.println("Podaj, z ktorej playlisty chcesz przeniesc");
                    p1 = choosePlaylista();
                    if (!this.sprawdz(p1)) break;
                    if (p1.size() == 0) {
                        System.out.println(Odtwarzacz.EMPTY_PLAYLIST);
                        break;
                    }
                    nr = askInt(1, p1.size(), "Podaj nr utwory, ktory chcesz przeniesc");
                    u = p1.getUtwor(nr);
                    System.out.println("Podaj playliste docelowa");
                    p2 = this.choosePlaylista(p1);
                    if (!this.sprawdz(p2)) break;
                    p1.removeUtwor(nr);
                    p2.addUtwory(u);
                    break;
                case 6: //skopiuj uwtor
                    System.out.println("Podaj, z ktorej playlisty chcesz skopiowac");
                    p1 = choosePlaylista();
                    if (!this.sprawdz(p1)) break;
                    if (p1.size() == 0) {
                        System.out.println(Odtwarzacz.EMPTY_PLAYLIST);
                        break;
                    }
                    nr = askInt(1, p1.size(), "Podaj nr utworu, ktory chcesz skopiowac");
                    u = p1.getUtwor(nr);
                    System.out.println("Podaj playliste docelowa");
                    p2 = this.choosePlaylista(p1);
                    if (!this.sprawdz(p2)) break;
                    p2.addUtwory(u);
                    break;
                case 7: //usun utwor
                    p1 = choosePlaylista();
                    if (!this.sprawdz(p1)) break;
                    if (p1.size() == 0) {
                        System.out.println(Odtwarzacz.EMPTY_PLAYLIST);
                        break;
                    }
                    nr = askInt(1, p1.size(), "Podaj nr utworu, ktory chcesz usunac");
                    p1.removeUtwor(nr);
                    break;
                case 8:
                    p1 = choosePlaylista();
                    if (!this.sprawdz(p1)) break;
                    System.out.println("Wybierz opcje:");
                    printOptions(Odtwarzacz.SORT_OPTS);
                    int c2 = askInt(1, Odtwarzacz.SORT_OPTS.length);
                    switch (c2) {
                        case 1:
                            p1.sortujPoTytule();
                            break;
                        case 2:
                            p1.sortujPoWykonawcy();
                            break;
                        case 3:
                            p1.sortujPoRoku();
                            break;
                        default:
                            break;
                    }
                    break;
                case 9:
                    jestWlaczony = false;
                    break;
                default:
                    break;
            }
            System.out.println();
        }
        System.exit(0);

    }

    public void dodajPlayliste(Playlista p) {
        if (this.playlisty.containsKey(p.getNazwa())) {
            System.out.println("Nie może być dwóch playlist o tej samej nazwie!");
        } else this.playlisty.put(p.getNazwa(), p);
    }

    public void usunPlayliste(Playlista p) {
        String key = p.getNazwa();
        this.playlisty.remove(key);
    }

    private boolean sprawdz(Playlista p) {
        if (p == null) {
            System.out.println(Odtwarzacz.NO_LISTS);
            return false;
        }
        return true;
    }

    private Playlista choosePlaylista(Playlista... exceptions) {
        List<Playlista> opts = new ArrayList<>(this.playlisty.values()); //kopiujemy
        for (Playlista p : exceptions) opts.remove(p);
        if (opts.isEmpty()) return null;
        String[] nameArr = new String[opts.size()];
        opts.stream().map(Playlista::getNazwa).collect(Collectors.toList()).toArray(nameArr);
        System.out.println("Wybierz playliste:");
        printOptions(nameArr);
        final int c = askInt(1, nameArr.length);
        return this.playlisty.get(nameArr[c-1]);
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
