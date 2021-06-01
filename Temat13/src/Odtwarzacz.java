import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.file.Files;

public class Odtwarzacz {

    private static final Scanner cin = new Scanner(System.in);
    private final HashMap<String, Playlista> playlisty = new HashMap<>();
    private boolean jestWlaczony = false;
    private static final String[] MAIN_OPTS = new String[] { "Wyswietl playliste", "Utworz playliste", "Usun playliste",
            "Dodaj nowy utwor", "Przenies utwor", "Skopiuj utwor", "Skasuj utwor", "Posortuj playliste",
            "Zapisz playliste do pliku", "Wczytaj playliste z pliku", "Usun plik z playlista", "Wylacz odtwarzacz" 
        };
    private static final String[] SORT_OPTS = new String[] { "Posortuj po tytule", "Posortuj po wykonawcach",
            "Posortuj po roku wydania" };
    private static final String INTO_PLAYLIST = " do playlisty ";
    private static final String[] BOOL_ANS = new String[] { "TAK", "NIE" };
    private static final File SAVE_DIR = new File("zapisane_playlisty");

    public static void main(String[] args) {
        if (!SAVE_DIR.exists()) SAVE_DIR.mkdir();
        Odtwarzacz o = new Odtwarzacz();
        o.wlacz();
    }

    public Odtwarzacz() {
        this.dodajPlayliste(new Playlista("Rap", new Utwor("Drunk", "TheLivingTombstone", 2020),
                new Utwor("Paper", "2Scratch", 2019), new Utwor("Venom", "Eminem", 2018),
                new Utwor("Demony", "Dziarma", 2019)));
        this.dodajPlayliste(new Playlista("Metal", new Utwor("The Moth Song", "House of Hatchets", 2019),
                new Utwor("Run You", "The Quemists", 2016),
                new Utwor("Night of Fire", new String[] { "RichaadEB", "Caleb Hyles", "FamilyJules" }, 2015)));
    }

    public void wlacz() {
        if (!jestWlaczony)
            this.mainMenu();
    }

    private void mainMenu() {
        this.jestWlaczony = true;
        while (jestWlaczony) {
            System.out.println("Wybierz opcje:");
            printOptions(Odtwarzacz.MAIN_OPTS);
            int choice = askInt(1, Odtwarzacz.MAIN_OPTS.length);
            try {
                switch (choice) {
                    case 1: // wyswietl
                        this.wyswieltPlayliste();
                        break;
                    case 2: // dodaj playliste
                        this.dodajNowaPlayliste();
                        break;
                    case 3: // usun playliste
                        this.usunPlayliste();
                        break;
                    case 4: // dodaj utwor
                        this.dodajUtwor();
                        break;
                    case 5: // przenies utwor
                        this.przeniesUtwor();
                        break;
                    case 6: // skopiuj uwtor
                        this.skopiujUtwor();
                        break;
                    case 7: // usun utwor
                        this.usunUtwor();
                        break;
                    case 8:
                        this.posortujPlayliste();
                        break;
                    case 9: //zapisz do pliku
                        this.zapiszPlayliste();
                        break;
                    case 10:
                        this.wczytajPlayliste();
                        break;
                    case 11:
                        this.usunPlikPlaylisty();
                        break;
                    case 12:
                        jestWlaczony = false;
                        break;
                    default:
                        break;
                }
            } catch (NoPlaylistException|UserAbortedException|EmptyPlaylistException e) {
                //do nothing
            }
            System.out.println();
        }
        System.exit(0);

    }

    // funkcje z CLI

    private void wyswieltPlayliste() throws NoPlaylistException, UserAbortedException {
        Playlista p1 = this.wybierzPlayliste();
        p1.print();
    }

    private void dodajNowaPlayliste() {
        final String nazwa = askString("Podaj nazwe nowej playlisty");
        this.dodajPlayliste(new Playlista(nazwa));
    }

    private void usunPlayliste() throws NoPlaylistException, UserAbortedException {
        Playlista p1 = this.wybierzPlayliste();
        final String nazwaPlaylisty = p1.getNazwa();
        this.playlisty.remove(nazwaPlaylisty);
        Odtwarzacz.printGreen("Usunieto playliste " + nazwaPlaylisty + "!");
    }

    private void dodajUtwor() throws NoPlaylistException, UserAbortedException {
        Playlista p1 = wybierzPlayliste();
        final String tytul = askString("Podaj tytul utworu");
        final String[] wykonawcy = askStrings("Podaj wykonawcow rodzielonych przecinkiem");
        final int rok = askInt(0, 10000, "Podaj rok wykonania");
        try {
            Utwor u = new Utwor(tytul, wykonawcy, rok);
            p1.addUtwory(u);
            Odtwarzacz.printGreen("Dodano utwor " + u.toString() + INTO_PLAYLIST + p1.getNazwa());
        } catch (IllegalArgumentException e) {
            Odtwarzacz.printYellow(e.getMessage());
        }
    }

    private void przeniesUtwor() throws NoPlaylistException, UserAbortedException, EmptyPlaylistException {
        System.out.println("Podaj, z ktorej playlisty chcesz przeniesc");
        Playlista p1 = this.wybierzPlayliste();
        int nrUtworu = p1.wybierzIndeksUtworu();
        Utwor u = p1.getUtwor(nrUtworu);
        System.out.println("Podaj playliste docelowa");
        Playlista p2 = this.wybierzPlayliste(p1);
        p1.removeUtwor(nrUtworu);
        p2.addUtwory(u);
        Odtwarzacz.printGreen("Przeniesiono utwor " + u.toString() + " z playlisty " + p1.getNazwa() + INTO_PLAYLIST
                + p2.getNazwa() + "!");
    }

    private void skopiujUtwor() throws NoPlaylistException, UserAbortedException, EmptyPlaylistException {
        System.out.println("Podaj, z ktorej playlisty chcesz skopiowac");
        Playlista p1 = wybierzPlayliste();
        int nr = p1.wybierzIndeksUtworu();
        Utwor u = p1.getUtwor(nr);
        System.out.println("Podaj playliste docelowa");
        Playlista p2 = this.wybierzPlayliste(p1);
        p2.addUtwory(u);
        Odtwarzacz.printGreen("Skopiowano utwor " + u.toString() + " z playlisty " + p1.getNazwa() + INTO_PLAYLIST
        + p2.getNazwa() + "!");
    }


    private void usunUtwor() throws NoPlaylistException, UserAbortedException, EmptyPlaylistException {
        Playlista p1 = wybierzPlayliste();
        int nr = p1.wybierzIndeksUtworu();
        Utwor u = p1.getUtwor(nr);
        p1.removeUtwor(nr);
        Odtwarzacz.printGreen("Usunieto utwor " + u.toString());
    }

    private void posortujPlayliste() throws NoPlaylistException, UserAbortedException {
        Playlista p1 = wybierzPlayliste();
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
        Odtwarzacz.printGreen("Posortowano playliste " + p1.getNazwa());
    }

    private void dodajPlayliste(Playlista p) {
        String nazwa = p.getNazwa();
        if (this.playlisty.containsKey(nazwa)) {
            Odtwarzacz.printYellow("Nie może być dwóch playlist o tej samej nazwie!");
        } else {
            this.playlisty.put(nazwa, p);
            Odtwarzacz.printGreen("Utworzono playliste " + nazwa + "!");
        }
            
    }

    private void zapiszPlayliste() throws NoPlaylistException, UserAbortedException {
        Playlista p1 = this.wybierzPlayliste();
        File plikZapisu = new File(SAVE_DIR , p1.getNazwa()+".csv");
        if (!plikZapisu.exists() || askBoolean("Na pewno chcesz zapisac? Jest juz zapisana playlista o takiej samej nazwie")) {
            try (
                FileWriter writer = new FileWriter(plikZapisu);
                BufferedWriter zapis = new BufferedWriter(writer);
            ) {
                p1.serialise(zapis);
                Odtwarzacz.printGreen("Zapisanie playlisty powiodlo sie!");
            } catch (IOException e) {
                Odtwarzacz.printYellow("Operacja zapisu nie powiodla sie!");
            }
        } else {
            throw new UserAbortedException();
        }

    }

    private void wczytajPlayliste() throws UserAbortedException, NoPlaylistException {
        File[] acceptableFiles = SAVE_DIR.listFiles(f -> !f.isDirectory() && f.getName().toUpperCase().endsWith(".CSV"));
        if (acceptableFiles.length == 0) throw new NoPlaylistException("Brak playlist do wczytania, najpierw zapisz jakas!");
        String[] fileNames = new String[acceptableFiles.length];
        Arrays.asList(acceptableFiles).stream().map(f -> f.getName().substring(0, f.getName().length() - 4)).collect(Collectors.toList()).toArray(fileNames);
        System.out.println("Wybierz playliste, ktora chcesz wczytac:");
        Odtwarzacz.printOptions(true, fileNames);
        final int choice = Odtwarzacz.askInt(1, fileNames.length + 1);
        if (choice == fileNames.length + 1) throw new UserAbortedException();
        if (!this.playlisty.containsKey(fileNames[choice - 1]) || askBoolean("Istnieje juz playlista o takiej samej nazwie. Czy chcesz ja nadpisac?")) {
            try {
                this.playlisty.put(fileNames[choice - 1], new Playlista(fileNames[choice - 1], acceptableFiles[choice - 1]));
                Odtwarzacz.printGreen("Wczytanie playlisty powiodlo sie!");
            } catch (IOException e) {
                throw new NoPlaylistException("Blednie zapisana playlista!");
            }
        } else throw new UserAbortedException();
    }

    private void usunPlikPlaylisty() throws UserAbortedException, NoPlaylistException {
        File[] acceptableFiles = SAVE_DIR.listFiles(f -> !f.isDirectory() && f.getName().toUpperCase().endsWith(".CSV"));
        if (acceptableFiles.length == 0) throw new NoPlaylistException("Brak playlisty do usuniecia, najpierw zapisz jakas!");
        String[] fileNames = new String[acceptableFiles.length];
        Arrays.asList(acceptableFiles).stream().map(f -> f.getName().substring(0, f.getName().length() - 4)).collect(Collectors.toList()).toArray(fileNames);
        System.out.println("Wybierz plik playlisty, ktora chcesz usunac:");
        Odtwarzacz.printOptions(true, fileNames);
        final int choice = Odtwarzacz.askInt(1, fileNames.length + 1);
        if (choice == fileNames.length + 1) throw new UserAbortedException();
        try {
            Files.delete(acceptableFiles[choice - 1].toPath());
            Odtwarzacz.printGreen("Plik usuniety pomyslnie!");
        } catch (IOException e) {
            Odtwarzacz.printYellow("Nie udalo sie usunac pliku.");
        }
    }

    //Narzedzia

    private Playlista wybierzPlayliste(Playlista... exceptions) throws NoPlaylistException, UserAbortedException {
        List<Playlista> opts = new ArrayList<>(this.playlisty.values()); // kopiujemy
        for (Playlista p : exceptions)
            opts.remove(p);
        if (opts.isEmpty())
            throw new NoPlaylistException(); 
        String[] nameArr = new String[opts.size()];
        opts.stream().map(Playlista::getNazwa).collect(Collectors.toList()).toArray(nameArr);
        System.out.println("Wybierz playliste:");
        printOptions(true, nameArr);
        final int c = askInt(1, nameArr.length + 1);
        if (c == nameArr.length + 1) throw new UserAbortedException();
        return this.playlisty.get(nameArr[c - 1]);
    }

    protected static void printOptions(String[] options) {
        printOptions(false, options);
    }

    protected static void printOptions(boolean cancel, String[] options) {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < options.length; i++) {
            sb.append("\t(").append(i + 1).append(") ").append(options[i]).append('\n');
        }
        if (cancel) sb.append("\t(").append(i + 1).append(") ").append("\u001B[35mANULUJ\u001B[0m").append('\n');
        System.out.print(sb.toString());
    }

    protected static String[] askStrings(String k) {
        k += ": ";
        String[] x;
        while (true) {
            System.out.print(k);
            x = cin.nextLine().trim().split(",");
            List<String> temp = Arrays.asList(x);
            if (temp.stream().noneMatch(String::isBlank)) {
                x = temp.stream().map(String::trim).collect(Collectors.toList()).toArray(x);
                if (x.length > 0)
                    break;
            }
            Odtwarzacz.printYellow("Zadna z podanych odpowiedzi nie moze byc pusta!");
        }
        return x;
    }

    protected static String askString(String k) {
        k += ": ";
        String x;
        while (true) {
            System.out.print(k);
            x = cin.nextLine().trim();
            if (!x.isEmpty())
                break;
            Odtwarzacz.printYellow("Nie mozna podac pustej odpowiedzi!");
        }
        return x;
    }

    protected static boolean askBoolean(String k) {
        k += " (tak/nie): ";
        String x;
        while (true) {
            System.out.print(k);
            x = cin.nextLine().trim().toUpperCase();
            if (BOOL_ANS[0].startsWith(x)) return true;
            else if (BOOL_ANS[1].startsWith(x)) return false;
            Odtwarzacz.printYellow("Musisz wybrac odpowiedz tak/nie");
        }
    }

    protected static int askInt(int min, int max) {
        return Odtwarzacz.askInt(min, max, "?");
    }

    protected static int askInt(int min, int max, String k) {
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
            Odtwarzacz.printYellow("Podano nieprawidlowa liczbe, musi byc z zakresu " + min + '-' + max + '.');
        }
        return x;
    }

    protected static void printYellow(Object o) {
        System.out.println("\u001B[33m" + o + "\u001B[0m");
    }

    protected static void printGreen(Object o) {
        System.out.println("\u001B[32m" + o + "\u001B[0m");
    }
}
