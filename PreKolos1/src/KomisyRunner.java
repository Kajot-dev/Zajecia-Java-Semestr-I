import java.util.List;
import java.util.stream.Collectors;

public class KomisyRunner {

    private static final String NO_CARS = "Brak samochodow spelniajacych kryteria!";
    private static final String NO_OCASSION = "Aktualnie brak okazji!";
    private static final String OCASSION = "!!! OKAZJA !!! ";

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Komis rodzyny = new Komis("Rodzyny");
        Komis igly = new Komis("Igly");

        
        try {
            //dodajemy do rodzyn
            rodzyny.dodajAuto(new Auto("Ford", "Focus", 2001, 80000, 11999.99));
            rodzyny.dodajAuto(new Auto("Ford", "Fiesta", 2005, 84321, 17999.99));
            rodzyny.dodajAuto(new Auto("Nissan", "Staroc", 1950, 170500, 20000.00));

            //dodajemy do igiel
            igly.dodajAuto(new Auto("Renualt", "Choinka", 2017, 603030, 50999.99));
            igly.dodajAuto(new Auto("Nissan", "Juke", 2018, 0, 85000.00));
            igly.dodajAuto(new Auto("BMW", "E3", 2008, 140000, 18000.00));
        } catch (WrongvalueException e) {
            System.out.println(e.getMessage());
            return;
        }

        //c
        rodzyny.wyznaczOkazje();
        Auto okazjaRodzyny = rodzyny.getOkazja();

        //d

        //komis rodzyna
        List<Auto> auta2001 = rodzyny.getAuta()
        .stream().filter(s -> s.getRocznik() >= 2001)
        .collect(Collectors.toList());

        System.out.println("\nOferty komisu Rodzyny, ktorych rocznik jest rowny lub mlodszy niz 2001:\n");

        if (auta2001.isEmpty()) {
            System.out.println(NO_CARS);
        } else {
            for (Auto s : auta2001) System.out.println(s);
        }
        
        if (okazjaRodzyny == null) {
            System.out.println(NO_OCASSION);
        } else {
            System.out.println(OCASSION + okazjaRodzyny);
        }
        
        //komis igla

        igly.wyznaczOkazje();
        Auto okazjaIgly = igly.getOkazja();

        List<Auto> auta2007 = igly.getAuta()
        .stream().filter(s -> s.getRocznik() >= 2007)
        .collect(Collectors.toList());

        System.out.println("\nOferty komisu Igly, ktorych rocznik jest rowny lub mlodszy niz 2007:\n");

        if (auta2007.isEmpty()) {
            System.out.println(NO_CARS);
        } else {
            for (Auto s : auta2007) System.out.println(s);
        }

        if (okazjaIgly == null) {
            System.out.println(NO_OCASSION);
        } else {
            System.out.println(OCASSION + okazjaIgly);
        }

        //e

        try {
            igly.dodajAuto(new Auto("Dacia", "Sandero", 2016, 15000, 28000));
        } catch (WrongvalueException e) {
            System.out.println(e.getMessage());
            return;
        }

        igly.wyznaczOkazje();
        okazjaIgly = igly.getOkazja();

        //f

        List<Auto> auta2012 = igly.getAuta()
        .stream().filter(s -> s.getRocznik() >= 2012)
        .collect(Collectors.toList());

        System.out.println("\nOferty komisu Rodzyny, ktorych rocznik jest rowny lub mlodszy niz 2012:\n");

        if (auta2012.isEmpty()) {
            System.out.println(NO_CARS);
        } else {
            for (Auto s : auta2012) System.out.println(s);
        }

        if (okazjaIgly == null) {
            System.out.println(NO_OCASSION);
        } else {
            System.out.println(OCASSION + okazjaIgly);
        }

        //g

        rodzyny.usunAuto(okazjaRodzyny);

        rodzyny.usunAuto(okazjaIgly);
    }
}
