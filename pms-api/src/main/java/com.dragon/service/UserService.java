package com.dragon.service;

import com.dragon.common.DataList;
import com.dragon.common.model.User;
import com.dragon.pojo.Tuser;

public interface UserService {

    DataList findUsersByArgs(User user) throws Exception;

    Integer delUser(String accounts) throws Exception;

    Tuser getUserByAccount(String account);

}
