package number2;

import number2.flowers.DriedFlower;
import number2.flowers.Flower;
import number2.flowers.GreenhouseFlower;
import number2.flowers.Wildflower;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flower> flowers = new ArrayList<>();
        flowers.add(new DriedFlower("flower1", 100, 3));
        flowers.add(new GreenhouseFlower("flower2", 700, 300));
        flowers.add(new Wildflower("flower3", 500, 5));
        Bouquet bouquet = new Bouquet(flowers);
        bouquet.printBouquet();
        System.out.println("Full cost: " + bouquet.getFullPrice());
    }
}
