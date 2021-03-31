
public class RokPrzestepny {

	public static void przestepny() {
		System.out.println("Rok JEST rokiem przestepnym!");
	}
	
	public static void niePrzestepny() {
		System.out.println("Rok NIE jest rokiem przestepnym!");
	}

	private static boolean czyNiepodzielny4LubPodzielny100(int liczba) {
		return (liczba % 4 != 0 || liczba % 100 == 0);
	}
	public static void main(String[] args) {
		int rok = 2344;
		if (rok % 400 == 0) {
			przestepny();
		} else if (czyNiepodzielny4LubPodzielny100(rok)) {
			niePrzestepny();
		} else {
			przestepny();
		}

		//System.exit(0); //opcjonalne
	}

}
