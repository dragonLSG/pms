package com.dragon.service;

import com.dragon.common.DataList;

public interface DormService {

    DataList findDormByArgs(Integer pageNum, Integer pageSize, String dormName) throws Exception;

    Integer delDorm(String dormIds) throws Exception;
}
