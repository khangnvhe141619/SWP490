package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.*;
import com.workshop.carauctionsystem.model.FavoriteDTO;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.repository.CarRepository;
import com.workshop.carauctionsystem.repository.RoomRepository;
import com.workshop.carauctionsystem.service.*;
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

    @Autowired
    RoomParticipantService roomParticipantService;
    @Autowired
    UserService userService;

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
        int pageSize = 6;
        Page<Room> list = service.getSearchRoom(PageRequest.of(page - 1, pageSize), carName, model);
        List<Brand> brandList = brandService.getAllBrand();
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
            User u = userService.findUserById(Integer.parseInt(setUserId));
            if (cookie.getValue().equals("")) {
                modelAndView.addObject("check", false);
            } else {
                modelAndView.addObject("addressWallet", u.getAddressWallet());
                modelAndView.addObject("check", true);
                List<Favorite> favoriteList = favoriteService.listAllFavo(Integer.parseInt(setUserId));
                if (!favoriteList.isEmpty()) {
                    modelAndView.addObject("checkList", true);
                    modelAndView.addObject("favoriteList", favoriteList);
                } else {
                    modelAndView.addObject("checkList", false);
                }
            }

        } else {
            modelAndView.addObject("mess", "Không tìm thấy:");
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
                                                             @CookieValue(value = "setUserId") int userId) {
        favoriteService.removeFavorite(carId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Username or password is wrong !", null));
    }

    @PostMapping("/saveParticipant")
    public ResponseEntity<ResponseObject> saveBidder(@CookieValue(value = "setUserId") int setUserId,
                                                          @RequestParam("roomId") String roomId) {
        User u1 = userService.findUserById(setUserId);
        Room r1 = service.getRoomById(Integer.parseInt(roomId));
        boolean _suc = roomParticipantService.saveParticipant(u1, r1);
        if (u1 != null && _suc) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Succeed!", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Invalid!", null));
    }
    @GetMapping("/isUserInRoom")
    public ResponseEntity<ResponseObject> isUserInRoom(@CookieValue(value = "setUserId") int setUserId,
                                                     @RequestParam("roomId") String roomId) {
        User u1 = userService.findUserById(setUserId);
        Room r1 = service.getRoomById(Integer.parseInt(roomId));
        boolean _found = roomParticipantService.isParticipantIn(u1, r1);
        if (u1 != null && _found) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Found!", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("no", "Not found!", null));
    }

    @PutMapping("/updateTicket")
    public ResponseEntity<ResponseObject> updateTicket(@RequestParam("roomId") String roomId) {
        Room r1 = service.getRoomById(Integer.parseInt(roomId));
        service.updateTicket(r1);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Succeed!", null));

    }
}

