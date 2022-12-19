package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Room;
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
public class AdminHomeController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/admin/home")
    public ModelAndView showListCar(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "id") String id,
                                    @RequestParam(defaultValue = "") String search,
                                    @CookieValue(value = "setUser", defaultValue = "") String setUser,
                                    Model model){
        ModelAndView view = null;
        model.addAttribute("cars", carService.findAllDTO());
        model.addAttribute("createdBy", userService.getRoleByAdminAuction());
        model.addAttribute("roomType", roomTypeService.getAllRoomType());
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(new Date());
        Page<Room> list = roomService.findRoomByCurrent(PageRequest.of(page, 5, Sort.by(id)), search,date);
        Page<Room> list1 = roomService.findRoomByPending(PageRequest.of(page, 5, Sort.by(id)), search,date);
        Page<Room> list2 = roomService.findRoomByHistory(PageRequest.of(page, 5, Sort.by(id)), search,date);
        // current
        model.addAttribute("userName", setUser);
        view = new ModelAndView("admin/index");
        if (!list.isEmpty()) {
            view.addObject("rooms", list);
        } else {
            view.addObject("lst_empty", "List Empty!");
        }
        //
        if (!list1.isEmpty()) {
            view.addObject("rooms1", list1);
        } else {
            view.addObject("lst_empty1", "List Empty!");
        }
        if (!list2.isEmpty()) {
            view.addObject("rooms2", list2);
        } else {
            view.addObject("lst_empty2", "List Empty!");
        }
        return view;
    }
    @PostMapping("admin/home/edit")
    public String update(@RequestParam MultipartFile upImg, RedirectAttributes ra,
                         @RequestParam Map<String, String> requestMap) throws ParseException {

        if (requestMap.get("roomName").equals("") || requestMap.get("startTime").equals("") ||
                requestMap.get("endTime").equals("") || requestMap.get("openDate").equals("")){
            ra.addFlashAttribute("fail", "Add new Room Auction Failed");
            return "redirect:/admin/home";
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
        String regex = "^[a-zA-Z0-9 \\-*_]+$";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d1 = sdf.parse(startTime);
        Date d2 = sdf.parse(endTime);
        long a = d2.getTime() - d1.getTime();
        if (a < 1){
            ra.addFlashAttribute("fail", "Add New Room Failed (EndTime Bigger StartTime)");
            return "redirect:/admin/home";
        }
        if (!roomName.matches(regex)) {
            ra.addFlashAttribute("fail", "Add New Room Failed (Don't enter special characters)");
            return "redirect:/admin/home";
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
        return "redirect:/admin/home";
    }

    @GetMapping("/admin/home/delete/{id}")
    public String deleteModel(@PathVariable(value = "id") int id, RedirectAttributes ra) {
        try {
            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            cal.add(Calendar.DATE,-1);
            String openDate =dateFormat.format(cal.getTime());
            System.out.println("-----------" + openDate + "-----" + id);
            roomService.auctionPause(openDate,id);
            ra.addFlashAttribute("success", "The Auction Room has been Pause successfully");
            return "redirect:/admin/home";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "admin/page404";
        }
    }

}
