package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.model.DataPrice;
import com.workshop.carauctionsystem.service.impl.DataPriceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminDataPriceAVGController {
    @Autowired
    DataPriceServiceImpl dataPrice;

    @GetMapping("/admin/dataPrice")
    public ModelAndView showList() {
        ModelAndView modelAndView = null;
        List<DataPrice> list = dataPrice.getPriceAVG();
        modelAndView = new ModelAndView("admin/dataPrice");
        if (!list.isEmpty()) {
            modelAndView.addObject("dataAVG", list);
        } else {
            modelAndView.addObject("lst_empty", "List Empty!");
        }
        return modelAndView;
    }
}
