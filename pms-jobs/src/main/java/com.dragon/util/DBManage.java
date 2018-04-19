package com.dragon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManage {
    public static final String url = "jdbc:mysql://localhost:3306/pms";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "dragon";

    public static Connection conn = null;

    public static Connection getConnection() {

        try {
            Class.forName(name);// 指定连接类型
            conn = DriverManager.getConnection(url, user, password);// 获取连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
