package com.dragon.elect_use;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HourJob {

    public void execute(JobExecutionContext context) throws JobExecutionException {
//
//		JobDetail jobDetail = context.getJobDetail();
//		JobDataMap dataMap = jobDetail.getJobDataMap();
//
//		List<String> list = (List) dataMap.get("dormList");
//		Statement stmt = (Statement) dataMap.get("stmt");
//		String date = (String) dataMap.get("date");
//		int hour = (Integer) dataMap.get("hour");
//		// 参数3--设置 写入时间
//		String datetime = date +" "+ String.format("%02d", hour) + ":00";
//
//		System.out.println("生成：" + datetime + " 的数据中！");
//
//		String sql = "insert into thour_use(dormitoryid, helect_use, date) VALUES ";
//		// 遍历宿舍
//		for (int i = 0; i < list.size(); i++) {
//			// 参数1--宿舍id
//			String dormId = list.get(i);
//
//			// 参数2--生成随机 每小时耗电量
//			float hour_use = 0.0f;
//			Random random = new Random();
//			// 0~7:00 0.5/h
//			if (hour >= 0 && hour <= 7) {
//				hour_use = random.nextFloat() / 2;
//			} else {// 8~23:00 1/h
//				hour_use = random.nextFloat();
//			}
//
//			if (i != list.size() - 1) {
//				sql += "('" + dormId + "'," + hour_use + ",'" + datetime + "'),";
//			} else {
//				sql += "('" + dormId + "'," + hour_use + ",'" + datetime + "')";
//			}
//		}
//
//		try {
//			stmt.execute(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//统计1天的数据, 并删除 thour_use 当天数据
//		if (hour == 23) {
//			String saveDaysql = "{call saveDayElectUse()}";
//			CallableStatement callstmt = null;
//			try {
//				callstmt = stmt.getConnection().prepareCall(saveDaysql);
//				boolean done = callstmt.execute();
//				System.out.println("daydone："+done);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					callstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		// 设置 下次插入时间 hour+1
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		ParsePosition pos = new ParsePosition(0);
//		Date olddate = formatter.parse(datetime, pos);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(olddate);
//		cal.add(Calendar.HOUR_OF_DAY, 1); //newDate 加一个小时
//		String nextDate = cal.get(Calendar.YEAR) + "-" + String.format("%02d", cal.get(Calendar.MONTH) + 1) + "-"
//				+ String.format("%02d", cal.get(Calendar.DATE));
//		int nexthour = cal.get(Calendar.HOUR_OF_DAY);
//		// 保存时间
//		dataMap.put("date", nextDate);
//		dataMap.put("hour", nexthour);
//
//		//统计一个月数据, 并删除 tday)use 1个月前的数据,即保留上个月和这个月的数据
//		// 31 30 > 1
//		if(olddate.getDate() > cal.get(Calendar.DATE)){
//			String saveDaysql = "{call saveMonthElectUse()}";
//			CallableStatement callstmt = null;
//			try {
//				callstmt = stmt.getConnection().prepareCall(saveDaysql);
//				boolean done = callstmt.execute();
//				System.out.println("monthdone："+done);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					callstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
    }
}
