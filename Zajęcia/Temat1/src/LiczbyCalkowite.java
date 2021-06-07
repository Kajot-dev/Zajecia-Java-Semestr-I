
public class LiczbyCalkowite {
	public static void main(String[] args) {
		//encoding UTF-8
		
		//PODPUNKT 3 (1 i 2 to było utworzenie projektu i klasy)
		System.out.println("---PODPUNKT 3---");

		byte liczba1 = (byte)(40 * 100); //musze zrobic castowanie do typu byte, aby moc skompilowac program
		short liczba2 = 40 * 100;
		int liczba3 = 40 * 100;
		long liczba4 = 40 * 100L; //umieszczam jeden literal typu long, aby wynik mnozenia byl typu long
		
		System.out.println("WYNIK DZIALANIA \"40 * 100\" DLA ROZNYCH TYPOW: ");
		System.out.println("BYTE: " + liczba1);
		System.out.println("SHORT: " + liczba2);
		System.out.println("INT: " + liczba3);
		System.out.println("LONG: " + liczba4);

		/**
		 * WNIOSEK
		 * 4000 nie mieści się w typie byte, który ma tylko 8 bitów, a więc może pomieścić 256 wartości (+-127)
		 */

		//PODPUNKT 4
		System.out.println("---PODPUNKT 4---");

		int pdp4 = 10 ^ 3;
		System.out.println(pdp4);
		/**
		 * Operator "^" służy do operacji XOR
		 * Wynikiem jest 9, ponieważ komputer wykonuje koleno operacje XOR na poszczególnych bitach obu liczb i w ten sposób powstają kolejne bity liczby wynikowej
		 * 
		 * 0011 -> 3
		 * 1010 -> 10
		 * ----
		 * 1001 -> 9
		 * 
		 * 1 traktujemy jako true
		 * 0 traktujemy jako false
		 */

		//PODPUNKT 5
		System.out.println("---PODPUNKT 5---");

		int a = 1;
		int b = a + a++;
		System.out.println(a == b);
		/**
		 * Wykonywane operacje
		 * a = 1
		 * b = a (1) + a++ (wciąż 1) (więc b = 2)
		 * a += 1 (więc a = 2) -> ta operacja zawiera się w a++
		 * w związku z tym a == b jest prawdą (2 == 2)
		 * dzieje się tak, ponieważ używamy postinkrementacji, która najpierw zwraca wartość, a potem ją zwiększa
		 * To znaczy, że najpierw b = 1 + 1 (ponieważ wartość a PRZED zwiększeniem to 1)
		 * Następnie a jest zwiększane o 1, więc a to także 2
		 */

		//PODPUNKT 6
		System.out.println("---PODPUNKT 6---");

		int f = 1;
		int g = f++; //g ma wartość 1, bo używamy postinkrementacji, a f w następnych działaniach będzie miało wartość 2
		int h = g * (++f); //h otrzyma wartość 3
		System.out.println(h); 
		/**
		 * Dlaczego?
		 * 1. f = 1
		 * 2. g również ma wartość 1 z uwagi na postinkrementację, f ma od teraz wartość 2
		 * 3. f najpierw zwiększane jest o 1 (czyli ma wartość 3), a następnie podstawiane do działania g * 3;
		 * 4. h = 1 * 3
		 */

		//PODPUNKT 7
		System.out.println("---PODPUNKT 7---");

		boolean k = false;
		boolean l = true;

		boolean m = k != !l; //m otrzyma wartość false
		System.out.println(m);
		/**
		 * Dlaczego?
		 * m = false != !true
		 * "!" jest negacją, a więc
		 * m = false != false
		 * "!=" oznacza "różne od" (w przypadku zmiennych "prymitywnych" oznacza różną wartość), a obie wartości są takie same, więc
		 * m = false
		 */
	}

}
