import java.util.*;

public class Playlista {
    private final String nazwa;
    private final List<Utwor> utwory = new LinkedList<>();

    public Playlista(String nazwa, Utwor... utwory) {
        this(nazwa);
        for (Utwor s : utwory) this.addUtwory(s);
    }

    public Playlista(String nazwa) {
        this.nazwa = nazwa;
    }

    public void addUtwory(Utwor... utwory) {
        for (Utwor u : utwory) {
            if (this.utwory.contains(u)) System.err.println("Ten utwor juz jest na playliscie!");
            else this.utwory.add(u);
        }
    }

    public Utwor getUtwor(int numer) {
        this.checkBounds(numer);
        return this.utwory.get(numer - 1);
    }

    public void removeUtwor(int numer) {
        this.checkBounds(numer);
        this.utwory.remove(numer - 1);
    }

    public void sort() {
        Collections.sort(this.utwory);
    }

    public int size() {
        return this.utwory.size();
    }

    public void print() {
        System.out.print(this);
    }

    public String getNazwa() {
        return this.nazwa;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlista \"").append(this.nazwa).append("\":\n");
        if (this.utwory.isEmpty()) {
            sb.append("Playlista jest pusta!\n");
            return sb.toString();
        }
        int nLen = getStrLen(this.utwory.size());
        if (nLen < 3) nLen = 3;
        final String formatString = "%" + nLen + "s. %s\n";
        Formatter fm = new Formatter(sb);
        fm.format(formatString, "Nr", "Nazwa");
        for (int i = 1; i <= this.utwory.size(); i++) {
            fm.format(formatString, i, this.utwory.get(i-1));
        }
        fm.close();
        return sb.toString();
    }

    private void checkBounds(int index) {
        if (this.size() < index || index < 1) throw new IndexOutOfBoundsException("Max index is " + (this.size() - 1) + " and has to be greater than 0!");
    }

    private static int getStrLen(int x) {
        int sum = 0;
        while (x > 0) {
            sum++;
            x /= 10;
        }
        return sum;
    }
}
