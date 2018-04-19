package com.dragon.genData;

import com.dragon.util.DBManage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenNewDorm {

    public static void main(String[] args) {

        Connection conn = DBManage.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        String[] A = {"东", "西"};
        String[] B = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
        String sql = "";
        /*生成数据*/
        for (int i = 0; i <= 1; i++) {
            String a = A[i];
            for (int j = 0; j <= 11; j++) {
                for (int k = 200; k <= 700; k += 100) {
                    for (int p = 1; p <= 60; p++) {
                        sql = "insert into Tdormitory(dormitoryid,dormitoryName,balance) values(" + (i + 1) + (j + 1) + (k + p) + ",'" + A[i] + B[j] + (k + p) + "'," + 0 + ")";
                        try {
                            stmt.execute(sql);
                        } catch (SQLException e) {
                            System.out.println("cannot insert: values(" + (i + 1) + (j + 1) + (k + p) + ",'" + A[i] + B[j] + (k + p) + "'," + 0 + ")");
                            continue;
                        }
                    }
                }
            }
        }
        System.out.println("finish all");
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBManage.close();
    }
}
