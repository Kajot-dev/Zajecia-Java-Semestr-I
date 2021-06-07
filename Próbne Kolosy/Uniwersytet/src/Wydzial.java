import java.util.*;

public class Wydzial {
    private String nazwa;
    private List<Student> studenci = new LinkedList<>();
    private Student prymus;
    
    public Wydzial(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public List<Student> getStudenci() {
        return studenci;
    }

    public Student getPrymus() {
        return prymus;
    }

    public void przyjmij(Student uczen) {
        this.studenci.add(uczen);
    }

    public void skresl(Student uczen) {
        if (!this.studenci.contains(uczen)) {
            System.out.println(uczen + " nie studiuje na wydziale " + this.nazwa);
        } else if (uczen == null) {
            System.out.println("Nie mozna opisac pustego obiektu!");
        } else {
            this.studenci.remove(uczen);
            if (this.prymus == uczen) {
                this.wybierzPrymusa();
            }
        }
    }

    public Student wybierzPrymusa() {

        Student aktualnyPrymus = null;

        for (Student aktualny : this.studenci) {
            if (aktualnyPrymus == null || aktualnyPrymus.getSrednia() < aktualny.getSrednia()) {
                aktualnyPrymus = aktualny;
            }
        }
        this.prymus = aktualnyPrymus;
        return this.prymus;
    }

    public void wypiszDane() {
        System.out.println("\nSTUDENCI WYDZIALU " + this.nazwa.toUpperCase() + ":\n");
        if (!this.studenci.isEmpty()) {
            for (Student uczen : this.studenci) {

                if (uczen == this.prymus) {
                    System.out.print("PRYMUS: ");
                }
                System.out.println(uczen);
            }

            if (this.prymus == null) {
                System.out.println("Nie wybrano jescze prymusa!");
            }
        } else {
            System.out.println("Wydzial " + this.nazwa + " nie ma zadnych studnetow!");
        }
    }
    
}
