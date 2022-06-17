package number3;

import number3.productpart.IProductPart;

public interface IProduct {
    void installFirstPart(IProductPart iProductPart);
    void installSecondPart(IProductPart iProductPart);
    void installThirdPart(IProductPart iProductPart);
}
