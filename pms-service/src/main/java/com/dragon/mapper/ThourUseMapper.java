package com.dragon.mapper;

import com.dragon.pojo.ThourUse;
import com.dragon.pojo.ThourUseExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ThourUseMapper {
    int countByExample(ThourUseExample example);

    int deleteByExample(ThourUseExample example);

    int insert(ThourUse record);

    int insertSelective(ThourUse record);

    List<ThourUse> selectByExample(ThourUseExample example);

    int updateByExampleSelective(@Param("record") ThourUse record, @Param("example") ThourUseExample example);

    int updateByExample(@Param("record") ThourUse record, @Param("example") ThourUseExample example);
}