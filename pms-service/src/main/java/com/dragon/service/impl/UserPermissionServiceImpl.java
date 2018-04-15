package com.dragon.service.impl;

import com.dragon.mapper.TuserpermissionMapper;
import com.dragon.pojo.Tuserpermission;
import com.dragon.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userPermissionService")
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private TuserpermissionMapper mapper;

    @Override
    public Tuserpermission getUserByAccount(String account) {

        if (account == null || "".equals(account.trim())) {
            return null;
        }
        //全为数字是账号
        if (account.matches("[0-9]+")) {
            Tuserpermission userpermission = mapper.selectByPrimaryKey(account);
            return userpermission;
        }
        return null;
    }

}
