package com.dragon.elect_use;

import org.quartz.*;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class GenDataJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDetail jobDetail = context.getJobDetail();
        JobDataMap dataMap = jobDetail.getJobDataMap();

        List<String> list = (List) dataMap.get("dormList");
        Statement stmt = (Statement) dataMap.get("stmt");
        float price = (Float) dataMap.get("price");
        // 参数3-- 写入时间
        String date = (String) dataMap.get("date");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date oldDate = formatter.parse(date, pos);

        System.out.println("生成：" + date + " 的数据中！");

        String sql = "insert into tday_use(dormitoryid, delect_use, date, dfee) VALUES ";
        // 遍历宿舍
        for (int i = 0; i < list.size(); i++) {
            // 参数1--宿舍id
            String dormId = list.get(i);

            // 参数2--生成随机 每天耗电量
            float day_use = 0.0f;
            Random random = new Random();
            if (oldDate.getMonth() >= 6 && oldDate.getMonth() <= 8) {
                day_use = random.nextFloat() * 12;
            } else {
                day_use = random.nextFloat() * 8;
            }

            //参数4--dfee 根据 用电量 和 电价 计算电费
            float dfee = day_use * 0.6f;

            if (i != list.size() - 1) {
                sql += "('" + dormId + "'," + day_use + ",'" + date + "'," + dfee + "),";
            } else {
                sql += "('" + dormId + "'," + day_use + ",'" + date + "'," + dfee + ")";
            }
        }

        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 设置 下次插入时间 day+1
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate);
        cal.add(Calendar.DAY_OF_MONTH, 1); //newDate 加一天
        //明天
        String nextDate = formatter.format(cal.getTime());
        // 保存时间
        dataMap.put("date", nextDate);

        //统计一个月数据, 并删除 tday)use 1个月前的数据,即保留上个月和这个月的数据
        // 31 30 > 1
        if (oldDate.getDate() > cal.get(Calendar.DATE)) {
            String saveDaySql = "{call saveMonthElectUse()}";
            CallableStatement callStmt = null;
            try {
                callStmt = stmt.getConnection().prepareCall(saveDaySql);
                boolean done = callStmt.execute();
                System.out.println("monthDone：" + done);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    callStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
