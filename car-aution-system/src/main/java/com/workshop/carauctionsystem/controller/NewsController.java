package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.News;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.model.NewsModel;
import com.workshop.carauctionsystem.service.NewsService;
import com.workshop.carauctionsystem.service.UserService;
import com.workshop.carauctionsystem.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import java.util.List;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService1;

    @Autowired
    UserService userService;


    @GetMapping("/news")
    public ModelAndView showList(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "id") String id,
                                 @RequestParam(defaultValue = "") String search,
                                 @CookieValue(value = "setUser", defaultValue = "") String setUser,
                                 Model model) {
        ModelAndView modelAndView = null;
        Page<News> top5 = newsService1.getTop5(1);
        List<News> top = top5.getContent();
        Page<News> list = newsService1.findAllNewsOrderById(PageRequest.of(page-1, 5, Sort.by(id)), search);
        modelAndView = new ModelAndView("blog");
        if (!list.isEmpty()) {
            modelAndView.addObject("top5", top);
            modelAndView.addObject("page", page);
            modelAndView.addObject("total", list.getTotalPages());
            modelAndView.addObject("search", search);
        } else {
           modelAndView.addObject("mess","Không tìm thấy:" +search);
        }
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        if(cookie.getValue().equals("")){
            model.addAttribute("check", false);
        } else {
            User u =  userService.findByUsername(setUser);
            modelAndView.addObject("addressWallet", u.getAddressWallet());
            model.addAttribute("check", true);
        }
        modelAndView.addObject("activeN", "nav-link scrollto active");
        modelAndView.addObject("list", list);
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

    @GetMapping("/details/{id}")
    public ModelAndView newsDetails(@PathVariable(value = "id") long id,
                                    @CookieValue(value = "setUser", defaultValue = "") String setUser,
                                    Model model) {
        ModelAndView view = new ModelAndView();
        News details = newsService1.getNewsById(id);
        details.getId();
        details.getAuthor();
        details.getImg();
        details.getCreateAt();
        Page<News> top5 = newsService1.getTop5(1);
        List<News> top = top5.getContent();
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        if(cookie.getValue().equals("")){
            model.addAttribute("check", false);
        } else {
            User u =  userService.findByUsername(setUser);
            view.addObject("addressWallet", u.getAddressWallet());
            model.addAttribute("check", true);
        }
        view.addObject("id", id);
        view.addObject("lsdetails", details);
        view.addObject("top5", top);
        view.addObject("activeN", "nav-link scrollto active");
        view.setViewName("blogDetail");
        return view;
    }

}
