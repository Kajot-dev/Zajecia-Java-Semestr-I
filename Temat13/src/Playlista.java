import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Playlista {
    private final String nazwa;
    private final List<Utwor> utwory = new LinkedList<>();
    private static final String SONG_LIST = "Lista utworow:";

    public Playlista(String nazwa, File f) throws IOException{
        this.nazwa = nazwa;
        //we assume that file is correct
        try (
            Scanner sc = new Scanner(f);
        ) {
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                if (s.isBlank()) continue;
                this.addUtwory(Utwor.from(s));
            }
        } catch (IllegalArgumentException e) {
            throw new IOException();
        }
    }

    public Playlista(String nazwa, Utwor... utwory) {
        this(nazwa);
        for (Utwor s : utwory) this.addUtwory(s);
    }

    public Playlista(String nazwa) {
        this.nazwa = nazwa;
    }

    public void addUtwory(Utwor... utwory) {
        for (Utwor u : utwory) {
            if (u == null) throw new NullPointerException("Utwor nie moze byc pustym obiektem!");
            if (this.utwory.contains(u)) System.err.println("Ten utwor juz jest na playliscie!");
            else this.utwory.add(u);
        }
    }

    public Utwor getUtwor(int numer) {
        return this.utwory.get(numer - 1);
    }

    public String[] getListaUtworow() {
        List<Utwor> temp = new ArrayList<>(this.utwory);
        String[] output = new String[this.utwory.size()];
        temp.stream().map(Utwor::toString).collect(Collectors.toList()).toArray(output);
        return output;
    }

    protected int wybierzIndeksUtworu() throws UserAbortedException, EmptyPlaylistException {
        if (this.utwory.isEmpty()) throw new EmptyPlaylistException();
        System.out.println(SONG_LIST);
        Odtwarzacz.printOptions(true, this.getListaUtworow());
        int out = Odtwarzacz.askInt(1, this.utwory.size() + 1);
        if (out == this.utwory.size() + 1) throw new UserAbortedException();
        return out;
    }

    public void removeUtwor(int numer) {
        this.utwory.remove(numer - 1);
    }

    public void sortujPoTytule() {
        Collections.sort(this.utwory, Utwor.compPoTytule);
    }

    public void sortujPoWykonawcy() {
        Collections.sort(this.utwory, Utwor.compPoAutorze);
    }

    public void sortujPoRoku() {
        Collections.sort(this.utwory, Utwor.compPoRoku);
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

    protected void serialise(Writer w) throws IOException {
        for (Utwor u : this.utwory) {
            w.write(u.getTytul() + ";" + String.join(",", u.getWykonawcy()) + ";" + u.getRokWydania() + "\n");
        }
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
