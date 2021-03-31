
public class LogikaBool {
	public static void main(String[] args) {
		boolean test1 = true;
		boolean test2 = false;
		
		boolean wynik = test1 && test2; //AND
		boolean wynik2 = test1 || test2; //OR
		boolean wynik3 = test1 ^ test2; //XOR
		boolean wynik4 = !test1; //Negation
		
		System.out.println("Wynik AND: " + wynik);
		System.out.println("Wynik OR: " + wynik2);
		System.out.println("Wynik XOR: " + wynik3);
		System.out.println("Wynik negacji: " + wynik4);
		
	}
}
