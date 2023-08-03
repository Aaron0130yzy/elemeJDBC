package dao;

import entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusinessDao {
    Scanner sc = new Scanner(System.in);
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

    public void delete(String id) {
        String sqlQueryLang = "DELETE FROM Business WHERE id = '" + id + "'";
        try {
            connection.modify(sqlQueryLang);
            System.out.println("删除成功！");
        } catch (SQLException e) {
            System.out.println("删除失败！"+e);
        }
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

    public void modify(String id, String name, String password, String address, String phoneNumber, String description) {
        String sqlQueryLang = "UPDATE Business SET name = '" + name + "', password = '" + password + "', address = '" + address + "', phoneNumber = '" + phoneNumber + "', description = '" + description + "' WHERE id = '" + id + "'";
        try {
            connection.modify(sqlQueryLang);
            System.out.println("修改成功！");
        } catch (SQLException e) {
            System.out.println("修改失败！"+e);
        }
    }

    public void showMenu(String id) {
        while (true){
            System.out.println("请选择操作：");
            System.out.println("1. 查询自家菜品\t2. 添加菜品");
            System.out.println("3. 修改菜品信息\t4. 删除菜品");
            System.out.println("5. 修改商家信息\t6. 查询订单");
            System.out.println("0. 退出");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    //todo 查询自家菜品
                }
                case 2 -> {
                    //todo 添加菜品
                }
                case 3 -> {
                    //todo 修改菜品信息
                }
                case 4 -> {
                    //todo 删除菜品
                }
                case 5 -> {
                    Business business = new Business();
                    System.out.println("请输入商店名称");
                    business.setName(sc.next());
                    System.out.println("请输入密码");
                    business.setPassword(sc.next());
                    System.out.println("请输入商店地址");
                    business.setAddress(sc.next());
                    System.out.println("请输入电话号码");
                    business.setPhoneNumber(sc.next());
                    System.out.println("请输入简介");
                    business.setDescription(sc.next());
                    modify(id, business.getName(),business.getPassword(),business.getAddress(),business.getPhoneNumber(),business.getDescription());
                }
                case 6 -> {
                    //todo 查询订单
                }
                case 0 -> {
                    return;
                }
                default -> {
                    System.out.println("输入无效，请重新输入！");
                }
            }
        }

    }
}
