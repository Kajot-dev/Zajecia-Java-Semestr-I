import java.util.Scanner;

public class Ruletka {

    private Rewolwer rewolwer;
    private Scanner skan = new Scanner(System.in);

    public void wlacz() {
        this.rewolwer = new Rewolwer();
        for(;;) {
            if (this.rewolwer.getNaboje() > 0) {
                this.rewolwer.odejmijNaboj();
                System.out.println("Załadowano nabój.");
                boolean rzut = rzucMoneta();
                if (rzut) {
                    System.out.println("Trafiłeś w <<HEADS>>");
                    this.rewolwer.dodajNaboje(10);
                } else {
                    System.out.println("Pudło!");
                }

                if (!zapytajCzyGracDalej()) {
                    System.out.println("Koniec strzelaniny! Zostało Ci " + this.rewolwer.getNaboje() + " naboi");
                    break;
                }

            } else {
                System.out.println("Skończyły się naboje!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Ruletka ruletka = new Ruletka();
        ruletka.wlacz();
    }

    public boolean rzucMoneta() {
        System.out.print("Nacieśnij Enter, aby rzucić moentą i strzelić");
        skan.nextLine();
        double r = Math.random();
        if (r > 0.5d) {
            return true;
        } else return false;
    }

    public boolean zapytajCzyGracDalej() {
        String x;
        for (;;) {
            System.out.print("Czy chcesz strzelać dalej? (t/n) ");
            x = skan.nextLine().trim().toLowerCase();
            if (x.equals("t")) return true;
            else if (x.equals("n")) return false;
            System.out.println("Nie podjąłeś decyzji!");
        }
    }
}
