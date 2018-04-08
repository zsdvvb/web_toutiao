package com.zhudi;

import com.zhudi.Utils.JedisAdapter;
import com.zhudi.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisTest {
    @Autowired
    JedisAdapter jedisAdapter;

    @Test
    public void testObject(){
        User user = new User();
        user.setName("tom");
        user.setPassword("pwd");
        user.setHeadUrl("http://www.baidu.com");
        user.setSalt("salt");
        jedisAdapter.setObject("user1xx", user);

        User u1 = jedisAdapter.getObject("user1xx", User.class);
        Assert.assertEquals("tom", u1.getName());
    }
}
