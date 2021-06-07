import java.util.*;
import java.util.stream.Collectors;

public class LiczbaPierwsza {

    private static final Scanner cin = new Scanner(System.in);

    private static List<Integer> getInts() { //cała ta funkcja pozwala wpisać liczby przez użytkownika
        List<Integer> nums = new ArrayList<>();
        while (true) {
            try {
                System.out.print("Podaj liczby calkowite: ");
                nums.addAll(
                    Arrays.asList(cin.nextLine().split(" "))
                    .stream()
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList())
                );
                if (!nums.isEmpty()) break;
            } catch (NumberFormatException e) {
                nums.clear();
            }
            System.out.println("Podano niepoprawne liczby!");
        }
        return nums;
    }

    public static boolean isPrime(int liczba) {
        for (int dzielnik = 2; dzielnik*dzielnik <= liczba; dzielnik++) { //skrócono pętle do pierwiastka z liczby
            if (liczba % dzielnik == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) { 
        List<Integer> liczby = getInts(); //tak naprawdę ważna jest tylko funkcja isPrime
        for (int i : liczby) {
            System.out.println("Liczba " + i + (isPrime(i) ? " jest" : " NIE jest") + " liczba pierwsza!");
        }
    }
}
