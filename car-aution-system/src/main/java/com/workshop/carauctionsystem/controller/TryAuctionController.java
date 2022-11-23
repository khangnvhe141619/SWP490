package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.model.BrandModel;
import com.workshop.carauctionsystem.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@Controller
public class TryAuctionController {
    @GetMapping("/auction")
    public String showAuction(){
        return "auctionRoom";
    }


}
