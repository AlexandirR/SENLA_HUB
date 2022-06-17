package number3;

import number3.linestep.ILineStep;

public class AssemblyCar implements IAssemblyLine {

    private ILineStep firstPart;
    private ILineStep secondPart;
    private ILineStep thirdPart;

    public AssemblyCar(ILineStep firstPart, ILineStep secondPart, ILineStep thirdPart) {
        this.firstPart = firstPart;
        this.secondPart = secondPart;
        this.thirdPart = thirdPart;
    }

    @Override
    public IProduct assembleProduct(IProduct iProduct) {
        System.out.println("Установка составляющих в заготовку на сборочной линии");
        iProduct.installFirstPart(firstPart.buildProductPart());
        iProduct.installSecondPart(secondPart.buildProductPart());
        iProduct.installThirdPart(thirdPart.buildProductPart());
        System.out.println("Возвращение готового продукта");
        return iProduct;
    }
}
