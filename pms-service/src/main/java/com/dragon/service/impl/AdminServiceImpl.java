package com.dragon.service.impl;

import com.dragon.mapper.TadminMapper;
import com.dragon.pojo.Tadmin;
import com.dragon.pojo.TadminExample;
import com.dragon.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TadminMapper adminMapper;

    @Override
    public Tadmin getAdminByAccount(String account) {

        if (account == null || account == "") {
            return null;
        }
        //全为数字是账号
        if (account.matches("[0-9]+")) {
            return adminMapper.selectByPrimaryKey(account);
        } else {
            TadminExample example = new TadminExample();
            TadminExample.Criteria criteria = example.createCriteria();
            criteria.andAdminnameEqualTo(account);
            return (Tadmin) adminMapper.selectByExample(example);
        }
    }
}
