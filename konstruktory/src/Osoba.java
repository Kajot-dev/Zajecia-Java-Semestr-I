public class Osoba {
    //statyczne, czyli na samym okresleniu "Osoba", a nie na kims konkretnym
    public static final int NOGI = 2;
    //instancji
    private int wiek;
    private String imie;

    //konstruktor bez parametrow
    public Osoba() {
        this("Nicek", 1000);
        /**
         * this("cos", 99) uruchamia konstruktor z dwoma parametrami, czyli ten w linijce 17
         * W ten sposob nie trzeba pisac drugiego konstruktora od poczatku
         */
    }

    //konstruktor z parametrami
    public Osoba(String imie, int wiek) {
        this.wiek = wiek;
        this.imie = imie;
    }

    //metoda bez static, czyli na instancji, na "konkretnej" osobie
    public void przedstawSie() {
        System.out.println("Czesc, nazywam sie " + this.imie + " i mam " + this.wiek + " lat!");
    }
}
