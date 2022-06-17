package number3.linestep;

import number3.productpart.Chassis;
import number3.productpart.IProductPart;

public class ChassisLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Поставка шасси с LineStep");
        return new Chassis();
    }
}
