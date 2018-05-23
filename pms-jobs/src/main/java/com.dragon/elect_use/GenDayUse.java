package com.dragon.elect_use;

import com.dragon.util.DBManage;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenDayUse {

    private static Statement stmt = null;
    private static Connection conn = null;
    private static List<String> list = null;
    private static String date = "2018-05-22 00:00:00";
    private static float price = 0.0f;

    // 初始化
    public static void init() {
        System.out.println("QuartzTest 构造器 初始化");
        list = new ArrayList<String>();
        try {
            //暂时先放外面，若为1小时，放在job里面创建，使用后释放
            conn = DBManage.getConnection();
            stmt = conn.createStatement();

            //获取最新 电费单价price
            String sql = "select price from tunit_price where date=(select max(date) from tunit_price)";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                price = set.getFloat(1);
            }
            // 获取 宿舍列表
            String sql1 = "select dormitoryid from Tdormitory";
            set = stmt.executeQuery(sql1);
            while (set.next()) {
                list.add(set.getString(1));
            }
        } catch (SQLException e1) {
            System.out.println("获取宿舍列表失败！");
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {

        init();
        // 定义一个JobDetail
        JobDetail job = JobBuilder.newJob(GenDataJob.class) // 定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                .withIdentity("genDayDataJob", "group1") // 定义name/group
                .build();

        job.getJobDataMap().put("dormList", list);
        job.getJobDataMap().put("date", date);
        job.getJobDataMap().put("price", price);
        //暂时放外面
        job.getJobDataMap().put("stmt", stmt);

        // 触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("genDayDataTrigger", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(10).repeatForever()).build();

        // 创建scheduler
        Scheduler scheduler;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            // 加入这个调度
            scheduler.scheduleJob(job, trigger);
            // 启动
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
