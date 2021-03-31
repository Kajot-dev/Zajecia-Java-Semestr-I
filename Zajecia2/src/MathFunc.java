public class MathFunc {
    public static int randInt(int min, int max) {
        return (int)(Math.floor(Math.random() * (max - min + 1)) + min);
    }
    public static double randDouble(double min, double max) {
        return Math.random() * (max - min + 1) + min;
    }
    public static void main(String[] args) {
        System.out.print("Podpunkt 1: ");
        System.out.println(Math.pow(10, 4));
        System.out.print("Podpunkt 2: ");
        System.out.println(Math.pow(4, 0.5));
        System.out.print("Podpunkt 3: ");
        System.out.println(Math.pow(4, 1.0/2.0)); //dokladnie to samo co 0.5
        System.out.print("Podpunkt 4: ");
        System.out.println(Math.pow(2.25, 0.5));
        System.out.print("Podpunkt 5: ");
        System.out.println(Math.log(0.5));
        System.out.print("Podpunkt 6: ");
        System.out.println(Math.log10(1000));
        System.out.print("Podpunkt 7: ");
        double objetosc1 = Math.PI * 10.0 * 10.0 * 20.0;
        System.out.println(objetosc1);
        System.out.print("Podpunkt 8: ");
        double objetosc2 = Math.PI * Math.pow(10, 2) * 20.0;
        System.out.println(objetosc2);
        System.out.print("Podpunkt 9: ");
        double wynik2 = Math.log10(2) / Math.log10(1024);
        System.out.println(wynik2);
        System.out.print("Podpunkt 10: ");
        //mozna by tez uzyc java.util.Random.nextInt()
        System.out.println(randInt(0, 123) + ", " + randDouble(0, 123));
        //jako dowolną l
        System.out.print("Podpunkt 11: ");
        System.out.println(randInt(-100, 100) + ", " + randDouble(-100, 100));
        System.out.print("Podpunkt 12: ");
        System.out.println(Math.pow(27, 1.0/3.0));
    }
}
