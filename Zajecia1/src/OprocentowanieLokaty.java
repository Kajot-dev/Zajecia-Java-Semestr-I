
public class OprocentowanieLokaty {

	public static void main(String[] args) {

		//PODPUNKTY 1, 2, 4
		System.out.println("---PODPUNKTy 1, 2, 4---");

		//lokata bazowa
		float interestRate = 0.4f;
		float value = 5000;
		float duration = 3/12.0f;
		
		System.out.println("Duration: " + duration);

		float interest;
		float futureValue;
		
		interest = value * interestRate * duration;
		System.out.println("Interest: " + interest);

		futureValue = interest + value;
		System.out.println("FutureValue: " + futureValue);



		//PODPUNKT 3
		System.out.println("---PODPUNKT 3---");

		byte dur1 = (byte)(40 * 100); //Nie moÅ¼na przenieÅ›Ä‡ zmiennej typu int to byte (tu naprawione)
		short dur2 = 40 * 100;
		int dur3 = 40 * 100;
		long dur4 = 40 * 100L; //castujemy jeden litera³ do typu long Å¼eby wynik operacji rÃ³wnieÅ¼ by³ w tym formacie

		System.out.println("Wyniki dzialania \"40 * 100\" dla roznych typow zmiennych:");
		System.out.println("BYTE: " + dur1);
		System.out.println("SHORT: " + dur2);
		System.out.println("INT: " + dur3);
		System.out.println("LONG: " + dur4);


		//PODPUNKT 5
		System.out.println("---PODPUNKT 5---");
		System.out.println("Liczba lat: " + OprocentowanieLokaty.ileLat(9200, 0.1f, 8000));

		//PODPUNKT 6
		System.out.println("---PODPUNKT 6---");
		System.out.println("Liczba lat: " + OprocentowanieLokaty.ileLat6(9200, 0.1f, 8000));

		/**
		 * Komentarze odnoœnie zadania
		 * Podpunkt 2: Program nie kompiluje siê, bo nie mo¿na przenieœæ zmiennej z typu double do float - wykonujemy casting do typu float lub zmieniamy typ interest na double (tutaj jest zmieniony typ duration na float, bo takie jest pozniejsze polecenie)
		 * Podpunkt 3: 40 * 100 jest typu int, nie mozna go bezposrednio zamienil na typ byte (by³aby to konwersja stratna - uzyto castowania, Zeby program siê skompilowa³). Wynik nie jest poprawny, poniewaz nie miesci siê w zmiennej byte (8 bitÃ³w)
		 * Podpunkt 4.1: litera³y 3 i 12 sa typu int, a wiêc operacje na nich daja typ int, który dopiero potem jest konwertowany do typu double (i stad liczba jest przyciêta do ca³kowitej)
		 * Podpunkt 4.2: Liczba konwertuje siê poprawnie, bo 12.0 to domyslnie litera³ typu double (tak samo jak zmienna)
		 * Podpunkt 4.3: Liczba nie konwertuje siê, poniewaz probujemy przypisal typ double (wynik dzielenia) do zmiennej typu float (konwersja stratna)
		 * Podpunkt 4.4: Liczba konwertuje siê poniewaz operacja dzielenia int/float daje taki sam typ jak zmienna (float)
		 * Podpunkt 6: Najpierw wykonywane jest dzielenie, a nastêpnie mnozenie, co daje b³êdny wynik
		 */
	}

	public static float ileLat(float wartoscDocelowa, float oprocentowanie, float kapitalPoczatkowy) {
		float zysk = wartoscDocelowa - kapitalPoczatkowy;
		return zysk / (kapitalPoczatkowy * oprocentowanie);
	}

	public static float ileLat6(float wartoscDocelowa, float oprocentowanie, float kapitalPoczatkowy) {
		float zysk = wartoscDocelowa - kapitalPoczatkowy;
		return zysk / kapitalPoczatkowy * oprocentowanie;
	}
}
