package entities;

public class Business {
    private String name; // 商家名称
    private int id; // 商家ID
    private String password; // 商家密码
    private String address; // 商家地址
    private String phoneNumber; // 商家电话号码
    private String description; // 商家描述信息


    public Business(Business business) {
        this.name = business.name;
        this.id = business.id;
        this.password = business.password;
        this.address = business.address;
        this.phoneNumber = business.phoneNumber;
        this.description = business.description;
    }

    public Business(int Id, String password, String name, String address, String description, String phoneNumber) {
        this.id = Id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.description = description;
        this.phoneNumber = phoneNumber;
    }

    public Business() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
