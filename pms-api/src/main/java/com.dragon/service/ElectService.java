package com.dragon.service;

import com.dragon.pojo.TdayUse;
import com.dragon.pojo.TmonUse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ElectService {

    public List<TdayUse> getDayUseByDormId(Integer dormId, String flag);

    public List<TmonUse> getMonUseByDormId(Integer dormId, String flag);

    public List<TdayUse> getAllDayUse(String flag);

    public List<TmonUse> getAllMonUse(String flag);

}
