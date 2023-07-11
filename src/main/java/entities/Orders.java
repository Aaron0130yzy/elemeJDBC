package entities;

import java.util.Date;

public class Orders {
    private int id;
    private String customerId;
    private Date time;
    private boolean condition;

    public Orders(int id, String customerId, Date time, boolean condition) {
        this.id = id;
        this.customerId = customerId;
        this.time = time;
        this.condition = condition;
    }

    public Orders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }


}

