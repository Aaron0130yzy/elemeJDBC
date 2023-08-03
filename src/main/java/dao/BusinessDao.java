package dao;

import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDao {
    public Business login(String id, String pwd) {
        String sqlQueryLang = "SELECT DISTINCT * FROM Business WHERE id ='" + id + "' AND password ='" + pwd + "'";
        try {
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            Business business;
            if (rs.next()) {
                business = new Business();
                business.setId(Integer.parseInt(rs.getString("id")));
                business.setPassword(rs.getString("password"));
                business.setName(rs.getString("name"));
                business.setAddress(rs.getString("address"));
                business.setDescription(rs.getString("description"));
                business.setPhoneNumber(rs.getString("phoneNumber"));
                return business;
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public String regist(String name, String pwd, String add, String pnum, String des) {
        String newId=null;
        String sqlQueryLang = "INSERT INTO Business(name,password,address,phoneNumber,description) VALUES('"
                + name + "','" + pwd + "','" + add
                + "','" + pnum + "','" + des + "')";
        try {
            connection.modify(sqlQueryLang);
            sqlQueryLang = "SELECT id FROM business WHERE name = '" + name+"'";
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                newId = rs.getString("id");
                System.out.println("注册成功！您的ID为 " + newId + " ，请保存以用于登录");
                // 处理查询结果
            }
        } catch (SQLException e) {
            System.out.println("注册失败！"+e);
            newId = null;
        }

        return newId;
    }

    public List<Business> showAllBusiness() throws SQLException {
        String sqlQueryLang = "SELECT  * FROM Business";
        ResultSet rs = connection.executeQuery(sqlQueryLang);

        var lst = new ArrayList<Business>();
        while (rs.next()) {
            Business business = new Business();
            business.setId(Integer.parseInt(rs.getString("id")));
            business.setPassword(rs.getString("password"));
            business.setName(rs.getString("name"));
            business.setAddress(rs.getString("address"));
            business.setDescription(rs.getString("description"));
            business.setPhoneNumber(rs.getString("phoneNumber"));
            lst.add(business);
        }
        return lst;
    }

    //根据id查询商户
    public Business searchId(String id) throws SQLException {
        String sqlQueryLang = "SELECT DISTINCT * FROM Bussiness WHERE id ='" + id + "'";
        ResultSet rs = connection.executeQuery(sqlQueryLang);
        Business business = null;
        while (rs.next()) {
            business = new Business();
            business.setId(Integer.parseInt(rs.getString("id")));
            business.setPassword(rs.getString("password"));
            business.setName(rs.getString("name"));
            business.setAddress(rs.getString("address"));
            business.setDescription(rs.getString("describe"));
            business.setPhoneNumber(rs.getString("telephone"));
        }
        return business;
    }
}
