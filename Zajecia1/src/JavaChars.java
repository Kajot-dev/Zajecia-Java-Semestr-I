class JavaChars {
    public static void main(String[] args) {
        String tekst1 = "Welcome to JavaChars"; //do tej wbudowanej "klasy" wyjątkowo nie potrzeba słowa kluczowego "new"
        System.out.println(tekst1);
        char znak1 = 'x'; //zwykły literał
        char znak2 = '\u0078'; //unicode
        char znak3 = '\170'; //ASCII

        System.out.println("Zywkły: " + znak1);
        System.out.println("Unicode: " + znak2);
        System.out.println("ASCII: " + znak3);

        //Dodatkowe znaki
        System.out.print("DODATKOWE ZNAKI: ");
        System.out.print('\u26A1' + " "); //piorun (i spacja) - unicode
        System.out.print('\u13AF' + " "); //specjalne a (i spacja) - unicode
        System.out.print('\122'); //R w ASCII
    }
}