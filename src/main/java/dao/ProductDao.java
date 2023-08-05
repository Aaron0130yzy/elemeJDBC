package dao;

import entities.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDao {
    Product product=new Product();
    Scanner sc = new Scanner(System.in);

    public List<Product> showAllProduct(){
        String sqlQueryLang = "SELECT  * FROM Product";
        var lst = new ArrayList<Product>();
        try{
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                Product Product = new Product();
                Product.setId(Integer.parseInt(rs.getString("id")));
                Product.setName(rs.getString("name"));
                Product.setPrice(rs.getBigDecimal("price"));
                Product.setOnSale(rs.getBoolean("onSale"));
                Product.setDescription(rs.getString("description"));
                Product.setDiscount(rs.getBigDecimal("discount"));
                lst.add(Product);
            }
        } catch (SQLException e) {
            System.out.println("查询失败！"+e);
        }
        return lst;
    }

    public List<Product> showOnesAllProduct(String id) {
        String sqlQueryLang = "SELECT  * FROM Product WHERE businessId = '" + id + "'";
        var lst = new ArrayList<Product>();
        try{
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                Product Product = new Product();
                Product.setId(Integer.parseInt(rs.getString("id")));
                Product.setName(rs.getString("name"));
                Product.setPrice(rs.getBigDecimal("price"));
                Product.setOnSale(rs.getBoolean("onSale"));
                Product.setDescription(rs.getString("description"));
                Product.setDiscount(rs.getBigDecimal("discount"));
                lst.add(Product);
            }
        } catch (SQLException e) {
            System.out.println("查询失败！"+e);
        }
        return lst;
    }

    public void add(String bid, String name, BigDecimal dis, boolean onsale, String des, BigDecimal price) {
        String newId;
        int onsale2=0;
        if(onsale) onsale2=1;
        String sqlQueryLang = "INSERT INTO Product(businessId,name,price,onSale,discount,description) VALUES("
                 + "'" + bid + "','" + name + "'," + price + "," + onsale2 + "," + dis + ",'" + des + "')";
        try {
            connection.modify(sqlQueryLang);
            sqlQueryLang = "SELECT id FROM Product WHERE name = '" + name + "' AND businessId= '"+bid +"'";
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                newId = rs.getString("id");
            System.out.println("添加 " + name + " 成功！商品id为 " + newId);
            }
        } catch (SQLException e) {
            System.out.println("添加失败！"+e);
        }
    }

    public void delete(String id,String bid) {
        String sql = "SELECT businessId FROM Product WHERE id = " + id;
        try{
            ResultSet rs = connection.executeQuery(sql);
            if (rs.next()) {
                String bid2 = rs.getString("businessId");
                if (!bid.equals(bid2)) {
                    System.out.println("操作失败！不是你的商品！");
                    return;
                }
            } else {
                System.out.println("操作失败！id无效！");
                return;
            }
            String sqlQueryLang = "DELETE FROM Product WHERE id = " + id ;
            connection.modify(sqlQueryLang);
            System.out.println("删除成功！");
        } catch (SQLException e) {
            System.out.println("删除失败！"+e);
        }

    }

    public void modify(String id, String bid, String name, BigDecimal price, BigDecimal discount, boolean isOnSale,
                       String description) {
        String sql = "SELECT businessId FROM Product WHERE id = " + id ;
        try{
            ResultSet rs = connection.executeQuery(sql);
            if (rs.next()) {
                String bid2 = rs.getString("businessId");
                if (!bid.equals(bid2)) {
                    System.out.println("操作失败！不是你的商品！");
                    return;
                }
            } else {
                System.out.println("操作失败！id无效！");
                return;
            }
        } catch (SQLException e) {
            System.out.println("操作失败！"+e);
        }
        String sqlQueryLang = "UPDATE Product SET name = '" + name + "', price = '" + price + "', discount = '"
                + discount + "', OnSale = '" + isOnSale + "', description = '" + description + "' WHERE id = '" + id + "'";
        try {
            connection.modify(sqlQueryLang);
            System.out.println("修改成功！");
        } catch (SQLException e) {
            System.out.println("修改失败！"+e);
        }
    }
}
