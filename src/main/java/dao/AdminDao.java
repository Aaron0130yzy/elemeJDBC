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

    public String regist(String name, String pwd) throws SQLException {
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
}