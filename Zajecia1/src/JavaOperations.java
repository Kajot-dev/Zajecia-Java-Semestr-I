
public class JavaOperations {
	public static void main(String[] args) {
		int liczbaCalkowita1 = 123;
		int calkowitaUjemna = -23;
		
		int wynik = liczbaCalkowita1 + calkowitaUjemna;
		long wynik2 = liczbaCalkowita1 * 200_200_200L; //literał typu long

		/**
		 * Operatory:
		 * Dodawanie (czy też złączenie) "+"
		 * Odejmowanie "-"
		 * Dzielenie "/"
		 * Mnożenie "*"
		 * Reszta (modulo) "%"
		 */


		System.out.println(liczbaCalkowita1 + " + " + calkowitaUjemna + " = " + wynik);
		System.out.println("Wynik2: " + wynik2);

		
	}
}
