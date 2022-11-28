package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.model.ModelCarModel;
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
}
