package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.News;
import com.workshop.carauctionsystem.model.NewsModel;
import com.workshop.carauctionsystem.service.NewsService;
import com.workshop.carauctionsystem.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService1;




    @GetMapping("/user/news")
    public ModelAndView showList(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "id") String id,
                                 @RequestParam(defaultValue = "") String search,
                                 Model model) {
        ModelAndView modelAndView = null;
        Page<News> top5 = newsService1.getTop5(1);
        List<News> top = top5.getContent();
        Page<News> list = newsService1.findAllNewsOrderById(PageRequest.of(page-1, 5, Sort.by(id)), search);
        if (!list.isEmpty()) {

            modelAndView = new ModelAndView("blog");
            modelAndView.addObject("top5", top);
            modelAndView.addObject("page", page);
            modelAndView.addObject("total", list.getTotalPages());
            modelAndView.addObject("list", list);
        } else {
            modelAndView = new ModelAndView("page404");
        }
        return modelAndView;
    }
//    @GetMapping("/user/news/{pageNo1}")
//    public ModelAndView listNews(@PathVariable(value = "pageNo1") int pageNo) {
//        ModelAndView view = new ModelAndView();
//        int pageSize = 5;
//        Page<News> page = newsService1.findPaginated(pageNo, pageSize);
//
//        Page<News> top5 = newsService1.getTop5(1);
//        List<News> top = top5.getContent();
//
//        List<News> listNews = page.getContent();
//        view.addObject("pageNo", pageNo);
//        view.addObject("total", page.getTotalPages());
//        view.addObject("list", listNews);
//        view.addObject("top5", top);
//        view.setViewName("blog");
//        return view;
//    }

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
