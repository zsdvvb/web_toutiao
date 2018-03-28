package com.zhudi.model;

import java.util.HashMap;
import java.util.Map;

public class ViewObject {
    private Map<String, Object> objs = new HashMap<String, Object>();

    public void set(String key, Object value) {
        objs.put(key, value);
    }

    public News getNews() {
        return (News)objs.get("news");
    }

    public User getUser(){
        return (User)objs.get("user");
    }

    public Comment getComment() {
        return (Comment)objs.get("comment");
    }

}
