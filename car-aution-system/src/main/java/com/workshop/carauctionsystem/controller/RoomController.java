package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.*;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.repository.ModelRepository;
import com.workshop.carauctionsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;

@Controller
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    ImageService imageService;
    @Autowired
    SafetySystemService safetySystemService;
    @Autowired
    CarService carService;
    @Autowired
    CarSpecificationService carSpecificationService;
    @Autowired
    RoomDetailPlayerService roomDetailPlayerService;
    @Autowired
    BrandService brandService;
    @Autowired
    UserService userService;
    @Autowired
    ModelService modelService;
    @Autowired
    ModelRepository modelRepository;

    @GetMapping("/htmlHelper/{brandId}")
    public String getCombobox(@PathVariable(value ="brandId" ) String brandId) {

        Long bId = Long.parseLong(brandId);
       Gson gson = new Gson();
        return gson.toJson(modelRepository.getListModelByBrand(bId));
    }

    @GetMapping("/auctionRoom/{id}")
    public ModelAndView redirectAuctionRoom(@PathVariable int id, Model model,@CookieValue(value = "setUser", defaultValue = "") String setUser,
                                            @CookieValue(value = "setUserId") int userId) throws ParseException {
        ModelAndView view = new ModelAndView();
        Room room = roomService.getRoomById(id);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(java.time.LocalTime.now().toString());
        Date date2 = format.parse(room.getEndTime().toString());
        long difference = date2.getTime() - date1.getTime();
        long diffSeconds = difference / 1000 % 60;
        long diffMinutes = difference / (60 * 1000) % 60;
        long diffHours = difference / (60 * 60 * 1000) % 24;
        if (diffSeconds < 0) {
            view.setViewName("redirect:/winPage?roomId=" + id);
            return view;
        }
        List<RoomDetailPlayer> roomDetailPlayer = roomDetailPlayerService.getAllByUserIdAndRoomId(id, userId);
        if (roomDetailPlayer.isEmpty()) {
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = df.format(date);
            roomDetailPlayerService.addPlayer(id, userId, 0, dateString, 0);
        } else {
            if (roomDetailPlayer.get(0).getUserBid() == 0) {
                model.addAttribute("bidPrice", true);
            } else {
                model.addAttribute("bidPrice", false);
            }
            model.addAttribute("roomDetailPlayer", roomDetailPlayer.get(0).getUserBid());
        }
        Long carId = room.getCarId().getId();
        List<Image> imageList = imageService.getAllImageByCarId(carId);
        List<SafetySystem> safetySystemList = safetySystemService.getAllSafetySystem(carId);
        Car car = carService.getAllCarById(carId);
        CarSpecification carSpecification = carSpecificationService.getAllByCarId(carId);
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("diffHours", diffHours);
        model.addAttribute("diffMinutes", diffMinutes);
        model.addAttribute("diffSeconds", diffSeconds);
        model.addAttribute("room", room);
        model.addAttribute("imageList", imageList);
        model.addAttribute("safetySystemList", safetySystemList);
        model.addAttribute("car", car);
        model.addAttribute("carSpecification", carSpecification);
        model.addAttribute("roomId", id);
        model.addAttribute("cookieValue", cookie);
        if(cookie.getValue().equals("")){
            model.addAttribute("check", false);
        } else {
            model.addAttribute("check", true);
            User u =  userService.findByUsername(setUser);
            view.addObject("addressWallet", u.getAddressWallet());
        }
        view.setViewName("index");
        return view;
    }

    @PostMapping("/insertBid")
    public ResponseEntity<ResponseObject> insertBid(@RequestParam("bid") String bid, @RequestParam("roomId") int roomId,
                                                    @CookieValue(value = "setUserId") int setUserId,
                                                    HttpSession session) {
        int bidInt = Integer.parseInt(bid);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = df.format(date);
        roomDetailPlayerService.updateUserBid(bidInt, dateString, setUserId, roomId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Invalid password!", null));
    }
}
