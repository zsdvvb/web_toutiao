package com.zhudi.async;

import com.alibaba.fastjson.JSONObject;
import com.zhudi.Utils.JedisAdapter;
import com.zhudi.Utils.RedisKeyUtil;
import com.zhudi.controller.LoginController;
import com.zhudi.model.EventModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    JedisAdapter jedisAdapter;

    public boolean fireEvent(EventModel model){
        try{
            String json = JSONObject.toJSONString(model);
            String key = RedisKeyUtil.getEventQueuekey();
            jedisAdapter.lpush(key, json);
            return true;
        }
        catch (Exception e){
            logger.error("添加事件进入队列出错" + e.getMessage());
            return false;
        }


    }
}
