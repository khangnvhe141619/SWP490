package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.News;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.service.NewsService;
import com.workshop.carauctionsystem.service.impl.NewsServiceImpl;
import com.workshop.carauctionsystem.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.apache.logging.log4j.LogManager;
import java.util.List;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
public class NewsController {
    @Autowired
    NewsServiceImpl newsService;

    @Autowired
    Validate validate;

    @Autowired
    NewsService newsService1;
    private static final Logger LOGGER = LogManager.getLogger(NewsController.class);
    ;

    @GetMapping("/user/news")
    public ModelAndView redirect() {
        return listNews(1);
    }

    @GetMapping("/user/news/{pageNo1}")
    public ModelAndView listNews(@PathVariable(value = "pageNo1") int pageNo) {
        ModelAndView view = new ModelAndView();
        int pageSize = 3;
        Page<News> page = newsService1.findPaginated(pageNo, pageSize);
        LOGGER.info("da vao day");
        Page<News> top5 = newsService1.getTop5(1);
        List<News> top = top5.getContent();
        LOGGER.info("Top 5: " + top.size());
        List<News> listNews = page.getContent();
        view.addObject("pageNo", pageNo);
        view.addObject("total", page.getTotalPages());
        view.addObject("list", listNews);
        view.addObject("top5", top);
        view.setViewName("blog");
        return view;
    }

    @GetMapping("/user/details/{id}")
    public ModelAndView newsDetails(@PathVariable(value = "id") int id) {
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

    @GetMapping("/news")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "id") String id) {
        ModelAndView modelAndView = null;
        Page<News> list = newsService.findAllOrderById(PageRequest.of(page, 5, Sort.by(id)));
        if (!list.isEmpty()) {
            modelAndView = new ModelAndView("listNews");
            modelAndView.addObject("news", list);
        } else {
            modelAndView = new ModelAndView("page404");
        }
        return modelAndView;
    }

    @GetMapping("/news/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = null;
        try {
            modelAndView = new ModelAndView("showFormCreate");
            modelAndView.addObject("news", new News());
            return modelAndView;
        } catch (Exception e) {
            modelAndView = new ModelAndView("page404");
            return modelAndView;
        }
    }

    @PostMapping("news/create")
    public String create(@Valid @ModelAttribute(value = "news") News news,
                         @RequestParam MultipartFile upImg, RedirectAttributes ra, Model model) {
        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            news.setImg("/hoang/" + nameFile);
            newsService.saveNews(news);
            ra.addFlashAttribute("message", "The news has been saved successfully");
            return "redirect:/news";
        } catch (IOException e) {
            model.addAttribute("message", "Must upload a image");
            return "test";
        }
    }


    @GetMapping("/news/delete/{id}")
    public String deleteBrand(@PathVariable(value = "id") int id, RedirectAttributes ra) {
        try {
            newsService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/news";
    }

    @GetMapping("/news/edit/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes ra) throws NotFoundException {
        News news = newsService.findById(id);
        if (news != null) {
            model.addAttribute("news", news);
            return "update";
        } else {
            throw new NotFoundException("Could not find any with ID");
        }
    }

    @PostMapping("news/edit")
    public String update(@Valid @ModelAttribute(value = "news") News news,
                         @RequestParam MultipartFile upImg, @RequestParam int id,
                         RedirectAttributes ra) {
        String nameFile = upImg.getOriginalFilename();
        News newsId = newsService.findById(id);
        news.setImg(newsId.getImg());

        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            news.setImg("/hoang/" + nameFile);
            newsService.saveNews(news);
        } catch (IOException e) {
            news.setImg(newsId.getImg());
            newsService.saveNews(news);
        }
        ra.addFlashAttribute("message", "The news has been saved successfully");
        return "redirect:/news";
    }
}
