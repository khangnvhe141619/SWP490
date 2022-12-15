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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    //    @GetMapping("/auctionRoom")
//    public ModelAndView redirectAuctionRoom(@CookieValue(value = "setUser", defaultValue = "") String setUser, Model model,
//                                            @CookieValue(value = "setUserId", defaultValue = "") String setUserId) {
//
//        return getListRoom(1, setUser, model, setUserId);
//    }
    @GetMapping("/auctionRoom")
    public ModelAndView showList(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "") String carName,
                                 @RequestParam(defaultValue = "") String model,
                                 @CookieValue(value = "setUser", defaultValue = "") String setUser,
                                 @CookieValue(value = "setUserId", defaultValue = "") String setUserId) {
        ModelAndView modelAndView = null;
        Page<Room> list = service.getSearchRoom(PageRequest.of(page - 1, 5), carName, model);
        List<Brand> brandList = brandService.getAllBrand();
        int pageSize=5;
        Page<Room> pageRoomCurrent = service.getListRoomCurrent(page, pageSize);

       List<Room> listRoomCurrent = pageRoomCurrent.getContent();
        modelAndView = new ModelAndView("auctionRoom");
        if (!list.isEmpty()) {
            System.out.println("day la list: " + list.getSize());


            modelAndView.addObject("page", page);
            modelAndView.addObject("nameCar", carName);
            modelAndView.addObject("modelId", model);
            modelAndView.addObject("total", list.getTotalPages());

            Cookie cookie = new Cookie("setUser", setUser);
            modelAndView.addObject("cookieValue", cookie);
            modelAndView.addObject("setUserId", setUserId);

            if (cookie.getValue().equals("")) {
                modelAndView.addObject("check", false);
            } else {
                modelAndView.addObject("check", true);
                List<Favorite> favoriteList = favoriteService.listAllFavo(Integer.parseInt(setUserId));
                if (!favoriteList.isEmpty()) {
                    modelAndView.addObject("checkList", true);
                    modelAndView.addObject("favoriteList", favoriteList);
                } else {
                    modelAndView.addObject("checkList", false);
                }
            }

        }else {
            modelAndView.addObject("mess","Không tìm thấy:" );
        }
        modelAndView.addObject("brandList", brandList);
        modelAndView.addObject("listRoomCurrent", listRoomCurrent);
        modelAndView.addObject("list", list);
        return modelAndView;
    }

//    @GetMapping("/lsautionRoom/{pageNo1}")
//    public ModelAndView getListRoom(@PathVariable(value = "pageNo1") int pageNo,
//                                    @CookieValue(value = "setUser", defaultValue = "") String setUser, Model model,
//                                    @CookieValue(value = "setUserId", defaultValue = "") String setUserId) {
//        ModelAndView view = new ModelAndView();
//        int pageSize = 6;
//        Page<Room> page = service.getListRoom(pageNo, pageSize);
//        Page<Room> pageRoomCurrent = service.getListRoomCurrent(pageNo, pageSize);
//        List<Room> listRoom = page.getContent();
//        List<Room> listRoomCurrent = pageRoomCurrent.getContent();
//        for (Room ls : listRoom) {
//            System.out.println(ls.getRoomName());
//        }
//        List<Brand> brandList = brandService.getAllBrand();
//
//        view.addObject("brandList", brandList);
//        view.addObject("pageNo", pageNo);
//        view.addObject("total", page.getTotalPages());
//        view.addObject("totalCurrent", pageRoomCurrent.getTotalPages());
//        view.addObject("list", listRoom);
//        view.addObject("listRoomCurrent", listRoomCurrent);
//
////        ---------------------------------------
//        Cookie cookie = new Cookie("setUser", setUser);
//        model.addAttribute("cookieValue", cookie);
//        model.addAttribute("setUserId", setUserId);
//        if (cookie.getValue().equals("")) {
//            model.addAttribute("check", false);
//        } else {
//            model.addAttribute("check", true);
//            List<Favorite> favoriteList = favoriteService.listAllFavo(Integer.parseInt(setUserId));
//            if (!favoriteList.isEmpty()) {
//                model.addAttribute("checkList", true);
//                model.addAttribute("favoriteList", favoriteList);
//            } else {
//                model.addAttribute("checkList", false);
//            }
//        }
//
////        ---------------------------------------
//
//        view.setViewName("auctionRoom");
//        return view;
//    }

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

