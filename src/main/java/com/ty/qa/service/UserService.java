package com.ty.qa.service;

import com.ty.qa.dao.LoginTicketDAO;
import com.ty.qa.dao.QuestionDao;
import com.ty.qa.dao.UserDao;
import com.ty.qa.model.LoginTicket;
import com.ty.qa.model.User;
import com.ty.qa.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.DateTimeException;
import java.util.*;

/**
 * created by TY on 2019/5/4.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    LoginTicketDAO loginTicketDAO;
    public User getUser(int id) {
        return userDao.selectByid(id);
    }

    public User selectByName(String name) {
        return userDao.selectByName(name);
    }


    /**
     * 注册功能   ，返回msg
     * @param username, password
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public Map<String, Object> register(String username, String password) {
        HashMap<String, Object> map = new HashMap<>();
        if(StringUtils.isEmpty(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isEmpty(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userDao.selectByName(username);

        if (user != null) {
            map.put("msg", "用户名已经被注册");
            return map;
        }

        user = new User();
        user.setName(username);
        //加盐
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeadUrl(head);
        user.setPassword(WendaUtil.MD5(password+user.getSalt()));
        userDao.addUser(user);

        // 登陆
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);

        return map;
    }

    public Map<String, Object> login(String username, String password) {
        HashMap<String, Object> map = new HashMap<>();
        if(StringUtils.isEmpty(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isEmpty(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userDao.selectByName(username);
        if (user == null) {
            map.put("msg", "用户名不存在");
            return map;
        }


        if (!WendaUtil.MD5(password+user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }

        //登陆成功后
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);

        map.put("userId", user.getId());
        return map;
    }
    /**  
     *    
     * @param userId
     * @return java.lang.String  
     */  
    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketDAO.addTicket(ticket);
        return ticket.getTicket();
    }

    public void logout(String ticket) {
        loginTicketDAO.updateStatus(ticket, 1);
    }



}
