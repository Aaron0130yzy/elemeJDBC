package dao;

import entities.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;

public class CustomerDao {
    public Customer login(String id, String pwd) {
        String sqlQueryLang = "SELECT DISTINCT * FROM Customer WHERE idNumber ='" + id + "' AND password ='" + pwd + "'";
        Customer customer=null;
        try {
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            if (rs.next()) {
                customer = new Customer();
                customer.setIdNumber(rs.getString("idNumber"));
                customer.setPassword(rs.getString("password"));
                customer.setUsername(rs.getString("username"));
                customer.setAddress(rs.getString("address"));
                customer.setSex(rs.getBoolean("sex"));
                customer.setRealName(rs.getString("realName"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
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
        ProductDao pDao = new ProductDao();
        OrderDao oDao =new OrderDao();
        ItemDao iDao = new ItemDao();
        Scanner sc = new Scanner(System.in);
//        OrderDao oDao = new OrderDao();
        while (true) {
            // 显示菜单选项
            System.out.println("请选择操作：");
            System.out.println("1. 查看商品列表\t2. 查询指定商家菜单");
            System.out.println("3. 修改个人信息\t4. 下订单");
            System.out.println("0. 退出");

            // 获取用户输入
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    //查看商品列表
                    List<Product> list = pDao.showAllProduct();
                    System.out.println("商品id\t商家id\t商品名称\t商品价格\t商品折扣\t是否在售\t商品介绍");
                    for (Product p : list) {
                        System.out.println(p.getId() + "\t" + p.getBusinessId() + "\t" + p.getName() + "\t" +
                                p.getPrice() + "\t" + p.getDiscount() + "\t" + p.isOnSale() + "\t" + p.getDescription());
                    }
                    System.out.println("按回车键以继续...");
                    sc.nextLine();
                    sc.nextLine();
                }
                case 2 -> {
                    //查询指定商家的菜单
                    String id;
                    System.out.println("请输入商家id");
                    id = sc.next();
                    List<Product> list = pDao.showOnesAllProduct(id);
                    if (list.isEmpty()) {
                        System.out.println("无效id，请检查输入！");
                    } else {
                        String name=null;
                        try {
                            // 创建查询语句
                            String query = "SELECT name FROM business WHERE id = "+id;
                            // 执行查询
                            ResultSet rs = connection.executeQuery(query);
                            // 处理查询结果
                            while (rs.next()){
                                name = rs.getString("name");
                            }
                        } catch (SQLException e) {
                            System.out.println("查询失败！"+e);
                        }
                        System.out.println("商家 "+ name + " 的菜单：");
                        System.out.println("商品id\t商品名称\t商品价格\t商品折扣\t是否在售\t商品介绍");
                        for (Product p : list) {
                            System.out.println(p.getId() + "\t" + p.getName() + "\t" + p.getPrice() + "\t" + p.getDiscount()
                                    + "\t" + p.isOnSale() + "\t" + p.getDescription());
                        }
                    }
                    System.out.println("按回车键以继续...");
                    sc.nextLine();
                    sc.nextLine();

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
                    //下订单
                    List<ProductPair> productPairs = new ArrayList<>();
                    List<Integer> notForSale = new ArrayList<>();
                    List<Integer> invalid = new ArrayList<>();
                    List<ProductPair> ready = new ArrayList<>();
                    Product product;
                    BigDecimal sum=new BigDecimal(0);
                    BigDecimal price = new BigDecimal(0);
                    int i=0,productId,quantity;
                    String input;
                    System.out.println("开始录入订单内容，输入#以结束：");
                    while(true) {
                        System.out.print("请输入第 " + (i+1) + " 个商品ID: ");
                        input = sc.next();
                        if(input.equals("#")) break;
                        try{
                            productId = Integer.parseInt(input);
                        }
                        catch (Exception e){
                            System.out.println("无效输入！请重新输入！");
                            continue;
                        }
                        System.out.print("请输入商品数量: ");
                        try{
                            quantity = sc.nextInt();
                            if(quantity<=0){
                                System.out.println("无效的数字！请重新输入！");
                                continue;
                            }
                        }
                        catch (Exception e){
                            System.out.println("无效输入！请重新输入！");
                            continue;
                        }
                        i++;
                        ProductPair productPair = new ProductPair(productId, quantity, price);
                        productPairs.add(productPair);
                    }

                    for(ProductPair pp : productPairs){//检查输入的商品id状况以及计算价格
                        product = pDao.searchId(String.valueOf(pp.getId()));
                        if(product==null){//查不到，则认为id无效
                            invalid.add(pp.getId());
                        }
                        else if(!product.isOnSale()){//不在售商品
                            notForSale.add(pp.getId());
                        }
                        else {//剩下的认为是正常的
                            price =product.getPrice().multiply(product.getDiscount());
                            ProductPair productPair2 = new ProductPair(pp.getId(), pp.getQuantity(),price);
                            ready.add(productPair2);
                            sum=sum.add(price.multiply(BigDecimal.valueOf(pp.getQuantity())));//计入到总价里
                        }
                    }
                    if(!notForSale.isEmpty()){
                        System.out.println("以下商品id不在售：");
                        for(Integer a : notForSale){
                            System.out.println(a);
                        }
                    }
                    if(!invalid.isEmpty()){
                        System.out.println("以下id无效：");
                        for(Integer a : invalid){
                            System.out.println(a);
                        }
                    }
                    if(!ready.isEmpty()){
                        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                        System.out.println("以下商品准备下单：");
                        System.out.println("商品id\t数量\t价格    小计");
                        for(ProductPair pp :ready){
                            System.out.println(pp.getId()+"\t"+pp.getQuantity()+"\t"+decimalFormat.format(pp.getPrice())
                                    +"\t"+decimalFormat.format(pp.getPrice().multiply(BigDecimal.valueOf(pp.getQuantity()))));
                        }

                        System.out.println("总价：￥" + decimalFormat.format(sum));

                        System.out.println("\n确认下单吗？输入y以确认，输入其它键取消：");
                        boolean isSuccess = true;
                        if(sc.next().equals("y")){
                            Orders order2=oDao.create(idNumber);
                            if(order2!=null){
                                System.out.println("创建成功！订单id："+order2.getId());
                                for(ProductPair pp : ready){
                                    if(!iDao.create(order2.getId(), pp.getId(),pp.getPrice(), pp.getQuantity())){
                                        System.out.println("创建订单条目失败！");
                                        isSuccess=false;
                                        break;
                                    }
                                }
                            }
                            if(isSuccess){
                                System.out.println("您的订单已创建成功！");
                            }
                            else{
                                if(oDao.delete(String.valueOf(order2.getId()))){
                                    System.out.println("订单撤回成功");
                                }
                                else{
                                    System.out.println("订单撤回失败！请联系管理员");
                                }
                            }
                            System.out.println("按回车键继续...");
                        }
                        else{
                            System.out.println("操作已取消，按回车键继续...");
                        }
                        sc.nextLine();
                        sc.nextLine();
                    }
                    else{
                        System.out.println("当前订单无可下单商品！请返回重试！");
                    }

                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("输入无效，请重新输入！");
            }
        }
    }
}
