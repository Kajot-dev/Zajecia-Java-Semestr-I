public class UniRunner {
    public static void main(String[] args) {
        UniRunner.run();
    }

    public static void run() {

        //podpunkt a
        Wydzial fizyka = new Wydzial("Fizyka");
        Wydzial psychologia = new Wydzial("Psycholgia");

        try {
            fizyka.przyjmij(new Student("Marian", "Matloka", 5.0));
            fizyka.przyjmij(new Student("Papiak", "Polez", 4.0));
            fizyka.przyjmij(new Student("Jan", "Kowalski", 3.0));

            psychologia.przyjmij(new Student("Marcin", "Nowak", 3.5));
            psychologia.przyjmij(new Student("Martyna", "Malina", 4.5));
            psychologia.przyjmij(new Student("Kasia", "Basia", 4.0));
        } catch (WrongMeanException e) {
            System.err.println(e.getMessage());
            return;
        }
        
        //podpunkt b

        //wydzial fizyki

        fizyka.wybierzPrymusa();

        fizyka.wypiszDane();

        //wydzial psychologii

        psychologia.wypiszDane();

        Student prymusPsychologii = psychologia.wybierzPrymusa();

        psychologia.wypiszDane();

        psychologia.skresl(prymusPsychologii);

        psychologia.wypiszDane();

        Student prymusFizyki = fizyka.wybierzPrymusa();

        System.out.println();
        psychologia.skresl(prymusFizyki);
    }
}
