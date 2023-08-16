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
        Admin admin = null;
        try {//进行查询
//            rs = statement.executeQuery();
            ResultSet rs = connection.executeQuery(sql);
            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setPassword(rs.getString("password"));
                admin.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            return null;
        }
        return admin;
    }

    public void regist(String name, String pwd) {
        String newId ;
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
                case 1 -> {//查询商家
                    System.out.println("输入商家id，输入*查询所有商家：");
                    String id = sc.next();
                    if(id.equals("*")){//打印所有商家信息
                        List<Business> list ;
                        list = bDao.showAllBusiness();
                        System.out.println("商家id\t商家名称\t商家密码\t商家地址\t商家号码\t商家介绍");
                        for (Business b : list) {
                            // 输出 Business 对象的所有属性值
                            System.out.println(b.getId() + "\t" + b.getName() + "\t" + b.getPassword() + "\t"
                                    + b.getAddress() + "\t" + b.getPhoneNumber() + "\t" + b.getDescription());
                        }
                    }
                    else{
                        business=bDao.search(id);
                        if (business == null) {
                            System.out.println("商家id " + id + " 不存在！");
                        } else {
                            System.out.println("商家id：" + business.getId());
                            System.out.println("商家名：" + business.getName());
                            System.out.println("商家密码：" + business.getPassword());
                            System.out.println("商家地址：" + business.getAddress());
                            System.out.println("商家电话：" + business.getPhoneNumber());
                            System.out.println("商家描述：" + business.getDescription());
                        }
                    }
                    System.out.println("按回车键继续...");
                    sc.nextLine();
                    sc.nextLine();
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
                    bDao.regist(business.getName(),business.getPassword(),business.getAddress(),
                            business.getPhoneNumber(),business.getDescription());
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
                    System.out.println("输入用户身份证号，输入*查询所有用户：");
                    String id = sc.next();
                    if(id.equals("*")){//打印所有用户信息
                        List<Customer> list ;
                        list = cDao.showAllCustomer();
                        System.out.println("用户身份证\t用户名称\t用户密码\t用户地址\t用户电话号码\t用户真名");
                        for (Customer c : list) {
                            // 输出 Customer 对象的所有属性值
                            System.out.println(c.getIdNumber() + "\t" + c.getUsername() + "\t" + c.getPassword() +
                                    "\t" + c.getAddress() + "\t" + c.getPhoneNumber() + "\t" + c.getRealName());
                        }
                    }
                    else {
                        customer = cDao.search(id);
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
                    customer.setIdNumber(sc.next());
                    cDao.regist(customer.getUsername(),customer.getPassword(),customer.getAddress(),customer.getSex(),
                            customer.getPhoneNumber(),customer.getRealName(),customer.getIdNumber());
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