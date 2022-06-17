package number2.flowers;

public class DriedFlower extends Flower {
    private int age;

    public DriedFlower(String name, int cost, int age) {
        super(name, cost);
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() + " age: " + this.age;
    }
}
