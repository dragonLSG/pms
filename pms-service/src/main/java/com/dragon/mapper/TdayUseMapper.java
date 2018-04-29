package com.dragon.mapper;

import com.dragon.pojo.TdayUse;
import com.dragon.pojo.TdayUseExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TdayUseMapper {

    int countByExample(TdayUseExample example);

    int deleteByExample(TdayUseExample example);

    int insert(TdayUse record);

    int insertSelective(TdayUse record);

    List<TdayUse> selectByExample(TdayUseExample example);

    List<TdayUse> selectSumUseByExample(TdayUseExample example);

    int updateByExampleSelective(@Param("record") TdayUse record, @Param("example") TdayUseExample example);

    int updateByExample(@Param("record") TdayUse record, @Param("example") TdayUseExample example);
}