package com.dragon.service.impl;

import com.dragon.common.PMSResult;
import com.dragon.common.model.EmailInfo;
import com.dragon.pojo.Tuser;
import com.dragon.service.SendMsgService;
import com.dragon.service.UserService;
import com.dragon.util.EmailThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

@Service("sendMsgService")
public class SendMsgServiceImpl implements SendMsgService {

    @Autowired
    EmailThread emailUtils;
    @Autowired
    UserService userService;
    @Autowired
    EmailThread emailThread;
    @Autowired
    JedisPool jedisPool;

    @Override
    public PMSResult sendCheckNum(String account, String phone, String email) {

        Tuser user = userService.getUserByAccount(account);
        if (user == null) {
            return PMSResult.Fail("用户不存在");
        }
        //生成验证码
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        String chckStr = stringBuilder.toString();

        //redis缓存发送的验证码，(account:mail,checkNum)/(account:phone,checkNum)
        Jedis resource = jedisPool.getResource();

        //发送手机号验证
        if (phone != null && !"".equals(phone.trim())) {
            resource.set("checkNum:" + account + ":" + phone, chckStr);
            return PMSResult.Ok(true, "验证码发送成功");
        }

        //发送eamil
        if (email != null && !"".equals(email.trim())) {

            String emailDB = user.getEmail();
            if (emailDB == null || "".equals(emailDB)) {
                return PMSResult.Fail("您的账号未绑定邮箱!请使用手机号进行校验");
            }
            email = email.trim();
            if (!emailDB.equals(email)) {
                return PMSResult.Fail("输入的邮箱与绑定的邮箱不符，请确认");
            }

            String title = "用户重设密码验证";
            String msg = "<table border=0 cellpadding=\"0\" cellspacing=\"0\" width=\"90%\" style=\"letter-spacing: 1px;margin: auto\">\n" +
                    "    　　\n" +
                    "    <tr>\n" +
                    "        <td style=\"padding: 3px;\"> 您正在申请重新设置电力管理系统的登陆密码！</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td style=\"padding: 3px\"> 若您从未申请过该系统的登陆密码重设，请忽略该邮件。</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td style=\"padding: 3px\"> 校验码有效期为<span style=\"color: orangered\">10分钟</span>，若校验码失效，请重新申请</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td style=\"padding: 15px 3px;\"> 您本次申请的校验码为 <span style=\"color: deepskyblue\">" + chckStr + "</span></td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td style=\"padding: 3px\"> 感谢您的使用！</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td style=\"padding: 3px;padding-bottom: 20px\">快捷链接：<a href=\"http://localhost:9001\">>>电力管理系统<<</a></td>\n" +
                    "    </tr>\n" +
                    "    　\n" +
                    "</table>";
            emailThread.sendEmail(new EmailInfo(email, title, msg));
            //存储
            String key = "checkNum:" + account + ":" + email;
            resource.set(key, chckStr);
            resource.expire(key, 600);

            return PMSResult.Ok(true, "验证码发送成功");
        }

        return PMSResult.Fail("接收方式为空，请输入");
    }
}
