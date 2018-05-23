package com.dragon.controller;

import com.dragon.common.PMSResult;
import com.dragon.pojo.Tuser;
import com.dragon.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息", notes = "根据account获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", dataType = "String", paramType = "query"),
    })
    @GetMapping("/detail")
    public PMSResult getUserByAccount(String account) {

        Tuser user = userService.getUserByAccount(account);
        user.setPasswd("");
        return PMSResult.Ok(user, "success");
    }

    @PostMapping("/update")
    public PMSResult updateUserInfo(Tuser user) {
        return PMSResult.Ok();
    }

    @GetMapping("/checkAccount")
    public PMSResult checkUserExist(@RequestParam("account") String account) {
        Tuser user = userService.getUserByAccount(account);
        boolean exist = user == null ? false : true;
        return PMSResult.Ok(exist, "success");
    }

    @PostMapping()
    public PMSResult addUser(Tuser user) {
        Integer num = null;
        try {
            num = userService.addUser(user);
        } catch (Exception e) {
            return PMSResult.Fail("操作失败，请稍后重试！");
        }
        return PMSResult.Ok(num, "success");
    }


}
