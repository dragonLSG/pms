package com.dragon.genData;

import com.dragon.util.DBManage;
import com.dragon.util.PasswordHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenNewUsers {

    public static void main(String[] args) {

        Connection conn = DBManage.getConnection();
        Statement stmt = null;

        String[] A = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈",
                "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金",
                "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛",
                "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁",
                "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤"};

        String str = "嘉姬蒋时莺龚孝豪袁冰辰米坤贾海时汪毅蒋宁郑冰萱曾孝莲古前原米树先徐卓坚侯共时罗苗余固生吴丽瑜邓春韵成雁姿许爽郭虹兰孟芸孟言兰庞彬黄敬山方浩王显根顾玉瑜古铭欢简灵江荣黄银贵罗喻娅钱天蓉赖保渊邹江丹顾千鸣曾宇宋以雄阮加原简双原杨华然钟盈音胡冲伍春蕾张若芮周韵慧阮匀咏郭洁樊书妍蔡富颖吕莉露孔玉穗邓希贾蓉王雅慧崔伶蓝钟继洪封江员米君吕雁桂吴康梁天蕴曹应莎吴树洲蔡泽暖姚爽范璨樊众风毛友锦方碧欢钱阳侯莉音江美蕴汤瑞梁伯毅黄莉翔米雅露鲁婵音陆菊康姣许秉聪徐茂丰高泽良崔固浩谢月彦冯业才李璨风罗悦馨唐眉谢伯武鲁熙辰阮镇帅黄";
        char[] B = str.toCharArray();

        String account = "3114001101";
        String dormname = "";

        //默认手机号码
        String mobile = "12345678987";
        try {
            stmt = conn.createStatement();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        for (int i = 0; i < 1500; i++) {
            int Anum = (int) (Math.random() * 72);
            int Bnum = (int) (Math.random() * 255);
            int Cnum = (int) (Math.random() * 255);
            int Dnum = 1 + (int) (Math.random() * 8640);

            // 设置随机宿舍
            String sql1 = "select * from Tdormitory limit " + Dnum + ",1";
            try {
                ResultSet set = stmt.executeQuery(sql1);
                boolean flag = false;
                while (set.next()) {
                    dormname = set.getString(2);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //随机用户名
            String username = A[Anum] + B[Bnum] + B[Cnum];
            //生成密码 123456 加密后
            //账号 递增
            String password = PasswordHelper.encryptPassword(account, "123456");

            String sql2 = "insert into Tuser(username,account,passwd,dormitoryName,mobile,status) values('"
                    + username + "'," + account + ",'" + password + "','" + dormname + "'," + mobile + ",1)";
            try {
                stmt.execute(sql2);
            } catch (SQLException e) {
                System.out.println("insert失败：values('"
                        + username + "'," + account + ",'" + password + "','" + dormname + "'," + mobile + ",1)");
                continue;
            }

            //账号 递增
            account = Long.parseLong(account) + 1 + "";
        }
        System.out.println("finish all!");
        try {
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DBManage.close();
    }
}
