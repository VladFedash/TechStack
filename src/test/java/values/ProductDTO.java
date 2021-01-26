package values;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {

    public List<Item> items = new ArrayList<>();
    public int subtotal;

    public void addItem(Item item) {
        items.add(item);
        calculateSubtotal();
    }

    public void calculateSubtotal() {
        int temp = 0;
        for (var item : items) {
            temp += item.getCost();
        }
         subtotal = temp;
    }
}