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
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {
    @Autowired
    private UserService userService;
    @Autowired
    private SendMsgService sendMsgService;
    @Autowired
    private JedisPool jedisPool;

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
    public String doRegist(Tuser user, HttpServletRequest request) {
        int num = 0;
        try {
            num = userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "注册失败，请重新注册！");
            return "fail";
        }
        request.setAttribute("msg", "恭喜您成功注册到电力管理系统");
        return "success";
    }

    @GetMapping("/sendCheckNum")
    @ResponseBody
    public PMSResult sendCheckNum(String account, String phone, String email) {

        PMSResult result = sendMsgService.sendCheckNum(account, phone, email);

        return result;
    }

    @PostMapping("/resetPasswd")
    public String resetPasswd(Tuser user, String checkNum, HttpServletRequest request) {

        Jedis resource = jedisPool.getResource();
        String checkNumDB = "";
        if (user.getEmail() != null && !"".equals(user.getEmail())) {
            checkNumDB = resource.get("checkNum:" + user.getAccount() + ":" + user.getEmail());
        } else {
            checkNumDB = resource.get("checkNum:" + user.getAccount() + ":" + user.getMobile());
        }
        //检查校验码
        if (checkNum.equals(checkNumDB)) {
            userService.updateUser(user);
            request.setAttribute("msg", "您已成功重置密码，请使用新密码登陆系统！");
            return "success";
        }
        request.setAttribute("msg", "校验码错误，重置密码失败，请检查或重新获取校验码");
        return "fail";
    }
}
