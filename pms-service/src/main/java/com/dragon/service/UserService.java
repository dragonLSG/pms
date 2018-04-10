package com.dragon.service;

import com.dragon.common.DataList;
import com.dragon.common.model.User;

public interface UserService {

    DataList findUsersByArgs(User user) throws Exception;

    Integer delUser(String accounts) throws Exception;
}
