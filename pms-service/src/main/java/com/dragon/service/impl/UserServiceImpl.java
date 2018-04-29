package com.dragon.service.impl;

import com.dragon.common.DataList;
import com.dragon.common.PageInf;
import com.dragon.common.model.User;
import com.dragon.mapper.TuserMapper;
import com.dragon.pojo.Tuser;
import com.dragon.pojo.TuserExample;
import com.dragon.pojo.TuserExample.Criteria;
import com.dragon.service.UserService;
import com.dragon.util.PasswordHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private TuserMapper userMapper;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public DataList findUsersByArgs(User user) throws Exception {

        String username = user.getUsername();
        String account = user.getAccount();
        String dormitoryname = user.getDormitoryname();
        Byte status = user.getStatus();
        /* 分页 */
        PageHelper.startPage(user.getPageNum(), user.getPageSize());

        TuserExample example = new TuserExample();
        Criteria criteria = example.createCriteria();
        if (username != null && !"".equals(username)) {
            criteria.andUsernameEqualTo(username);
        }
        if (account != null && !"".equals(account)) {
            criteria.andAccountEqualTo(account);
        }
        if (dormitoryname != null && !"".equals(dormitoryname)) {
            criteria.andDormitorynameEqualTo(dormitoryname);
        }
        if (status != null && status != 2) {
            criteria.andStatusEqualTo(status);
        }

        List<Tuser> list = userMapper.selectByExample(example);
        PageInfo<Tuser> pageinfo = new PageInfo<Tuser>(list);

        return new DataList(list, new PageInf(pageinfo));
    }

    @Override
    public Integer delUser(String accounts) throws Exception {

        if (null == accounts) {
            return 0;
        }
        String[] ids = accounts.split(",");
        int num = 0;
        for (String id : ids) {
            num += userMapper.deleteByPrimaryKey(id);
        }
        return num;
    }

    @Override
    public Tuser getUserByAccount(String account) {
        Tuser user = null;

        if (account != null && account != "") {
            user = userMapper.selectByPrimaryKey(account);
        }

        return user;
    }

    @Override
    public Integer addUser(Tuser user) throws Exception {
        int num = 0;
        if (user != null) {
            String newPasswd = PasswordHelper.encryptPassword(user.getPasswd(), user.getAccount());
            user.setPasswd(newPasswd);
            num = userMapper.insertSelective(user);
        }
        return num;
    }

    @Override
    public Integer updateUser(Tuser user) {

        //删除checkNum key
        Jedis resource = jedisPool.getResource();
        if (user.getEmail() != null && user.getEmail() != "") {
            resource.del("checkNum:" + user.getAccount() + ":" + user.getEmail());
            user.setMobile(null);
        } else {
            resource.del("checkNum:" + user.getAccount() + ":" + user.getMobile());
            user.setEmail(null);
        }

        //加密密码
        String password = PasswordHelper.encryptPassword(user.getAccount(), user.getPasswd());
        user.setPasswd(password);
        //更新用户
        Integer num = userMapper.updateByPrimaryKeySelective(user);

        return num;
    }
}
