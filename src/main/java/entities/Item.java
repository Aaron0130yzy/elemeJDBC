package entities;

import java.math.BigDecimal;

public class Item {
    private int id;
    private int orderId;
    private int productId;
    private BigDecimal price;
    private int amount;
    private boolean condition;

    public Item(int id, int orderId, int productId, BigDecimal price, int amount, boolean condition) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.amount = amount;
        this.condition = condition;
    }
    public Item() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean getCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }
}