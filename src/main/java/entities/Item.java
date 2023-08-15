package entities;

import java.math.BigDecimal;

public class Item {
    private int orderId;
    private int productId;
    private BigDecimal price;
    private int amount;

    public Item(int orderId, int productId, BigDecimal price, int amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}