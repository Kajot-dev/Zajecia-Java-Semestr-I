import java.util.InputMismatchException;
import java.util.Scanner;

public class WeryfikacjaWieku {
    public static void main(String[] args) {
        short wiek = -1; //czemu wartosc poczatkowa?
        //bonusowo
        Scanner s = new Scanner(System.in);
        while (wiek < 0) {
            while (true) {
                System.out.print("Podaj swoj wiek: ");
                try {
                    wiek = s.nextShort();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Podano nieprawidlowe dane!");
                    s.nextLine();
                }
            }
            if (wiek < 0) {
                System.out.println("Wiek nie moze byc ujemny!");
            }
        }
        s.close();
        if (wiek < 18) {
            System.out.println("Witamy w programie dla dzieci!");
        } else {
            System.out.println("Program jest przeznaczony wylacznie dla dzieci!");
        }
        
    }
}
