import java.util.*;

public class Sito {
    public static void main(String[] args) {
        int ilosc = 1200;
        boolean[] sito = new boolean[ilosc + 1];
        Arrays.fill(sito, true);

        for (int i = 2; i*i <= ilosc; i++) {
            if (sito[i]) {
                for (int j = i+i; j <= ilosc; j += i) sito[j] = false;
            }
        }

        for (int i = 2; i < sito.length; i++) {
            if (sito[i]) System.out.println(i);
        }
    }
}