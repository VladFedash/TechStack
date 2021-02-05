package values;

public class ProductDTO {
    public int cost;
    public String title;

    public int getCost() {
        return cost;
    }

    public ProductDTO(int cost, String title) {
        this.cost = cost;
        this.title = title;
    }
}