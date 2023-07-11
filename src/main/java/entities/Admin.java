package entities;

public class Admin {
    private int id; // 管理员ID
    private String password; // 管理员密码
    private String name; // 管理员名称

    public Admin(int id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public Admin() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}