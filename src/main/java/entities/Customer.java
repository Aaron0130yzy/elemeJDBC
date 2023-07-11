package entities;

public class Customer {
    private String username; // 用户名
    private String password; // 密码
    private String address; // 地址
    private Boolean sex; // 性别
    private String realName; // 真实姓名
    private String phoneNumber; // 手机号码
    private String idNumber; // 身份证号码

    public Customer(String username, String password, String address, Boolean sex, String realName, String phoneNumber, String idNumber) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.sex = sex;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
    }

    public Customer() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}