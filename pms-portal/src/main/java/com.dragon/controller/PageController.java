package com.dragon.controller;


import com.dragon.common.PMSResult;
import com.dragon.pojo.Tuser;
import com.dragon.pojo.Tuserpermission;
import com.dragon.service.SendMsgService;
import com.dragon.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @Autowired
    private UserService userService;
    @Autowired
    private SendMsgService emailService;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }

    @GetMapping("/login.html")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String login(Tuserpermission userInfo, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPasswd());
        String msg = "";
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            msg = "账号或密码出错";
        } catch (IncorrectCredentialsException e) {
            msg = "账号或密码出错";
        } catch (LockedAccountException e) {
            msg = "登录失败，该用户已被冻结";
        } catch (AuthenticationException e) {
            msg = "验证信息出错";
        } catch (Exception e) {
            msg = "系统内部出错，请稍后重试";
        } finally {
            if (msg == "") {
                return "redirect:index";
            }
            request.setAttribute("msg", msg);
            return "login";
        }
    }

    @PostMapping("/regUser")
    public String doRegist(Tuser user) {
        int num = 0;
        try {
            num = userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "regSuccess";
    }

    @GetMapping("/sendCheckNum")
    @ResponseBody
    public PMSResult sendCheckNum(String account, String phone, String email) {

        PMSResult result = emailService.sendCheckNum(account, phone, email);

        return result;
    }
}
