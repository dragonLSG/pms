package com.dragon.mapper;

import com.dragon.pojo.Tuserpermission;
import org.springframework.stereotype.Component;

@Component
public interface TuserpermissionMapper {

    Tuserpermission selectByPrimaryKey(String account);

}