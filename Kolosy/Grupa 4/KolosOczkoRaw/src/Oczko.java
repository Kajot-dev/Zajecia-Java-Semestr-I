import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Oczko {

    private static final int OCZKO = 21;

    private final Random random = new Random();
    private static final Scanner inScanner = new Scanner(System.in);
    private final FileWriter zapisPliku;
    private final String name;
    List<Integer> rzuty = new LinkedList<>();

    //bound is INCLUSIVE
    private int throwDice(int bound) {
        int rzut = random.nextInt(bound) + 1;
        this.rzuty.add(rzut);
        return rzut;
    }

    public Oczko() throws IOException {
        this.name = askString("Podaj swoj pseudonim");
        this.zapisPliku = new FileWriter(this.name + ".txt");
    }

    private void playGame() {

        //pierwszy rzut koscmi
        throwDice(12);
        System.out.println("Wylosowano: " + this.rzuty.get(this.rzuty.size() - 1));
        this.writeLastThrow();


        while (true) {
            //dopoki gra sie nie skonczy graj
            if (!doRound()) break;
        }
        if (this.zapisPliku != null) {
            try {
                this.zapisPliku.close();
            } catch (IOException e) {
                System.err.println("Nie udalo sie zamknac pliku!");
                e.printStackTrace();
            }
        }
    }

    private boolean doRound() {
        //UWAGA
        //Przesunalem zapisywanie do pliku po losowaniu, zeby w pliku bylo widac (w pliku) ostatni rzut w przypadku oczka lub porazki!
        //Teraz zapis do pliku dzieje sie bezporsrednio przed sprawdzeniem oczka/porazki, zeby zawsze bylo widac (w pliku) ostatni rzut
        boolean ans;
        while (true) {
            try {
                ans = askIfContinue();
                break;
            } catch (IllegaAnswerException e) {
                System.out.println(e.getMessage());
            }
        }
        if (ans) {
            throwDice(12); //rzut kostka
            System.out.println("Wylosowano: " + this.rzuty.get(this.rzuty.size() - 1));
            System.out.println("Wszystkie rzuty: " + this.rzuty.toString());
            int suma = this.rzuty.stream().mapToInt(Integer::intValue).sum();
            System.out.println("Suma: " + suma);
            //TUTAJ WRZUCILEM ZAPIS DO PLIKU
            this.writeToFile(String.format("%s. rzut \t [%s] suma: %s%n", this.rzuty.size(), this.rzuty.get(this.rzuty.size() - 1), this.rzuty.stream().mapToInt(Integer::intValue).sum()));
            //sprawdzenie wygranej
            if (suma == OCZKO) {
                System.out.println("OCZKO!");
                writeToFile("OCZKO\n");
                return false;
            } else if (suma >  OCZKO) {
                System.out.println("PORAÅ»KA!\n");
                writeToFile("PORA¯KA\n");
                return false;
            }
            return true;
        } else {
            System.out.println("SPASOWANO");
            this.writeToFile("PAS\n");
            try {
                this.zapisPliku.close();
                return false;
            } catch (IOException e) {
                System.err.println("Nie udalo sie zamknac pliku!");
                e.printStackTrace();
            }
        }
        return true;
    }

    private void writeToFile(String s) {
        try {
            this.zapisPliku.write(s);
        } catch (IOException e) {
            System.err.println("Nie udalo sie zapisac do pliku!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void writeLastThrow() {
        this.writeToFile(String.format("%s. rzut \t [%s] suma: %s%n", this.rzuty.size(), this.rzuty.get(this.rzuty.size() - 1), this.rzuty.stream().mapToInt(Integer::intValue).sum()));
    }

    private static String askString(String message) {
        message += ": ";
        String temp;
        while (true) {
            System.out.print(message);
            temp = inScanner.nextLine();
            if (!temp.isBlank()) break;
            System.out.println("Odpowiedz nie moze byc pusta!");
        }
        return temp;
    }

    private static boolean askIfContinue() throws IllegaAnswerException {
        System.out.print("Czy chcesz graæ dalej? (t/n): ");
        String temp = inScanner.nextLine().trim();
        if (temp.equals("n")) return false;
        if (temp.equals("t")) return true;
        throw new IllegaAnswerException("Wprowadzono nieprawidÅ‚owe dane");

    }

    public static void main(String[] args) {
        System.out.println("UWAGA! Przusnalem zapis do pliku (przy 2 i pozniejszej iteracji) z punktu 3 do \"Przed sprawdzeniem oczko/porazka\", poniewaz porazka/oczko powodowala niewidocznosc ostatniego rzutu (program nie wracal do punktu nr 3)");
        try {
            Oczko gra = new Oczko();
            gra.playGame();
        } catch (IOException e) {
            System.err.println("Nie udalo sie utworzyc pliku!");
            e.printStackTrace();
        }

    }


}
