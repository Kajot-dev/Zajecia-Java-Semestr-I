import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Random;

class RollList extends LinkedList<Integer> {

    private Random random = new Random();

    protected RollList() {
        super();
    }

    protected void throwDice(int bound) {
        int result = random.nextInt(bound) + 1;
        this.add(result);
    }

    protected void printState() {
        System.out.println("Wylosowano: " + this.get(this.size() - 1));
        if (this.size() > 1) {
            System.out.println("Wszystkie rzuty: " + super.toString());
            System.out.println("Suma: " + this.sum());
        }
    }

    protected int lastThrow() {
        return this.get(this.size() - 1);
    }

    protected void writeLastThrow(Writer w) throws IOException {
        w.write(String.format("%s. rzut \t [%s] suma: %s%n", this.size(), this.lastThrow(), this.sum()));
    }

    protected int sum() {
        return super.stream().mapToInt(Integer::intValue).sum();
    }
}
