package values;

import lombok.Value;

@Value
public class Item {
    public int cost;

    public Item(int cost) {
        this.cost = cost;
    }
}