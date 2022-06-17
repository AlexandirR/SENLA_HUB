package number2.flowers;

public class GreenhouseFlower extends Flower {
    private int surcharge;

    public GreenhouseFlower(String name, int cost, int surcharge) {
        super(name, cost + surcharge);
        this.surcharge = surcharge;
    }

    @Override
    public String toString() {
        return super.toString() + " surcharge: " + this.surcharge;
    }
}
