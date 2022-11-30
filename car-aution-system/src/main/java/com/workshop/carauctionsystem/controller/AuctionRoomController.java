package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.*;
import com.workshop.carauctionsystem.model.FavoriteDTO;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.repository.CarRepository;
import com.workshop.carauctionsystem.repository.RoomRepository;
import com.workshop.carauctionsystem.service.BrandService;
import com.workshop.carauctionsystem.service.CarService;
import com.workshop.carauctionsystem.service.FavoriteService;
import com.workshop.carauctionsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class AuctionRoomController {
    @Autowired
    RoomService service;
    @Autowired
    BrandService brandService;

    @Autowired
    FavoriteService favoriteService;

    @GetMapping("/auctionRoom")
    public ModelAndView redirectAuctionRoom(@CookieValue(value = "setUser") String setUser, Model model,
                                            @CookieValue(value = "setUserId") int setUserId) {
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue", cookie);
        model.addAttribute("setUserId", setUserId);
        if (cookie.getValue().equals("")) {
            model.addAttribute("check", false);
        } else {
            model.addAttribute("check", true);
        }
        List<Favorite> favoriteList = favoriteService.listAllFavo(setUserId);
        if(!favoriteList.isEmpty()){
            model.addAttribute("checkList", true);
            model.addAttribute("favoriteList", favoriteList);
        }else {
            model.addAttribute("checkList", false);
        }
        List<Room> listRoom = service.getAllRoom();
        List<Brand> brandList = brandService.getAllBrand();
        model.addAttribute("listRoom", listRoom);
        model.addAttribute("brandList", brandList);
        ModelAndView view = new ModelAndView();
        view.setViewName("auctionRoom");
        return view;
    }

    @PostMapping("/addFavorite")
    public ResponseEntity<ResponseObject> addFavorite(@RequestParam("carId") int carId,
                                                      @CookieValue(value = "setUserId") int userId) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = df.format(date);
        favoriteService.addFavorite(userId, carId, dateString);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Username or password is wrong !", null));
    }

    @PostMapping("/removeFavorite")
    public ResponseEntity<ResponseObject> redirectUnFavorite(@RequestParam("carId") int carId,
                                           @CookieValue(value = "setUserId") int userId){
        favoriteService.removeFavorite(carId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Username or password is wrong !", null));
    }
}

