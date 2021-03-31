
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

		byte dur1 = (byte)(40 * 100); //Nie można przenieść zmiennej typu int to byte (tu naprawione)
		short dur2 = 40 * 100;
		int dur3 = 40 * 100;
		long dur4 = 40 * 100L; //castujemy jeden litera� do typu long żeby wynik operacji również by� w tym formacie

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
		 * Komentarze odno�nie zadania
		 * Podpunkt 2: Program nie kompiluje si�, bo nie mo�na przenie�� zmiennej z typu double do float - wykonujemy casting do typu float lub zmieniamy typ interest na double (tutaj jest zmieniony typ duration na float, bo takie jest pozniejsze polecenie)
		 * Podpunkt 3: 40 * 100 jest typu int, nie mozna go bezposrednio zamienil na typ byte (by�aby to konwersja stratna - uzyto castowania, Zeby program si� skompilowa�). Wynik nie jest poprawny, poniewaz nie miesci si� w zmiennej byte (8 bitów)
		 * Podpunkt 4.1: litera�y 3 i 12 sa typu int, a wi�c operacje na nich daja typ int, kt�ry dopiero potem jest konwertowany do typu double (i stad liczba jest przyci�ta do ca�kowitej)
		 * Podpunkt 4.2: Liczba konwertuje si� poprawnie, bo 12.0 to domyslnie litera� typu double (tak samo jak zmienna)
		 * Podpunkt 4.3: Liczba nie konwertuje si�, poniewaz probujemy przypisal typ double (wynik dzielenia) do zmiennej typu float (konwersja stratna)
		 * Podpunkt 4.4: Liczba konwertuje si� poniewaz operacja dzielenia int/float daje taki sam typ jak zmienna (float)
		 * Podpunkt 6: Najpierw wykonywane jest dzielenie, a nast�pnie mnozenie, co daje b��dny wynik
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
