package number3.linestep;

import number3.productpart.Engine;
import number3.productpart.IProductPart;

public class EngineLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        System.out.println("Поставка двигателя с LineStep");
        return new Engine();
    }
}
