
public class Casting {

	public static void main(String[] args) {
		//castowanie/rzutowanie
		int number = 100 * 100;
		System.out.println(number);
		
		System.out.println("P\tTAB\tK\tk");
		System.out.println((int)'P' + "\t" + (int)'\t' + "\t" + (int)'K' + "\t" + (int)'k');
		
		//dodawanie tkstu i liczb (automatyczne .toString())
		int liczba = 1;
		String tekst = "1";
		System.out.println(liczba + tekst);
		
		//potegowanie
		double wynik = Math.pow(10, 3);
		System.out.println(wynik);
		
	}

}
