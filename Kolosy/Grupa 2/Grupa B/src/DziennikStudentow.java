import java.util.*;

public class DziennikStudentow {
    private static List<Student> listaStudentow = new LinkedList<>();
    private static boolean dziala = false;
    private static final String[] MENU = new String[] {
        "PRZYJMIJ STUDENTA",
        "WYPISZ STUDENTOW",
        "OBLICZ SREDNIA",
        "SKRESL STUDENTA",
        "ZAKONCZ"
    };
    private static Scanner scIn = new Scanner(System.in);
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        if (dziala) return;
        dziala = true;
        while(dziala) {
            wyswietlMenu(MENU);
            int wybor = askInt(1, MENU.length, "Wybierz");
            switch(wybor) {
                case 1:
                    przymijStudenta();
                    break;
                case 2:
                    wypiszListeStudentow();
                    break;
                case 3:
                    wypiszSredniaStudnetow();
                    break;
                case 4:
                    skreslStudenta();
                    break;
                case 5:
                    System.out.println("Koncze program...");
                    dziala = false;
                    break;
                default:
                    System.out.println("Nieprawidlowy wybor");
                    break;
            }
        }
    }

    private static void skreslStudenta() {
        int album = askInt(0, Integer.MAX_VALUE, "Podaj nr albumu studenta");
        for (Student uczen : listaStudentow) {
            if (uczen.getNumerAlbumu() == album) {
                listaStudentow.remove(uczen);
                System.out.println("Skreslono studenta: " + uczen);
                return;
            }
        }
        System.out.println("Nie znaleziono studenta z takim numerem albumu");
    }

    private static void wypiszSredniaStudnetow() {
        if (listaStudentow.isEmpty()) {
            System.out.println("Nie ma zadnych studnetow!");
        }
        double sum = 0d;
        for (Student s : listaStudentow) {
            sum += s.getSrednia();
        }
        System.out.println("Srednia ocen studnetow wynosi: " + (sum/listaStudentow.size()));
    }

    private static void przymijStudenta() {
        String nazwisko = askString("Podaj nazwisko studenta");
        int nr = askInt(0, Integer.MAX_VALUE, "Podaj nr albumu");
        double srednia = askDouble(0, 5, "Podaj srednia ocen");
        Student s = new Student(nazwisko, nr, srednia);
        listaStudentow.add(s);
        System.out.println("Przyjeto studenta: " + s);
    }

    private static void wypiszListeStudentow() {
        if (listaStudentow.isEmpty()) {
            System.out.println("Nie ma zadnych studentow");
            return;
        }
        for (int i = 0; i < listaStudentow.size(); i++) {
            System.out.println((i+1) + ". " + listaStudentow.get(i));
        }
    }

    private static void wyswietlMenu(String[] opcje) {
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
                x = Integer.parseInt(scIn.nextLine());
                if (x >= min && x <= max) break;
                System.out.println("Podana liczba musi byc niemniejsza niz " + min + " i niewieksza niz " + max + "!");
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidlowy numer!");
            }
        }
        return x;
    }

    private static double askDouble(double min, double max, String message) {
        double x;
        while (true) {
            try {
                System.out.print(message + ": ");
                x = Double.parseDouble(scIn.nextLine());
                if (x >= min && x <= max) break;
                System.out.println("Podana liczba musi byc niemniejsza niz " + min + " i niewieksza niz " + max + "!");
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
            x = scIn.nextLine();
            if (!x.isBlank()) break;
            System.out.println("Odpowiedz nie moze byc pusta!");
        }
        return x;
    }
}
