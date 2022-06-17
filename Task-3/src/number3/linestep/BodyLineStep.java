package number3.linestep;

import number3.productpart.Body;
import number3.productpart.IProductPart;

public class BodyLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Поставка кузова с LineStep");
        return new Body();
    }
}
