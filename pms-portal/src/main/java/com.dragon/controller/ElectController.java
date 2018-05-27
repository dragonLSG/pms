package com.dragon.controller;

import com.dragon.common.PMSResult;
import com.dragon.pojo.TdayUse;
import com.dragon.pojo.Tdormitory;
import com.dragon.pojo.TmonUse;
import com.dragon.pojo.Tuser;
import com.dragon.service.DormService;
import com.dragon.service.ElectService;
import com.dragon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ElectController {

    @Autowired
    private UserService userService;
    @Autowired
    private DormService dormService;
    @Autowired
    private ElectService electService;

    @GetMapping("/getDayUse")
    public PMSResult getDayUseData(String account, String flag) {

        List<TdayUse> list = null;
        Integer dormId = getDormId(account);
        list = electService.getDayUseByDormId(dormId, flag);

        return PMSResult.Ok(list, "success");
    }

    @GetMapping("/getMonUse")
    public PMSResult getMonUseData(String account, String flag) {

        List<TmonUse> list = null;
        Integer dormId = getDormId(account);
        list = electService.getMonUseByDormId(dormId, flag);

        return PMSResult.Ok(list, "success");

    }

    private Integer getDormId(String account) {

        Tuser user = userService.getUserByAccount(account);
        String dormName = user.getDormitoryname();
        Tdormitory dorm = dormService.findDormByName(dormName);
        Integer dormitoryid = null;
        if (dorm != null) {
            dormitoryid = dorm.getDormitoryid();
        }
        return dormitoryid;
    }
}
