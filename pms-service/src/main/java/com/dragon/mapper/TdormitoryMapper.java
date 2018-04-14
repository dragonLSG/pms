package com.dragon.mapper;

import com.dragon.pojo.Tdormitory;
import com.dragon.pojo.TdormitoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TdormitoryMapper {
    int countByExample(TdormitoryExample example);

    int deleteByExample(TdormitoryExample example);

    int deleteByPrimaryKey(Integer dormitoryid);

    int insert(Tdormitory record);

    int insertSelective(Tdormitory record);

    List<Tdormitory> selectByExample(TdormitoryExample example);

    Tdormitory selectByPrimaryKey(Integer dormitoryid);

    int updateByExampleSelective(@Param("record") Tdormitory record, @Param("example") TdormitoryExample example);

    int updateByExample(@Param("record") Tdormitory record, @Param("example") TdormitoryExample example);

    int updateByPrimaryKeySelective(Tdormitory record);

    int updateByPrimaryKey(Tdormitory record);
}