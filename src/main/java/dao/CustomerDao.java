package dao;

import entities.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
