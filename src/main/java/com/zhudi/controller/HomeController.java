package com.zhudi.controller;

import com.zhudi.model.EntityType;
import com.zhudi.model.HostHolder;
import com.zhudi.model.News;
import com.zhudi.model.ViewObject;
import com.zhudi.service.LikeService;
import com.zhudi.service.NewsService;
import com.zhudi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @Autowired
    LikeService likeService;

    @Autowired
    HostHolder hostHolder;

    //private List<ViewObject>

    @RequestMapping(path={"/", "index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model, @RequestParam(value="pop", defaultValue = "0") int pop){

        model.addAttribute("vos", getNews(0, 0, 10));
        model.addAttribute("pop", pop);
        return "home";
    }




    @RequestMapping(path={"/user/{userId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String userIndex(Model model, @PathVariable("userId") int userId){
        model.addAttribute("vos", getNews(userId, 0, 10));
        return "home";
    }

    @RequestMapping(path={"/setting"}, method = RequestMethod.GET)
    public String userSetting(Model model){
        return "setting";
    }


    @RequestMapping("/hello")
    public String hello(Model model){

        model.addAttribute("name", "noName");
        //model.addAttribute("pops", "");
        return "test";

    }

    private List<List<ViewObject>> getNews(int userId, int offset, int limit){
        List<News> newsList = newsService.getLatestNews(userId, offset, limit);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String cur_date = null;
        List<ViewObject> temp = new ArrayList<>();;
        List<List<ViewObject>> vos = new ArrayList<>();
        int localUserId = hostHolder.getUser() != null ? hostHolder.getUser().getId() : 0;
        for(News news : newsList){
            if(cur_date == null){
                cur_date = sdf.format(news.getCreatedDate());
            }
            if(!cur_date.equals(sdf.format(news.getCreatedDate()))){
                cur_date = sdf.format(news.getCreatedDate());
                vos.add(temp);
                temp = new ArrayList<>();
            }

            ViewObject vo = new ViewObject();
            vo.set("news", news);
            vo.set("user", userService.getUser(news.getUserId()));

            if(localUserId != 0){
                vo.set("like", likeService.getLikeStatus(localUserId, EntityType.ENTITY_NEWS, news.getId()));
            }
            temp.add(vo);
        }
        vos.add(temp);  //装入最后一个temp
        return vos;
    }

}
