package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.*;


public class AdminDao {
    PreparedStatement statement = null;
//    Connection conn = null;
    Connection conn;
    ResultSet rs = null;
    Admin Admin;
    public Admin login(String id, String pwd) {
//        String sql="SELECT DISTINCT * FROM Admin WHERE id = ? AND password = ?";
//        try {//先设置占位符的值
//            statement= conn.prepareStatement(sql);
//            statement.setString(1, id);
//            statement.setString(2, pwd);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        String sql="SELECT DISTINCT * FROM Admin WHERE id = '"+id+"' AND password = '"+pwd+"'";
        try {//进行查询
//            rs = statement.executeQuery();
            ResultSet rs = connection.executeQuery(sql);
            if (rs.next()) {
                Admin = new Admin();
                Admin.setId(rs.getInt("id"));
                Admin.setPassword(rs.getString("password"));
                Admin.setName(rs.getString("name"));
                return Admin;
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public String regist(String name, String pwd) {
        String newId = null;
        try {
            String sqlQueryLang = "INSERT INTO Admin (name, password) VALUES ('" + name + "', '" + pwd + "');";
            connection.modify(sqlQueryLang);
            sqlQueryLang = "SELECT id FROM Admin WHERE name = '" + name+"'";
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                newId = rs.getString("id");
                System.out.println("注册成功！您的ID为 " + newId + " ，请保存以用于登录");
                // 处理查询结果
            }

        } catch (SQLException e) {
            System.out.println("注册失败！"+e);
        }

        return newId;
    }
    public void modify(String id, String password, String newPassword) {
        String sqlQueryLang = "UPDATE Admin SET password = '" + newPassword +"' WHERE id = '"
                + id + "' AND password = '" + password +"'";
        try {
            connection.modify(sqlQueryLang);
            System.out.println("修改成功！");
        } catch (SQLException e) {
            System.out.println("修改失败！"+e);
        }
    }

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        BusinessDao bDao = new BusinessDao();
        CustomerDao cDao = new CustomerDao();
        Customer customer = new Customer();
        Business business = new Business();
        while (true) {
            // 显示菜单选项
            System.out.println("请选择操作：");
            System.out.println("1. 查询商家       2. 添加商家");
            System.out.println("3. 修改商家信息    4. 删除商家");
            System.out.println("5. 查询用户       6. 添加用户");
            System.out.println("7. 修改用户信息    8. 删除用户");
            System.out.println("9. 修改密码       0. 退出");

            // 获取用户输入
            int choice = sc.nextInt();

            // 执行相应操作
            switch (choice) {
                case 1 -> {//todo 查询商家

                }
                case 2 -> {// 添加商家
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
                    String id;
                    id = bDao.regist(business.getName(),business.getPassword(),business.getAddress(),
                            business.getPhoneNumber(),business.getDescription());
                    if (id!=null) {
                        System.out.println("注册成功！id为" + id);
                    } else {
                        System.out.println("注册失败");
                    }
                }
                case 3 -> {// 修改商家信息
                    System.out.println("输入要改的商家id：");
                    business.setId(sc.nextInt());
                    System.out.println("请输入商店名称：");
                    business.setName(sc.next());
                    System.out.println("请输入密码：");
                    business.setPassword(sc.next());
                    System.out.println("请输入商店地址：");
                    business.setAddress(sc.next());
                    System.out.println("请输入电话号码：");
                    business.setPhoneNumber(sc.next());
                    System.out.println("请输入简介：");
                    business.setDescription(sc.next());
                    bDao.modify(String.valueOf(business.getId()),business.getName(),business.getPassword(),
                            business.getAddress(),business.getPhoneNumber(),business.getDescription());
                }
                case 4 -> {//删除商家
                    System.out.println("输入要删除的商家id：");
                    String id = sc.next();
                    bDao.delete(id);
                }
                case 5 -> {//查询用户
                    System.out.println("输入用户身份证号：");
                    String id = sc.next();
                    customer=cDao.search(id);
                    if (customer == null) {
                        System.out.println("用户身份证号 " + id + " 不存在！");
                    } else {
                        System.out.println("用户身份证号：" + customer.getIdNumber());
                        System.out.println("用户名：" + customer.getUsername());
                        System.out.println("用户密码：" + customer.getPassword());
                        System.out.println("用户地址：" + customer.getAddress());
                        String sex = "女";
                        if (customer.getSex()) {
                            sex = "男";
                        }
                        System.out.println("用户性别：" + sex);
                        System.out.println("用户真实姓名：" + customer.getRealName());
                        System.out.println("用户电话号码：" + customer.getPhoneNumber());
                    }
                    System.out.println("按回车键继续...");
                    sc.nextLine();
                    sc.nextLine();
                }
                case 6 -> {//添加用户
                    System.out.println("请输入用户名：");
                    customer.setUsername(sc.next());
                    System.out.println("请输入密码：");
                    customer.setPassword(sc.next());
                    System.out.println("请输入用户性别：");
                    customer.setSex(sc.next().equals("男"));
                    System.out.println("请输入用户地址：");
                    customer.setAddress(sc.next());
                    System.out.println("请输入用户电话号码：");
                    customer.setPhoneNumber(sc.next());
                    System.out.println("请输入用户真名；");
                    customer.setRealName(sc.next());
                    System.out.println("请输入用户身份证号：");
                    String id;
                    id = sc.next();
                    cDao.regist(customer.getUsername(),customer.getPassword(),customer.getAddress(),customer.getSex(),
                            customer.getPhoneNumber(),customer.getRealName(),customer.getIdNumber());
                    if (id!=null) {
                        System.out.println("注册成功！");
                    } else {
                        System.out.println("注册失败");
                    }
                }
                case 7 -> {//修改用户信息
                    System.out.println("输入要改的用户id：");
                    customer.setIdNumber(sc.next());
                    System.out.println("请输入用户名称：");
                    customer.setUsername(sc.next());
                    System.out.println("请输入密码：");
                    customer.setPassword(sc.next());
                    System.out.println("请输入用户性别：");
                    customer.setSex(sc.next().equals("男"));
                    System.out.println("请输入用户地址：");
                    customer.setAddress(sc.next());
                    System.out.println("请输入电话号码：");
                    customer.setPhoneNumber(sc.next());
                    cDao.modify(customer.getUsername(), customer.getPassword(), customer.getSex(),customer.getAddress(),
                            customer.getPhoneNumber(), customer.getIdNumber());
                }
                case 8 -> {//删除用户
                    System.out.println("输入要删除的用户id：");
                    String id = sc.next();
                    System.out.println("确认删除吗？请输入y以确认");
                    if(sc.next().equals("y")){
                        cDao.delete(id);
                    }
                }
                case 9 -> {//修改密码
                    System.out.println("请输入管理员id：");
                    String id =sc.next();
                    System.out.println("请输入旧密码：");
                    String password = sc.next();
                    System.out.println("请输入新密码：");
                    String newPassword=sc.next();
                    modify(id,password,newPassword);
                    System.out.println("按回车键继续...");
                    sc.nextLine();
                    sc.nextLine();
                }
                case 0 -> {// 退出
                    return;
                }

                default -> System.out.println("输入无效，请重新输入！");

            }
        }
    }
}