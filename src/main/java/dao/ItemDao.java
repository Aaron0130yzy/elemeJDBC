package dao;

import entities.*;

import java.math.BigDecimal;

public class ItemDao {
    public boolean create(int oid , int pid , BigDecimal price , int amount){
        String sqlQueryLang ="INSERT INTO item (orderId, productId, price, amount) VALUES ("
                + oid + "," + pid + "," + price + "," + amount + ")";
        try{
            connection.modify(sqlQueryLang);
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}
