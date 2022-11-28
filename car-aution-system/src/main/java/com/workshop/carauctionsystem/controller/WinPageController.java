package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.entity.RoomDetailPlayer;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.service.RoomDetailPlayerService;
import com.workshop.carauctionsystem.service.RoomService;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class WinPageController {
    @Autowired
    UserService userService;

    @Autowired
    RoomDetailPlayerService roomDetailPlayerService;

    @Autowired
    RoomService roomService;

    @GetMapping("/winPage")
    public ModelAndView winPage(Model model, @RequestParam("roomId") int roomId){
        List<RoomDetailPlayer> roomDetailPlayerList = roomDetailPlayerService.getAllBidByRoomId(roomId);
        double sum = 0;
        double finalResult;
        double []array = new double[roomDetailPlayerList.size()];
        for (int i = 0; i<roomDetailPlayerList.size(); i++){
            sum += roomDetailPlayerList.get(i).getUserBid();
        }
        double result = sum/roomDetailPlayerList.size();

        for (int i = 0; i<roomDetailPlayerList. size(); i++){
            finalResult = Math.abs(result - roomDetailPlayerList.get(i).getUserBid());
            array[i] = finalResult;
        }
        double min = array[0];
        for (int i = 0; i<array.length; i++){
            if(array[i] < min){
                min = array[i]  ;
            }
        }
        List<RoomDetailPlayer> roomDetailPlayerList1=null;
        for (int i = 0; i<roomDetailPlayerList. size(); i++){
            if(min == Math.abs(result - roomDetailPlayerList.get(i).getUserBid())){
                roomDetailPlayerList1 = roomDetailPlayerService.findWinner(roomDetailPlayerList.get(i).getUserBid(), roomId);
                break;
            }
        }
        if(roomDetailPlayerList1.get(0) != null){
            int winId = roomDetailPlayerList1.get(0).getUserId().getId();
            roomDetailPlayerService.updateWinner(winId, roomId);
        }
        model.addAttribute("winner", roomDetailPlayerList1.get(0));
        model.addAttribute("userList",roomDetailPlayerList);
        model.addAttribute("result",  (double)Math.round(result * 100) / 100);

        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);

        ModelAndView view = new ModelAndView();
        view.setViewName("winner");
        return view;
    }
}
