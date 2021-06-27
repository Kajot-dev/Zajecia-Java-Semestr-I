public class Rewolwer {
    private int naboje;

    public Rewolwer() {
        this.naboje = 5;
    }

    public void odejmijNaboj() {
        this.naboje--;
    }

    public void dodajNaboje(int n) {
        this.naboje += n;
    }

    public int getNaboje() {
        return this.naboje;
    }
}
