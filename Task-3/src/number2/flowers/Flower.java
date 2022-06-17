package number2.flowers;

public class Flower {

    private String name;
    private int cost;


    public Flower(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Flower: " + name + " cost: " + cost;
    }
}
