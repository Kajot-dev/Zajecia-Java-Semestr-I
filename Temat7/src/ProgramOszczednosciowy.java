public class ProgramOszczednosciowy {
    public static void main(String[] args) {
        Skarbonka s1 = new Skarbonka("Alicja", 1000);
        Skarbonka s2 = new Skarbonka("Bogdan", 200);
        s1.print();
        s2.print();
        s2.dodajOszczednosci(300);
        s1.print();
        s2.print();
        s1.dodajOszczednosci(100);
        s1.print();
        s2.print();
        double przelew = s2.getOszczednosci();
        s2.pobierzOszczednosci(przelew);
        s1.dodajOszczednosci(przelew);
        s1.print();
        s2.print();
    }
}
