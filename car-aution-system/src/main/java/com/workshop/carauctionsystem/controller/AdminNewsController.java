package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.News;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.NewsModel;
import com.workshop.carauctionsystem.service.impl.NewsServiceImpl;
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
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;


@Controller
public class AdminNewsController {
    @Autowired
    NewsServiceImpl newsService;

    @GetMapping("/news")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "id") String id,
                                 @RequestParam(defaultValue = "") String search,
                                 Model model) {
        ModelAndView modelAndView = null;
        model.addAttribute("newsModel", new NewsModel());
        Page<News> list = newsService.findAllNewsOrderById(PageRequest.of(page, 5, Sort.by(id)), search);
        if (!list.isEmpty()) {
            modelAndView = new ModelAndView("admin/listNews");
            modelAndView.addObject("newss", list);
        } else {
            modelAndView = new ModelAndView("page404");
        }
        return modelAndView;
    }

    @PostMapping("news/create")
    public String create(@ModelAttribute(value = "newsModel") NewsModel newsModel,
                         @RequestParam MultipartFile upImg, RedirectAttributes ra) {
        try {
            String nameFile = upImg.getOriginalFilename();
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            News news = new News();
            news.setTitle(newsModel.getTitle());
            news.setAuthor(newsModel.getAuthor());
            news.setShortDescribe(newsModel.getShortDescribe());
            news.setDescribe(newsModel.getDescribe());
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            news.setCreateAt(timestamp);
            news.setDescribe1(newsModel.getDescribe1());
            news.setDescribe2(newsModel.getDescribe2());
            news.setDescribe3(newsModel.getDescribe3());
            news.setDescribe4(newsModel.getDescribe4());
            news.setImg("/hoang/" + nameFile);
            newsService.saveNews(news);
            ra.addFlashAttribute("success", "The news has been saved successfully");
            return "redirect:/news";
        } catch (IOException e) {
            ra.addFlashAttribute("fail", "Must upload a image");
            return "redirect:/news";
        }
    }

    @PostMapping("news/edit")
    public String update(@ModelAttribute(value = "newss") News news,
                         @RequestParam MultipartFile upImg,
                         @RequestParam Long id,
                         RedirectAttributes ra) {
        String nameFile = upImg.getOriginalFilename();
        News newsId = newsService.findById(id);
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            news.setImg("/hoang/" + nameFile);
            newsService.saveNews(news);
        } catch (IOException e) {
            news.setImg(newsId.getImg());
            newsService.saveNews(news);
        }
        ra.addFlashAttribute("success", "The brand has been saved successfully");
        return "redirect:/news";
    }

    @GetMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
        try {
            newsService.delete(id);
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "page404";
        }
        ra.addFlashAttribute("success", "The News has been deleted successfully");
        return "redirect:/news";
    }
}
