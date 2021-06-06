public class Student {
    private String imie;
    private String nazwisko;
    private double sredniaOcen;

    public Student(String imie, String nazwisko, double sredniaOcen) throws WrongMeanException {
        this.imie = imie;
        this.nazwisko = nazwisko;

        if (sredniaOcen < 0 || sredniaOcen > 5) throw new WrongMeanException("Srednia musi zawierac się pomiędzy 0,a 5!");

        this.sredniaOcen = sredniaOcen;
    }

    public double getSrednia() {
        return this.sredniaOcen;
    }

    

    @Override
    public String toString() {
        return String.format("Student [imie=%s, nazwisko=%s, srednia=%s]", 
            this.imie,
            this.nazwisko,
            this.sredniaOcen
        );
    }
}
