package values;

import java.util.ArrayList;
import java.util.List;

public class PricesDTO {

    public List<ProductDTO> productDTOS = new ArrayList<>();
    public int subtotal;

    public void addItem(ProductDTO productDTO) {
        productDTOS.add(productDTO);
        calculateSubtotal();
    }

    public void calculateSubtotal() {
        int temp = 0;
        for (var item : productDTOS) {
            temp += item.getCost();
        }
         subtotal = temp;
    }
}