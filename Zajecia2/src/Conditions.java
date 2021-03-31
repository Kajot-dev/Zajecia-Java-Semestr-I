public class Conditions {
    public static void main(String[] args) {
        System.out.println("Conditions applications started!");
        if (true) {
            System.out.println("TRUE CONDITION"); //dla jednej instrukcji klamry sa zbedne
        } else System.out.println("FALSE CONDITION"); //wlasnie tak (tutaj jest martwy kod - nigdy sie nie uruchomi)

        if (13 == 13) System.out.println("13 is equal to 13");
        String test = new String("13"); //celowo tworze zmienna za pomoca slowa kluczowego new, zeby utworzyc 2 rozne obiekty o takiej samej wartosci, aby wykluczyc "recykling" tego samego obiektu przez wirtualna maszyne Javy
        String test2 = new String("13");
        if (test == test2) { 
            /**
             * do porownywania tekstu powinno sie uzywac metody .equals()
             * "==" w przypadku wartości nie-prymitywnych sprawdza, czy to jest ten sam obiekt (odnosi się do tego samego adresu w pamieci), a nie czy ma taka sama wartosc
             * "==" w tym przypadku nie sprawdza wartosci obiektu, ale (u mnie) mimo wszystko warunek jest spelniany, kiedy uzywam literalow
             * dzieje sie tak w wyniku optymalizacji przeprowadzanych przez wirtualna maszyne Javy, ktora uzywa tego samego obiektu w celu zaoszczedzenia pamieci
             */
            System.out.println("\"13\" is equal to \"13\"");
        } else {
            System.out.println("\"13\" is NOT equal to \"13\"");
            System.out.println("Wait, what?");
        }

        if (test.equals(test2)) {
            System.out.println("Ufff, \"13\" IS equal to \"13\" after all");
        }

        if ('E' != 'e') System.out.println("Wielkosc znakow ma znaczenie");

        //dobra starczy, bo sonarlint juz wariuje

        int test3 = 2;

        switch (test3) {
            case 1:
                System.out.println("ONE");
                break;
            case 2:
                System.out.println("TWO");
                break;
            case 3:
                System.out.println("THREE");
                break;
            default:
                System.out.println("SOMETHING ELSE");
                break;
        }

        System.out.println("Conditions applications ended!");
    }
}
