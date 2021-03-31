import java.util.Scanner;

public class Powitanie {
    public static void main(String[] args) {
        System.out.print("Podaj swoje imie: ");
        final Scanner sc = new Scanner(System.in);
        final String name = sc.nextLine();
        System.out.println("Witaj " + name + "!");
        sc.close();
    }
}
