package entities;

public class ProductPair {
    private int productId;
    private int quantity;

    public ProductPair(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
