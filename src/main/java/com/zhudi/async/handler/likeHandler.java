package com.zhudi.async.handler;

import com.zhudi.async.EventHandler;
import com.zhudi.model.EventModel;
import com.zhudi.async.EventType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class likeHandler implements EventHandler{

    @Override
    public void doHandle(EventModel model) {
        System.out.println("LIKE");
    }

    @Override
    public List<EventType> getSupportEventType() {
        return Arrays.asList(EventType.LIKE);
    }
}
