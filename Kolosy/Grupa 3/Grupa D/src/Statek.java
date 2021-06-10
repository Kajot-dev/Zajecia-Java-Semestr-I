public class Statek {
    private int id;
    private String nazwa;
    private int punkty;
    
    public Statek(int id, String nazwa, int punkty) {
        this.id = id;
        this.nazwa = nazwa;
        this.punkty = punkty;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getPunkty() {
        return punkty;
    }

    @Override
    public String toString() {
        return "Statek [id=" + id + ", nazwa=" + nazwa + ", punkty=" + punkty + "]";
    }
    
}
