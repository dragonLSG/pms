package com.dragon.service;

import com.dragon.common.DataList;
import com.dragon.pojo.Tdormitory;
import org.springframework.stereotype.Component;

@Component
public interface DormService {

    DataList findDormByArgs(Integer pageNum, Integer pageSize, String dormName) throws Exception;

    Integer delDorm(String dormIds) throws Exception;

    Tdormitory findDormByName(String dormName);
}
