package dao;

import entities.Customer;
import entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDao {
    public Customer login(String id, String pwd) {
        String sqlQueryLang = "SELECT DISTINCT * FROM Customer WHERE idNumber ='" + id + "' AND password ='" + pwd + "'";
        Customer customer;
        customer = new Customer();
        try {
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            if (rs.next()) {
                customer.setIdNumber(rs.getString("idNumber"));
                customer.setPassword(rs.getString("password"));
                customer.setUsername(rs.getString("username"));
                customer.setAddress(rs.getString("address"));
                customer.setSex(rs.getBoolean("sex"));
                customer.setRealName(rs.getString("realName"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                return customer;
            }
        } catch (SQLException e) {
            System.out.println("登录失败");
            return null;
        }
        return customer;
    }

    //注册用户
    public void regist(String uname, String pwd, String add, boolean sex, String pnum, String rname, String id) {
        String sqlQueryLang = "INSERT INTO Customer(username,password,address,sex,phoneNumber,realName,idNumber) VALUES('"
                + uname + "','" + pwd + "','" + add
                + "','" + sex + "','" + pnum + "','" + rname + "','" + id + "')";
        System.out.println("注册成功！您可以使用身份证号登录新账号");
        try {
            connection.modify(sqlQueryLang);
        } catch (SQLException e) {
            System.out.println("注册失败！请重试");
        }

    }

    public void modify(String username, String password, boolean sex, String address, String phoneNumber, String idNumber) {
        String sqlQueryLang = "UPDATE Customer SET username = '" + username + "', password = '" + password + "', sex = '" + sex +
                "', address = '" + address + "', phoneNumber = '" + phoneNumber + "' WHERE idNumber = '" + idNumber + "'";
        try {
            connection.modify(sqlQueryLang);
            System.out.println("修改成功！");
        } catch (SQLException e) {
            System.out.println("修改失败！"+e);
        }
    }

    public void delete(String id) {
        String sqlQueryLang = "DELETE FROM Customer WHERE idNumber = '" + id + "'";
        try {
            connection.modify(sqlQueryLang);
            System.out.println("删除成功！");
        } catch (SQLException e) {
            System.out.println("删除失败！"+e);
        }
    }

    //直接查询所有用户信息
    public List<Customer> showAllCustomer(){
        String sqlQueryLang = "SELECT  * FROM Customer";
        var lst = new ArrayList<Customer>();
        try{
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                Customer Customer = new Customer();
                Customer.setIdNumber(rs.getString("idNumber"));
                Customer.setPassword(rs.getString("password"));
                Customer.setUsername(rs.getString("username"));
                Customer.setAddress(rs.getString("address"));
                Customer.setSex(rs.getBoolean("sex"));
                Customer.setRealName(rs.getString("realName"));
                Customer.setPhoneNumber(rs.getString("phoneNumber"));
                lst.add(Customer);
            }
        } catch (SQLException e) {
            System.out.println("查询失败！"+e);
        }


        return lst;
    }

    public Customer search(String id) {
        String sqlQueryLang = "SELECT DISTINCT * FROM Customer WHERE idNumber ='" + id +"'";
        Customer customer =null;
        try{
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                customer = new Customer();
                customer.setIdNumber(rs.getString("idNumber"));
                customer.setPassword(rs.getString("password"));
                customer.setUsername(rs.getString("username"));
                customer.setAddress(rs.getString("address"));
                customer.setSex(rs.getBoolean("sex"));
                customer.setRealName(rs.getString("realName"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
            }
            return customer;
        } catch (SQLException e) {
            System.out.println("查询失败！"+e);
        }
        return customer;
    }

    public void showMenu(String idNumber) {
//        ProductDao pDao = new ProductDao();
        Scanner sc = new Scanner(System.in);
//        OrderDao oDao = new OrderDao();
        while (true) {
            // 显示菜单选项
            System.out.println("请选择操作：");
            System.out.println("1. 查看商品列表\t2. 查询指定商家菜单");
            System.out.println("3. 修改个人信息\t4. 查看商品列表");
            System.out.println("5. 下订单");
            System.out.println("0. 退出");

            // 获取用户输入
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    //todo 查看商品列表
//                    pDao.showAllProduct();
                }
                case 2 -> {
                    //todo 查询指定商家的菜单
                }
                case 3 -> {
                    Customer customer = new Customer();
                    System.out.println("请输入新用户名");
                    customer.setUsername(sc.next());
                    System.out.println("请输入新密码");
                    customer.setPassword(sc.next());
                    System.out.println("请输入您的性别");
                    customer.setSex(sc.next().equals("男"));
                    System.out.println("请输入新地址");
                    customer.setAddress(sc.next());
                    System.out.println("请输入新电话号码");
                    customer.setPhoneNumber(sc.next());
                    modify(customer.getUsername(), customer.getPassword(), customer.getSex(),customer.getAddress(), customer.getPhoneNumber(), idNumber);
                }
                case 4 -> {
                    //todo 查看商品列表
                }
                case 5 -> {
                    //todo 下订单
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("输入无效，请重新输入！");
            }
        }
    }
}
