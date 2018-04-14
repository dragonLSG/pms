package com.dragon.shiro.realm;

import com.dragon.pojo.Tuserpermission;
import com.dragon.service.UserPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserPermissionService service;
    @Autowired
    private RedisManager redisManager;

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Tuserpermission userPerms = (Tuserpermission) principals.getPrimaryPrincipal();
        List<String> permissions = userPerms.getPermissions();
        if (permissions != null) {
            for (String permission : permissions) {
                authorizationInfo.addStringPermission(permission);
            }
        }
        return authorizationInfo;
    }

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String account = (String) token.getPrincipal();

        Tuserpermission user = null;
        if (account != null && account != "") {
            //查询redis中是否存储 user
            byte[] bytes = redisManager.get(account.getBytes());

            if (bytes != null && bytes.length > 0) {
                user = (Tuserpermission) SerializeUtils.deserialize(bytes);
            } else {
                //若没有存储,从数据库中取
                user = service.getUserByAccount(account);
                //若数据库中没有该账户
                if (user == null) {
                    throw new UnknownAccountException();
                }
                //若有该账户,将user存入redis
                redisManager.set(account.getBytes(), SerializeUtils.serialize(user), 3600);
            }
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getPasswd(), //密码
                ByteSource.Util.bytes(user.getAccount()),//salt
                getName()  //realm name
        );
        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getAccount());
        return authenticationInfo;
    }
}
