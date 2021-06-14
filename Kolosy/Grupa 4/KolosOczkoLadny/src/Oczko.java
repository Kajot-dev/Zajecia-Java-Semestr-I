import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Oczko {

    private static final Scanner inScanner= new Scanner(System.in);
    private static final int DICE_SIZE = 12;
    private static final int TARGET_NUM = 21;

    private final RollList rollList = new RollList();
    private final FileWriter saveFile;

    public Oczko() throws IOException {
        final String name = Oczko.askName();
        this.saveFile = new FileWriter(name + ".txt");
    }

    public static void main(String[] args) {
        try {
            var game = new Oczko();
            game.play();
        } catch (IOException e) {
            System.err.println("Nie udalo sie utworzyc pliku!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void play() {
        while (true) {
            this.rollList.throwDice(DICE_SIZE);
            this.rollList.printState();
            try {
                this.rollList.writeLastThrow(this.saveFile);
            } catch (IOException e) {
                System.err.println("Nie udalo sie zapisac rzutu!");
                e.printStackTrace();
                System.exit(-1);
            }

            int rollSum = this.rollList.sum();

            if (rollSum == TARGET_NUM) {
                System.out.println("OCZKO!");
                this.writeToFile("OCZKO");
                break;
            } else if (rollSum > TARGET_NUM) {
                System.out.println("PORAZKA!");
                this.writeToFile("PORAZKA");
                break;
            }

            boolean ans;

            while (true) {
                try {
                    ans = askIfContinue();
                    break;
                } catch (WrongAnswerException e) {
                    System.err.println(e.getMessage());
                }
            }

            if (!ans) {
                System.out.println("SPASOWANO!");
                this.writeToFile("PAS");
                break;
            }

        }
        this.afterFinish();
    }

    private void afterFinish() {
        try {
            if (this.saveFile != null) this.saveFile.close();
        } catch (IOException e) {
            System.err.println("Nie udalo sie zamknac pliku");
            e.printStackTrace();
        }

    }

    private void writeToFile(String s) {
        try {
            this.saveFile.write(s + '\n');
        } catch (IOException e) {
            System.err.println("Nie udalo sie zapisac do pliku!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static boolean askIfContinue() throws WrongAnswerException {
        System.out.print("Czy chcesz kontynuowac (t/n)?: ");
        final String temp = inScanner.nextLine().trim();

        if (temp.equals("n")) return false;
        if (temp.equals("t")) return true;
        throw new WrongAnswerException("Podano nieprawidlowa odpowiedz!");
    }

    private static String askName() {
        String temp;
        while (true) {
            System.out.print("Podaj swoj pseudonim: ");
            temp = inScanner.nextLine();
            if (!temp.isBlank()) break;
            System.out.println("Nie mozna podac pustej odpowiedzi!");
        }
        return temp;
    }
}
