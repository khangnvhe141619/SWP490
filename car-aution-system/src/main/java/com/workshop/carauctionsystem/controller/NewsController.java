package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.News;
import com.workshop.carauctionsystem.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService1;


    @GetMapping("/user/news")
    public ModelAndView redirect() {
        return listNews(1);
    }

    @GetMapping("/user/news/{pageNo1}")
    public ModelAndView listNews(@PathVariable(value = "pageNo1") int pageNo) {
        ModelAndView view = new ModelAndView();
        int pageSize = 3;
        Page<News> page = newsService1.findPaginated(pageNo, pageSize);

        Page<News> top5 = newsService1.getTop5(1);
        List<News> top = top5.getContent();

        List<News> listNews = page.getContent();
        view.addObject("pageNo", pageNo);
        view.addObject("total", page.getTotalPages());
        view.addObject("list", listNews);
        view.addObject("top5", top);
        view.setViewName("blog");
        return view;
    }

    @GetMapping("/user/details/{id}")
    public ModelAndView newsDetails(@PathVariable(value = "id") long id) {
        ModelAndView view = new ModelAndView();
        News details = newsService1.getNewsById(id);
        details.getId();
        details.getAuthor();
        details.getImg();
        details.getCreateAt();
        Page<News> top5 = newsService1.getTop5(1);
        List<News> top = top5.getContent();
        view.addObject("id", id);
        view.addObject("lsdetails", details);
        view.addObject("top5", top);
        view.setViewName("blogDetail");
        return view;
    }

}
