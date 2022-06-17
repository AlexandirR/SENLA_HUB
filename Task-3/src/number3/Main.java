package number3;

import number3.linestep.BodyLineStep;
import number3.linestep.ChassisLineStep;
import number3.linestep.EngineLineStep;

public class Main {

    public static void main(String[] args) {
        IAssemblyLine assemblyCar = new AssemblyCar(new BodyLineStep(), new ChassisLineStep(), new EngineLineStep());
        IProduct car = assemblyCar.assembleProduct(new Car());
        System.out.println(car);
    }
}
