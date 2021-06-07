import kredyty.*;

public class KalkulatorKredytowy {
    public static void main(String[] args) {

        Kredyt kredyt1 = new KredytMalejacy(15000, 12, 0.2);
        Kredyt kredyt2 = new KredytStaly(17000, 24, 0.17);


        System.out.println("---KREDYT MALEJACY---");
        System.out.println(kredyt1);

        System.out.println("---KREDYT STALY---");
        System.out.println(kredyt2);

    }
}
