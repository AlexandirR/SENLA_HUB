package number2;

import number2.flowers.Flower;

import java.util.List;

public class Bouquet {

    private List<Flower> flowers;

    public Bouquet(List<Flower> flowers) {
        this.flowers = flowers;
    }

    public int getFullPrice() {
        int cost = 0;
        for (Flower flower : this.flowers) {
            cost += flower.getCost();
        }
        return cost;
    }

    public void printBouquet() {
        for (Flower flower : this.flowers) {
            System.out.println(flower);
        }
    }
}
