package entities;

import java.math.BigDecimal;

public class Product {
    private int businessId; // 商家ID
    private String name; // 商品名称
    private BigDecimal discount; // 商品折扣
    private boolean onSale; // 商品是否正在销售
    private String description; // 商品描述信息
    private int id; // 商品ID
    private BigDecimal price; // 商品价格

    public Product(int businessId, String name, BigDecimal discount, boolean onSale, String description, int id, BigDecimal price) {
        this.businessId = businessId;
        this.name = name;
        this.discount = discount;
        this.onSale = onSale;
        this.description = description;
        this.id = id;
        this.price = price;
    }

    public Product() {
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}