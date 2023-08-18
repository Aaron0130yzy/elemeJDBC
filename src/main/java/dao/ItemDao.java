package dao;


import entities.Item;
import entities.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    public boolean create(int oid , int pid , BigDecimal price , int amount){
        String sqlQueryLang ="INSERT INTO Item (orderId, productId, price, amount, condition) VALUES ("
                + oid + "," + pid + "," + price + "," + amount + "," + "1 )";
        try{
            connection.modify(sqlQueryLang);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean finish(String id,String bid){
        String sqlQueryLang = "SELECT p.businessId FROM item i JOIN product p ON i.productId = p.id WHERE i.id = "+id;
        String bid2 ;
        try{
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()){
                bid2 = rs.getString("businessId");
                if(bid==null){
                    System.out.println("此订单条目id无效！");
                    return false;
                }
                if(!(bid2.equals(bid))){
                    System.out.println("这个订单条目不属于你！");
                    return false;
                }
            }
        }
        catch (Exception e){
            System.out.println("操作失败！"+e);
        }
        sqlQueryLang = "UPDATE Item SET condition = 0 WHERE id = " + id;
        try{
            connection.modify(sqlQueryLang);
            return true;
        }
        catch (Exception e){
            System.out.println("修改条目失败！"+e);
            return false;
        }
    }

    public List<Item> showOnesFinishedItem(String id) {
        String sqlQueryLang = "SELECT i.* " +
                "FROM item i " +
                "JOIN Orders o ON i.orderId = o.id " +
                "JOIN Product p ON i.productId = p.id " +
                "WHERE p.businessId = " +id+
                "AND i.condition = 0" +
                "ORDER BY o.time DESC";

        var lst = new ArrayList<Item>();
        try{
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                Item item =new Item();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("orderId"));
                item.setProductId(rs.getInt("productId"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setAmount(rs.getInt("amount"));
                item.setCondition(rs.getBoolean("condition"));
                lst.add(item);
            }
        } catch (SQLException e) {
            System.out.println("查询失败！"+e);
        }
        return lst;
    }

    public List<Item> showOnesWaitingItem(String id) {
        String sqlQueryLang = "SELECT i.* " +
                "FROM item i " +
                "JOIN Orders o ON i.orderId = o.id " +
                "JOIN Product p ON i.productId = p.id " +
                "WHERE p.businessId = " +id+
                "AND i.condition = 1" +
                "ORDER BY o.time DESC";

        var lst = new ArrayList<Item>();
        try{
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                Item item =new Item();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("orderId"));
                item.setProductId(rs.getInt("productId"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setAmount(rs.getInt("amount"));
                item.setCondition(rs.getBoolean("condition"));
                lst.add(item);
            }
        } catch (SQLException e) {
            System.out.println("查询失败！"+e);
        }
        return lst;
    }
}
