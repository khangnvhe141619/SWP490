package com.workshop.carauctionsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TryAuctionController {
    @GetMapping("/auction")
    public String showAuction(){
        return "index";
    }
    @GetMapping("/connect")
    public String showAuction1(){
        return "connectWallet";
    }

}
