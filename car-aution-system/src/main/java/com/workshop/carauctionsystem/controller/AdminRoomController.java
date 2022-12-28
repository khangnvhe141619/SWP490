package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.*;
import com.workshop.carauctionsystem.model.RoomDTO;
import com.workshop.carauctionsystem.service.CarService;
import com.workshop.carauctionsystem.service.RoomService;
import com.workshop.carauctionsystem.service.RoomTypeService;
import com.workshop.carauctionsystem.service.UserService;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Controller
public class AdminRoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/admin/auctionroom")
    public ModelAndView showList(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "id") String id,
                                 @RequestParam(defaultValue = "") String search,
                                 @CookieValue(value = "setUser", defaultValue = "") String setUser,
                                 Model model) {
        ModelAndView modelAndView = null;
        model.addAttribute("cars", carService.findAllDTO());
        model.addAttribute("createdBy", userService.getRoleByAdminAuction());
        model.addAttribute("roomType", roomTypeService.getAllRoomType());
        model.addAttribute("roomDTO", new RoomDTO());
        model.addAttribute("userName", setUser);
        Page<Room> list = roomService.findAllByName(PageRequest.of(page, 5, Sort.by(id)), search);
        if (!list.isEmpty()) {
            modelAndView = new ModelAndView("admin/listAuction");
            modelAndView.addObject("rooms", list);
        } else {
            modelAndView = new ModelAndView("page404");
        }
        return modelAndView;
    }

    @PostMapping("/admin/auctionroom/create")
    public String create(@ModelAttribute(value = "roomDTO") RoomDTO roomDTO,
                         @RequestParam Long carId,
                         @RequestParam MultipartFile upImg,
                         @CookieValue(value = "setUser", defaultValue = "") String setUser,
                         RedirectAttributes ra) {

        try {
            if (roomDTO.getRoomName().equals("") || carId == null || roomDTO.getStartTime().equals("") ||
            roomDTO.getEndTime().equals("") || roomDTO.getOpenDate().equals("") ){
                ra.addFlashAttribute("fail", "Add new Room Auction Failed");
                return "redirect:/admin/auctionroom";
            }
            Car car = new Car();
            car.setId(carId);
            RoomType roomType = new RoomType();
            roomType.setId(1);
            User userData = userService.findByUsername(setUser);
            User user = new User();
            user.setId(userData.getId());
            Room room = new Room();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            room.setCarId(car);
            room.setTypeRoomId(roomType);
            room.setCreatedBy(user);
            room.setRoomName(roomDTO.getRoomName());
            room.setCreatedAt(timestamp);
            room.setUpdatedAt(timestamp);
            room.setRoomTime("10:00:00");
            room.setOpenDate(roomDTO.getOpenDate());
            room.setStartTime(roomDTO.getStartTime());
            room.setEndTime(roomDTO.getEndTime());
            room.setTicketNumber(roomDTO.getTicketNumber());
            room.setTicketPrice(roomDTO.getTicketPrice());
            String nameFile = upImg.getOriginalFilename();
            FileCopyUtils.copy(upImg.getBytes(), new File("src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            room.setImgPath("/assets/hoang/"+nameFile);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date d1 = sdf.parse(roomDTO.getStartTime());
            Date d2 = sdf.parse(room.getEndTime());
            long a = d2.getTime() - d1.getTime();
            String regex = "^[a-zA-Z0-9 \\-*_]+$";
            if (a < 1){
                ra.addFlashAttribute("fail", "Add New Room Failed (EndTime Bigger StartTime)");
                return "redirect:/admin/auctionroom";
            }
            if (!roomDTO.getRoomName().matches(regex)) {
                ra.addFlashAttribute("fail", "Add New Room Failed (Don't enter special characters)");
                return "redirect:/admin/auctionroom";
            }
            roomService.saveRoom(room);
            ra.addFlashAttribute("success", "The new Room Auction has been saved successfully");
            return "redirect:/admin/auctionroom";
        } catch (Exception e) {
            ra.addFlashAttribute("fail", "Add new Room Auction Failed");
            return "redirect:/admin/auctionroom";
        }
    }

    @PostMapping("admin/auctionroom/edit")
    public String update(@RequestParam MultipartFile upImg, RedirectAttributes ra,
                         @RequestParam Map<String, String> requestMap) throws ParseException {

        if (requestMap.get("roomName").equals("") || requestMap.get("startTime").equals("") ||
                requestMap.get("endTime").equals("") || requestMap.get("openDate").equals("")){
            ra.addFlashAttribute("fail", "Add new Room Auction Failed");
            return "redirect:/admin/auctionroom";
        }
        int id = Integer.parseInt(requestMap.get("id"));
        String roomName = requestMap.get("roomName");
        String startTime = requestMap.get("startTime");
        String endTime = requestMap.get("endTime");
        String openDate = requestMap.get("openDate");
        Date date = new Date();
        Timestamp updateAt = new Timestamp(date.getTime());
        int ticketPrice = Integer.parseInt(requestMap.get("ticketPrice"));
        int ticketNumber = Integer.parseInt(requestMap.get("numTicket"));
        int createBy = 2;
        int typeRoom = 2;
        String nameFile = upImg.getOriginalFilename();
        Room room = roomService.getRoomById(id);
        String regex = "^[a-zA-Z0-9 \\s-*_]+$";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d1 = sdf.parse(startTime);
        Date d2 = sdf.parse(endTime);
        long a = d2.getTime() - d1.getTime();
        if (a < 1){
            ra.addFlashAttribute("fail", "Add New Room Failed (EndTime Bigger StartTime)");
            return "redirect:/admin/auctionroom";
        }
        if (!roomName.matches(regex)) {
            ra.addFlashAttribute("fail", "Add New Room Failed (Don't enter special characters)");
            return "redirect:/admin/auctionroom";
        }
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            String img = "/assets/hoang/" + nameFile;
            roomService.update(roomName,startTime,endTime,updateAt,ticketNumber,ticketPrice,typeRoom,createBy,img,openDate,id);
        } catch (IOException e) {
            String img = room.getImgPath();
            roomService.update(roomName,startTime,endTime,updateAt,ticketNumber,ticketPrice,typeRoom,createBy,img,openDate,id);
        }
        ra.addFlashAttribute("success", "The brand has been saved successfully");
        return "redirect:/admin/auctionroom";
    }

}
