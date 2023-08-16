package entities;

import java.math.BigDecimal;

public class ProductPair {
    private int productId;
    private int quantity;
    private BigDecimal price;

    public ProductPair(int productId, int quantity, BigDecimal price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
    public BigDecimal getPrice() { return price;}
}
