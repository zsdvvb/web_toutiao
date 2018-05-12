package com.zhudi.async.handler;

import com.zhudi.async.EventHandler;
import com.zhudi.model.*;
import com.zhudi.async.EventType;
import com.zhudi.service.LikeService;
import com.zhudi.service.MessageService;
import com.zhudi.service.NewsService;
import com.zhudi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class likeHandler implements EventHandler{

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @Autowired
    LikeService likeService;

    @Override
    public void doHandle(EventModel model) {
        int likeCount = likeService.getLikeCount(EntityType.ENTITY_NEWS, model.getEntityId());
        newsService.updateLikeCount(model.getEntityId(), likeCount);
        Message message = new Message();
        User user = userService.getUser(model.getActorId());
        message.setFromId(user.getId());
        message.setToId(model.getEntityOwnerId());
        message.setContent("用户" + user.getName() + "赞了你的资讯&quot <a href='http://localhost:8080/news/" + model.getEntityId() + "'> " + newsService.getById(model.getEntityId()).getTitle() + "</a>&quot" );
        message.setCreatedDate(new Date());

        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventType() {
        return Arrays.asList(EventType.LIKE);
    }
}
