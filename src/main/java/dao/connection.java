package dao;

import java.sql.*;


public class connection {
    static public Connection getConnection() {//连接数据库
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            System.out.println("连接成功1");
        } catch (Exception e) {
            System.out.println("连接失败1");
        }
        String connectionUrl =
                "jdbc:sqlserver://AARON的钛钽PLUS:1433;"
                        + "databaseName=eleme;"
                        + "user=sa;"
                        + "password=123456;"
                        + "trustServerCertificate=true";
        Connection con = null;//处理与特定数据库的连接


        try {
            con = DriverManager.getConnection(connectionUrl);
//            System.out.println("连接成功2");
        } catch (SQLException e) {
            System.out.println("请输入正确的表名" + e);
            System.out.println("连接失败2");
        }
        return con;
    }


    public static Statement getStatement() throws SQLException {
        Connection con = getConnection();
        return con.createStatement();
    }

    public static ResultSet executeQuery(String s) throws SQLException {
        return getStatement().executeQuery(s);
    }

    public static void modify(String s) throws SQLException {
        getStatement().executeUpdate(s);
    }
}





