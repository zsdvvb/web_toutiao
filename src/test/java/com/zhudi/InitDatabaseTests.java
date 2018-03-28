package com.zhudi;

import com.zhudi.dao.LoginTicketDAO;
import com.zhudi.dao.NewsDAO;
import com.zhudi.dao.UserDAO;
import com.zhudi.model.LoginTicket;
import com.zhudi.model.News;
import com.zhudi.model.User;
import org.apache.ibatis.annotations.Select;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/init-schema.sql")
public class InitDatabaseTests {

    @Autowired
    UserDAO userDAO;

    @Autowired
    NewsDAO newsDAO;

    @Autowired
    LoginTicketDAO loginTicketDAO;

    @Test
    public void contextLoads(){
        Random random = new Random();
        for(int i = 0; i < 11; ++i){
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("User%d", i));
            user.setPassword("");
            user.setSalt("");
            userDAO.addUser(user);

            News news = new News();
            news.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000 * 3600 * 5 * i);
            news.setCreatedDate(date);
            news.setImage(String.format("http://images.nowcoder.com/head/%dm.png", random.nextInt(1000)));
            news.setLikeCount(i + 1);
            news.setUserId(i+1);
            news.setTitle(String.format("TITLE{%d}", i));
            news.setLink("http://www.baidu.com");

            LoginTicket ticket = new LoginTicket();
            ticket.setStatus(0);
            ticket.setExpired(date);
            ticket.setTicket(String.format("Ticket%d", i + 1));
            ticket.setUserId(i + 1);

            newsDAO.addNews(news);

            loginTicketDAO.addTicket(ticket);
            loginTicketDAO.updateStatus(ticket.getTicket(), 2);
            user.setPassword("newPassword");
            userDAO.updatePassword(user);
        }

        Assert.assertEquals("newPassword", userDAO.selectById(1).getPassword());
        userDAO.deleteById(1);
        Assert.assertNull(userDAO.selectById(1));
        Assert.assertEquals(2, loginTicketDAO.selectByTicket("Ticket1").getStatus());






    }
}
