package com.dragon.service.impl;

import com.dragon.mapper.TdayUseMapper;
import com.dragon.mapper.TmonUseMapper;
import com.dragon.pojo.TdayUse;
import com.dragon.pojo.TdayUseExample;
import com.dragon.pojo.TmonUse;
import com.dragon.pojo.TmonUseExample;
import com.dragon.service.ElectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("electService")
public class ElectServiceImpl implements ElectService {

    @Autowired
    private TdayUseMapper dayUseMapper;
    @Autowired
    private TmonUseMapper monUseMapper;

    @Override
    public List<TdayUse> getDayUseByDormId(Integer dormId, String flag) {

        if (dormId == null) {
            return null;
        }
        List<TdayUse> list = new ArrayList<>();
        TdayUseExample example = new TdayUseExample();
        example.setOrderByClause("date asc");
        TdayUseExample.Criteria criteria = example.createCriteria();
        criteria.andDormitoryidEqualTo(dormId);
        // 当月1号
        Calendar instance = Calendar.getInstance();
        String dateStr = instance.get(Calendar.YEAR) + "-" + (instance.get(Calendar.MONTH) + 1) + "-01 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date monFirstDay = simpleDateFormat.parse(dateStr, pos);
        //上个月数据
        if ("lastMon".equals(flag)) {
            criteria.andDateLessThan(monFirstDay);
        }
        //当月数据
        else {
            criteria.andDateGreaterThanOrEqualTo(monFirstDay);
        }

        return dayUseMapper.selectByExample(example);
    }

    @Override
    public List<TmonUse> getMonUseByDormId(Integer dormId, String flag) {

        if (dormId == null) {
            return null;
        }
        TmonUseExample example = new TmonUseExample();
        example.setOrderByClause("date asc");
        TmonUseExample.Criteria criteria = example.createCriteria();
        criteria.andDormitoryidEqualTo(dormId);

        // 当前年的 1月
        String dateStr = Calendar.getInstance().get(Calendar.YEAR) + "-01-01 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date yearFirstMon = simpleDateFormat.parse(dateStr, pos);
        //上一年数据
        if ("lastYear".equals(flag)) {
            criteria.andDateLessThan(yearFirstMon);
        }
        //当前年数据
        else {
            criteria.andDateGreaterThanOrEqualTo(yearFirstMon);
        }
        return monUseMapper.selectByExample(example);
    }


    @Override
    public List<TdayUse> getAllDayUse(String flag) {

        List<TdayUse> list = new ArrayList<>();
        TdayUseExample example = new TdayUseExample();
        example.setOrderByClause("date asc");
        TdayUseExample.Criteria criteria = example.createCriteria();
        // 当月1号
        Calendar instance = Calendar.getInstance();
        String dateStr = instance.get(Calendar.YEAR) + "-" + (instance.get(Calendar.MONTH) + 1) + "-01 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date monFirstDay = simpleDateFormat.parse(dateStr, pos);
        //上个月数据
        if ("lastMon".equals(flag)) {
            criteria.andDateLessThan(monFirstDay);
        }
        //当月数据
        else {
            criteria.andDateGreaterThanOrEqualTo(monFirstDay);
        }
        return dayUseMapper.selectSumUseByExample(example);
    }

    @Override
    public List<TmonUse> getAllMonUse(String flag) {

        TmonUseExample example = new TmonUseExample();
        example.setOrderByClause("date asc");
        TmonUseExample.Criteria criteria = example.createCriteria();

        // 当前年的 1月
        String dateStr = Calendar.getInstance().get(Calendar.YEAR) + "-01-01 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date yearFirstMon = simpleDateFormat.parse(dateStr, pos);
        //上一年数据
        if ("lastYear".equals(flag)) {
            criteria.andDateLessThan(yearFirstMon);
        }
        //当前年数据
        else {
            criteria.andDateGreaterThanOrEqualTo(yearFirstMon);
        }
        return monUseMapper.selectSumUseByExample(example);
    }

}
