import java.util.Scanner;

public class Kalkulator {

    /**
     * UWAGA
     * ROZSZERZONA WERSJA KALKULATORA (z 2 parametrami/dodatkowymi funkcjami/inną pozycją operatora)
     * została wrzucona jako zadanie domowe na platformę Moodle
     */

    private static final Scanner cin = new Scanner(System.in);

    public static void main(String[] args) {

        final String[] dzialania;

        if (args.length < 2) {
            System.out.print("Podaj działanie: ");
            dzialania = cin.nextLine().split(" ");
        } else dzialania = args;

        if (dzialania.length >= 3) {
            threeParams(dzialania);
        } else {
            System.out.println("Za mała ilość argumentów!");
        }

    }
    private static void printResult(double a) {
        System.out.println("WYNIK: " + a);
    }

    private static void threeParams(String[] dzialania) {
        String operator = dzialania[1];
        double x = Double.parseDouble(dzialania[0]);
        double y = Double.parseDouble(dzialania[2]);
        
        switch (operator) {
            case "+":
                printResult(x + y);
                break;
            case "-":
                printResult(x - y);
                break;
            case "/":
                printResult(x / y);
                break;
            case "x":
            case "*":
                printResult(x * y);
                break;
            default:
                System.out.println("Operator \"" + operator + "\" nie jest prawidłowy!");
                System.exit(1);
        }
    }
}
