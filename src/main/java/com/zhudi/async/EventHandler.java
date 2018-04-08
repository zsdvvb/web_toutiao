package com.zhudi.async;

import com.zhudi.model.EventModel;

import java.util.List;

public interface EventHandler {
    void doHandle(EventModel model);
    List<EventType> getSupportEventType();

}
