package com.dragon.mapper;

import com.dragon.pojo.TunitPrice;
import com.dragon.pojo.TunitPriceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TunitPriceMapper {
    int countByExample(TunitPriceExample example);

    int deleteByExample(TunitPriceExample example);

    int deleteByPrimaryKey(Integer priceid);

    int insert(TunitPrice record);

    int insertSelective(TunitPrice record);

    List<TunitPrice> selectByExample(TunitPriceExample example);

    TunitPrice selectByPrimaryKey(Integer priceid);

    int updateByExampleSelective(@Param("record") TunitPrice record, @Param("example") TunitPriceExample example);

    int updateByExample(@Param("record") TunitPrice record, @Param("example") TunitPriceExample example);

    int updateByPrimaryKeySelective(TunitPrice record);

    int updateByPrimaryKey(TunitPrice record);
}