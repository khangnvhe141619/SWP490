package com.workshop.carauctionsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@Controller
public class TryAuctionController {
    @GetMapping("/auction")
    public String showAuction(){
        return "auctionRoom";
    }
}
