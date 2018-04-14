package com.dragon.controller;


import com.dragon.pojo.Tuserpermission;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "/admin";
    }

    @GetMapping("/admin")
    public String toIndex() {
        return "admin";
    }


    @GetMapping("/login.html")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String login(Tuserpermission userInfo, boolean rememberMe, HttpServletRequest request) {
        System.out.println("rememberMe = " + rememberMe);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPasswd(), rememberMe);
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
                return "redirect:admin";
            }
            request.setAttribute("msg", msg);
            return "login";
        }
    }

}