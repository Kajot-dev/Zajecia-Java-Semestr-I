public class Program {
    public static void main (String args[]) {
        Osoba marek = new Osoba("Marek", 18); //to uruchomi linijke 17 w klasie osoba
        Osoba alicja = new Osoba("Alicja", 27); //to uruchomi linijke 17 w klasie osoba
        Osoba tajemnica = new Osoba(); //to uruchmi linijke 9 w klasie Osoba

        marek.przedstawSie();
        alicja.przedstawSie();
        tajemnica.przedstawSie();

        /**
         * Nie ma czegos takiego jak Osoba.przedstawSie(), poniewaz moze to zrobic tylko instancja
         * Czlowiek bez imienia nie moze sie przedstawic
         */

        System.out.println("Kazda osoba ma " + Osoba.NOGI + " nogi!");
        /**
         * Nie ma czegos takiego jak marek.NOGI, poniwaz to jest wartosc statyczna
         * Przeciez kazdy ma tyle samo nog, wiec po co kazda osoba mialaby przechowywac ta wartosc
         */
    }
}
