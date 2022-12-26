package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.service.TransactionService;
import com.workshop.carauctionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.crypto.Credentials;
import org.web3j.ens.EnsResolver;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.lang.String;
import java.lang.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Controller
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    UserService userService;

    @PostMapping("/saveTransaction")
    public ResponseEntity<ResponseObject> saveTransaction(@CookieValue(value = "setUserId") int setUserId,
                                                          @RequestParam("carId") long carId, @RequestParam("transactionHash") String transactionHash,
                                                          @RequestParam("status") int status) {
        User u1 = userService.findUserById(setUserId);
        //Room r1 = service.getRoomById(Integer.parseInt(roomId));
        Date date = new Date();
        transactionService.saveTransaction(u1.getId(), carId, transactionHash, status, new Timestamp(date.getTime()), new Timestamp(date.getTime()));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Succeed!", null));

    }
}
