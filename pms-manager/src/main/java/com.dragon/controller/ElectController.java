package com.dragon.controller;

import com.dragon.common.PMSResult;
import com.dragon.pojo.TdayUse;
import com.dragon.pojo.TmonUse;
import com.dragon.service.ElectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ElectController {

    @Autowired
    private ElectService electService;

    @GetMapping("/getDayUse")
    public PMSResult getDayUseData(String flag) {

        List<TdayUse> list = null;
        list = electService.getAllDayUse(flag);

        return PMSResult.Ok(list, "success");
    }

    @GetMapping("/getMonUse")
    public PMSResult getMonUseData(String flag) {

        List<TmonUse> list = null;
        list = electService.getAllMonUse(flag);

        return PMSResult.Ok(list, "success");

    }
}
