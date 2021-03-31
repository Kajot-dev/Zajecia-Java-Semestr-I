import java.util.Scanner;

public class Kalkulator {

  private static final Scanner cin = new Scanner(System.in);

  public static void main(String[] args) {

    final String[] dzialania;

    if (args.length < 2) {
      System.out.print("Podaj działanie: ");
      dzialania = cin.nextLine().split(" ");
    } else
      dzialania = args;

    if (dzialania.length == 2) {
      twoParams(dzialania);
    } else if (dzialania.length >= 3) {
      threeParams(dzialania);
    } else {
      System.out.println("Za mała ilość argumentów!");
    }

  }

  private static void wynik(double a) {
    System.out.println("WYNIK: " + a);
  }

  private static void twoParams(String[] dzialania) {
    String operator;
    double x;
    x = Double.parseDouble(dzialania[1]);
    operator = dzialania[0];
    switch (operator.trim()) {
    case "abs":
      wynik(Math.abs(x));
      break;
    case "sin":
      wynik(Math.sin(x));
      break;
    case "cos":
      wynik(Math.cos(x));
      break;
    case "tan":
      wynik(Math.tan(x));
      break;
    case "round":
      wynik(Math.round(x));
      break;
    case "sqrt":
      wynik(Math.sqrt(x));
      break;
    default:
      System.out.println("Operator \"" + operator + "\" nie jest prawidłowy!");
      System.exit(1);
    }
  }

  private static void threeParams(String[] dzialania) {
    String operator;
    double x;
    double y;
    try {
      x = Double.parseDouble(dzialania[0]);
      operator = dzialania[1];
    } catch (NumberFormatException e) {
      operator = dzialania[0];
      x = Double.parseDouble(dzialania[1]);
    }
    y = Double.parseDouble(dzialania[2]);

    switch (operator.trim()) {
    case "+":
    case "add":
    case "dodaj":
      wynik(x + y);
      break;
    case "-":
    case "sub":
    case "odejmij":
      wynik(x - y);
      break;
    case "/":
    case "div":
    case "podziel":
      wynik(x / y);
      break;
    case "x":
    case "*":
    case "pomnoz":
      wynik(x * y);
      break;
    case "pow":
      wynik(Math.pow(x, y));
      break;
    case "min":
      wynik(Math.min(x, y));
      break;
    case "max":
      wynik(Math.max(x, y));
      break;
    default:
      System.out.println("Operator \"" + operator + "\" nie jest prawidłowy!");
      System.exit(1);
    }
  }
}
