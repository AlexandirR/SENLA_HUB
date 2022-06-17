package number3;

import number3.productpart.IProductPart;

public class Car implements IProduct {

    private IProductPart firstPart;
    private IProductPart secondPart;
    private IProductPart thirdPart;

    @Override
    public void installFirstPart(IProductPart iProductPart) {
        System.out.println("Установка первой части в продукт(заготовку)");
        this.firstPart = iProductPart;
    }

    @Override
    public void installSecondPart(IProductPart iProductPart) {
        System.out.println("Установка второй части в продукт(заготовку)");
        this.secondPart = iProductPart;
    }

    @Override
    public void installThirdPart(IProductPart iProductPart) {
        System.out.println("Установка третей части в продукт(заготовку)");
        this.thirdPart = iProductPart;
    }

    @Override
    public String toString() {
        return "Машина с сотовляющими: " + firstPart + " " + secondPart + " " + thirdPart;
    }
}
