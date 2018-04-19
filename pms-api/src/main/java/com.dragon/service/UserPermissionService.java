package com.dragon.service;

import com.dragon.pojo.Tuserpermission;

public interface UserPermissionService {

    Tuserpermission getUserByAccount(String account);

    Tuserpermission getAdminByAccount(String account);
}
