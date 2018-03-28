package com.zhudi.service;

import com.zhudi.Utils.ToutiaoUtil;
import com.zhudi.dao.LoginTicketDAO;
import com.zhudi.dao.UserDAO;
import com.zhudi.model.LoginTicket;
import com.zhudi.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.*;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private LoginTicketDAO loginTicketDAO;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public Map<String, Object> register(String username, String password){
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isBlank(username)){
            map.put("msgname", "用户名不能为空~！");
            return map;
        }

        if(StringUtils.isBlank(password)){
            map.put("msgpwd", "密码不能为空~！");
            return map;
        }

        User user = userDAO.selectByName(username);
        if(user != null){
            map.put("msgname", "用户名已经注册了~~~！");
            return map;
        }

        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        String head = String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000));
        user.setHeadUrl(head);
        user.setPassword(ToutiaoUtil.MD5(password + user.getSalt()));
        userDAO.addUser(user);

        //ticket
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;

    }


    public Map<String, Object> login(String username, String password){
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isBlank(username)){
            map.put("msgname", "用户名不能为空~！");
            return map;
        }

        if(StringUtils.isBlank(password)){
            map.put("msgpwd", "密码不能为空~！");
            return map;
        }

        User user = userDAO.selectByName(username);
        if(user == null){
            map.put("msgname", "用户名不存在，请先注册~！");
            return map;
        }

        if(!ToutiaoUtil.MD5(password + user.getSalt()).toString().equals(user.getPassword())){
            map.put("msgpwd", "密码错误~！");
            return map;
        }

        //ticket
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }

    //getTicket
    private String addLoginTicket(int userId){
        //ticket
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);

        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        ticket.setExpired(date);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        ticket.setStatus(0);

        loginTicketDAO.addTicket(ticket);
        return ticket.getTicket();
    }


    public void logout(String ticket){
        loginTicketDAO.updateStatus(ticket, 1);
    }

    public User getUser(int id){
        return userDAO.selectById(id);

    }
}
