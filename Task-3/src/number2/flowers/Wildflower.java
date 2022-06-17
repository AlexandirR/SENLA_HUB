package number2.flowers;

public class Wildflower extends Flower {

    private int count;

    public Wildflower(String name, int cost, int count) {
        super(name, cost);
        this.count = count;
    }

    @Override
    public String toString() {
        return super.toString() + " count: " + this.count;
    }
}
