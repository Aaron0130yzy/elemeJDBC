package dao;

import entities.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusinessDao {
    Scanner sc = new Scanner(System.in);
    ProductDao pDao = new ProductDao();
    Product product = new Product();
    ItemDao iDao = new ItemDao();
    public Business login(String id, String pwd) {
        String sqlQueryLang = "SELECT DISTINCT * FROM Business WHERE id ='" + id + "' AND password ='" + pwd + "'";
        Business business=null;
        try {
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            if (rs.next()) {
                business = new Business();
                business.setId(Integer.parseInt(rs.getString("id")));
                business.setPassword(rs.getString("password"));
                business.setName(rs.getString("name"));
                business.setAddress(rs.getString("address"));
                business.setDescription(rs.getString("description"));
                business.setPhoneNumber(rs.getString("phoneNumber"));
            }
        } catch (SQLException e) {
            return null;
        }
        return business;
    }

    public void regist(String name, String pwd, String add, String pnum, String des) {
        String newId;
        String sqlQueryLang = "INSERT INTO Business(name,password,address,phoneNumber,description) VALUES('"
                + name + "','" + pwd + "','" + add
                + "','" + pnum + "','" + des + "')";
        try {
            connection.modify(sqlQueryLang);
            sqlQueryLang = "SELECT id FROM Business WHERE name = '" + name+"'";
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

    public void delete(String id) {
        String sqlQueryLang = "DELETE FROM Business WHERE id = '" + id + "'";
        try {
            connection.modify(sqlQueryLang);
            System.out.println("删除成功！");
        } catch (SQLException e) {
            System.out.println("删除失败！"+e);
        }
    }

    public List<Business> showAllBusiness(){
        String sqlQueryLang = "SELECT  * FROM Business";
        var lst = new ArrayList<Business>();
        try{
            ResultSet rs = connection.executeQuery(sqlQueryLang);
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
        } catch (SQLException e) {
            System.out.println("查询失败！"+e);
        }
        return lst;

    }

    //根据id查询商户
    public Business search(String id){
        String sqlQueryLang = "SELECT DISTINCT * FROM Business WHERE id ='" + id + "'";
        Business business = null;
        try{
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                business = new Business();
                business.setId(Integer.parseInt(rs.getString("id")));
                business.setPassword(rs.getString("password"));
                business.setName(rs.getString("name"));
                business.setAddress(rs.getString("address"));
                business.setDescription(rs.getString("description"));
                business.setPhoneNumber(rs.getString("phoneNumber"));
            }
            return business;
        } catch (SQLException e) {
            System.out.println("查询失败！"+e);
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
            System.out.println("7. 完成订单   \t0. 退出");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    //查询自家菜品
                    List<Product> list = pDao.showOnesAllProduct(id);
                    System.out.println("商品id\t商品名称\t商品价格\t商品折扣\t是否在售\t商品介绍");
                    for (Product p : list) {
                        // 输出 Product 对象的所有属性值
                        System.out.println(p.getId() + "  " + p.getName() + "  " + p.getPrice() + "  " + p.getDiscount()
                                + "  " + p.isOnSale() + "  " + p.getDescription());
                    }
                    System.out.println("按回车键以继续...");
                    sc.nextLine();
                    sc.nextLine();

                }
                case 2 -> {
                    //添加菜品
                    System.out.println("请输入商品名：");
                    product.setName(sc.next());
                    System.out.println("请输入价格：");
                    product.setPrice(sc.nextBigDecimal());
                    System.out.println("请输入折扣：");
                    product.setDiscount(sc.nextBigDecimal());
                    System.out.println("请输入在售状态，0表示不在售，其它任意字符表示在售：");
                    String onSale=sc.next();
                    product.setOnSale(!onSale.equals("0"));
                    System.out.println("请输入商品描述：");
                    product.setDescription(sc.next());
                    pDao.add(id,product.getName(),product.getDiscount(),product.isOnSale(), product.getDescription(),
                            product.getPrice());

                }
                case 3 -> {
                    //修改菜品信息
                    System.out.println("请输入要修改的商品id：");
                    String pid = sc.next();
                    System.out.println("请输入商品名称：");
                    product.setName(sc.next());
                    System.out.println("请输入商品价格：");
                    product.setPrice(sc.nextBigDecimal());
                    System.out.println("请输入商品折扣：");
                    product.setDiscount(sc.nextBigDecimal());
                    System.out.println("请输入在售状态，0表示不在售，其它任意字符表示在售：");
                    String onSale=sc.next();
                    product.setOnSale(!onSale.equals("0"));
                    System.out.println("请输入商品介绍：");
                    product.setDescription(sc.next());
                    pDao.modify(pid,id,product.getName(),product.getPrice(),product.getDiscount(),product.isOnSale(),
                            product.getDescription());
                }
                case 4 -> {
                    //删除菜品
                    System.out.println("请输入想要删除的商品id：");
                    String pid = sc.next();
                    System.out.println("确认删除吗？请输入y以确认：");
                    if(sc.next().equals("y")){
                        pDao.delete(pid,id);
                    }
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
                    System.out.println("输入1查询未完成订单，输入2查询已完成订单：");
                    String choice2 = sc.next();
                    if(choice2.equals("1")){
                        List<Item> itemList =iDao.showOnesWaitingItem(id);
                        System.out.println("条目id\t商品id\t数量\t价格");
                        for(Item item : itemList){
                            System.out.println(item.getId() + "\t" + item.getProductId() + "\t" + item.getAmount()
                                    + "\t" + item.getPrice().multiply(BigDecimal.valueOf(item.getAmount())));
                        }
                        System.out.println("按回车键以继续...");
                    }
                    else if (choice2.equals("2")) {
                        System.out.println("以下是最近20条已完成订单：");
                        int i=0;
                        List<Item> itemList =iDao.showOnesFinishedItem(id);
                        System.out.println("条目id\t商品id\t数量\t价格");
                        for(Item item : itemList){
                            System.out.println(item.getId() + "\t" + item.getProductId() + "\t" + item.getAmount()
                                    + "\t" + item.getPrice().multiply(BigDecimal.valueOf(item.getAmount())));
                            if(i++>=20) break;
                        }
                        System.out.println("按回车键以继续...");

                    }
                    else{
                        System.out.println("输入无效！\n按回车键继续...");
                    }
                    sc.nextLine();
                    sc.nextLine();
                }
                case 7 -> {
                    //完成订单
                    System.out.println("请输入要完成的订单条目id：");
                    String itemId=sc.next();
                    if(iDao.finish(itemId,id)){
                        System.out.println("id为"+itemId +"的订单条目已完成！\n按回车键继续...");
                    }
                    else{
                        System.out.println("修改操作未完成！\n按回车键继续...");
                    }
                    sc.nextLine();
                    sc.nextLine();
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("输入无效，请重新输入！");
            }
        }

    }
}
