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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        if (!list.isEmpty()) {
            view = new ModelAndView("admin/index");
            view.addObject("rooms", list);
        } else {
            view = new ModelAndView("admin/index");
            view.addObject("lst_empty", "List Empty!");
            view.addObject("rooms", list);
        }
        //
        if (!list1.isEmpty()) {
            view = new ModelAndView("admin/index");
            view.addObject("rooms1", list1);
        } else {
            view = new ModelAndView("admin/index");
            view.addObject("lst_empty1", "List Empty!");
        }
        if (!list2.isEmpty()) {
            view = new ModelAndView("admin/index");
            view.addObject("rooms2", list2);
        } else {
            view = new ModelAndView("admin/index");
            view.addObject("lst_empty2", "List Empty!");
        }
        return view;
    }

}
