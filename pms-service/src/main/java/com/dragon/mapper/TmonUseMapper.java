package com.dragon.mapper;

import com.dragon.pojo.TmonUse;
import com.dragon.pojo.TmonUseExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TmonUseMapper {
    int countByExample(TmonUseExample example);

    int deleteByExample(TmonUseExample example);

    int insert(TmonUse record);

    int insertSelective(TmonUse record);

    List<TmonUse> selectByExample(TmonUseExample example);

    List<TmonUse> selectSumUseByExample(TmonUseExample example);

    int updateByExampleSelective(@Param("record") TmonUse record, @Param("example") TmonUseExample example);

    int updateByExample(@Param("record") TmonUse record, @Param("example") TmonUseExample example);
}