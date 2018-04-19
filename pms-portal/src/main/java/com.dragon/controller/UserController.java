package com.dragon.controller;

import com.dragon.common.DataList;
import com.dragon.common.PMSResult;
import com.dragon.common.model.User;
import com.dragon.pojo.Tuser;
import com.dragon.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes = "根据url的参数来获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账号", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "dormname", value = "宿舍号", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "Long", paramType = "query")
    })
    @GetMapping()
    public PMSResult listUsersByArgs(User user) {
        DataList list = null;
        try {
            list = userService.findUsersByArgs(user);
        } catch (Exception e) {
            return PMSResult.Fail();
        }
        return PMSResult.Ok(list, "success");
    }

    @ApiOperation(value = "删除用户", notes = "删除account对应的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accounts", value = "账号集合,如'12,34,56'", dataType = "String", paramType = "query"),
    })
    @DeleteMapping("/{accounts}")
    @RequiresPermissions("user:del:all")
    public PMSResult delUsers(
            @PathVariable(value = "accounts") String accounts) {
        Integer num = null;
        try {
            num = userService.delUser(accounts);
        } catch (Exception e) {
            e.printStackTrace();
            return PMSResult.Fail();
        }
        return PMSResult.Ok(num, "删除成功");
    }


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


}
