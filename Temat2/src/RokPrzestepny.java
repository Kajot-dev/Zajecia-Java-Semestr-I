
public class RokPrzestepny {

	public static void przestepny() {
		System.out.println("Rok JEST rokiem przestepnym!");
	}
	
	public static void niePrzestepny() {
		System.out.println("Rok NIE jest rokiem przestepnym!");
	}
	public static void main(String[] args) {
		int rok = 2344;
		if (rok % 400 == 0) {
			przestepny();
		} else if (rok % 4 != 0) {
			niePrzestepny();
		} else if (rok % 100 == 0) {
			niePrzestepny();
		} else {
			przestepny();
		}

		//System.exit(0); //opcjonalne
	}

}
