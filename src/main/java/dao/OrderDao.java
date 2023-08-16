package dao;
import entities.*;

import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class OrderDao {
    public Orders create(String cid){
        Orders order=null;
        LocalDateTime currentTime = LocalDateTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String sqlQueryLang = "INSERT INTO Orders (customerId, condition, time) VALUES ("+ cid + ", 1 , '"+formattedTime+"')";
        try{
            connection.modify(sqlQueryLang);
            sqlQueryLang = "SELECT id FROM Orders WHERE customerId = " + cid + " AND time = '" + formattedTime +"'";
            ResultSet rs = connection.executeQuery(sqlQueryLang);
            while (rs.next()) {
                order = new Orders();
                order.setId(rs.getInt("id"));
                // 找到新建的orderid
            }
        }
        catch (Exception e){
            System.out.println("插入失败！"+e);
            return null;
        }
        return order;
    }
}
