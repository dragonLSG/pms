package com.dragon.mapper;

import com.dragon.pojo.Tfeedback;
import com.dragon.pojo.TfeedbackExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TfeedbackMapper {
    int countByExample(TfeedbackExample example);

    int deleteByExample(TfeedbackExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(Tfeedback record);

    int insertSelective(Tfeedback record);

    List<Tfeedback> selectByExample(TfeedbackExample example);

    Tfeedback selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") Tfeedback record, @Param("example") TfeedbackExample example);

    int updateByExample(@Param("record") Tfeedback record, @Param("example") TfeedbackExample example);

    int updateByPrimaryKeySelective(Tfeedback record);

    int updateByPrimaryKey(Tfeedback record);
}