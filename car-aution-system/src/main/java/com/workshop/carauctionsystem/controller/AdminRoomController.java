package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.*;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.ModelCarModel;
import com.workshop.carauctionsystem.model.RoomDTO;
import com.workshop.carauctionsystem.service.CarService;
import com.workshop.carauctionsystem.service.RoomService;
import com.workshop.carauctionsystem.service.RoomTypeService;
import com.workshop.carauctionsystem.service.UserService;
import com.workshop.carauctionsystem.validate.Validate;
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
import java.text.SimpleDateFormat;
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
                                 Model model) {
        ModelAndView modelAndView = null;
        model.addAttribute("cars", carService.findAllDTO());
        model.addAttribute("createdBy", userService.getRoleByAdminAuction());
        model.addAttribute("roomType", roomTypeService.getAllRoomType());
        model.addAttribute("roomDTO", new RoomDTO());

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
                         @RequestParam int typeRoom,
                         @RequestParam int createBy,
                         @RequestParam MultipartFile upImg,
                         RedirectAttributes ra) {

        try {
            Car car = new Car();
            car.setId(carId);
            RoomType roomType = new RoomType();
            roomType.setId(typeRoom);
            User user = new User();
            user.setId(createBy);
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
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            room.setImgPath("/hoang/"+nameFile);
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
                         @RequestParam Map<String, String> requestMap) {

        int id = Integer.parseInt(requestMap.get("id"));
        String roomName = requestMap.get("roomName");
        String startTime = requestMap.get("startTime");
        String endTime = requestMap.get("endTime");
        Date date = new Date();
        Timestamp updateAt = new Timestamp(date.getTime());
        int ticketPrice = Integer.parseInt(requestMap.get("ticketPrice"));
        int createBy = Integer.parseInt(requestMap.get("createdBy"));
        int typeRoom = Integer.parseInt(requestMap.get("typeRoom"));
        String nameFile = upImg.getOriginalFilename();
        Room room = roomService.getRoomById(id);
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("E:\\DoAn\\SWP490\\car-aution-system\\src\\main\\resources\\static\\assets\\hoang/" + nameFile));
            String img = "/hoang/" + nameFile;
            roomService.update(roomName,startTime,endTime,updateAt,ticketPrice,typeRoom,createBy,img,id);
        } catch (IOException e) {
            String img = room.getImgPath();
            roomService.update(roomName,startTime,endTime,updateAt,ticketPrice,typeRoom,createBy,img,id);
        }
        ra.addFlashAttribute("success", "The brand has been saved successfully");
        return "redirect:/admin/auctionroom";
    }

//    @GetMapping("/admin/auctionroom/delete/{id}")
//    public String deleteModel(@PathVariable(value = "id") Long id, RedirectAttributes ra) {
//        try {
//            roomService.delete(id);
//        } catch (NotFoundException e) {
//            ra.addFlashAttribute("message", e.getMessage());
//            return "page404";
//        }
//        ra.addFlashAttribute("success", "The Auction Room has been deleted successfully");
//        return "redirect:/admin/auctionroom";
//    }
}
