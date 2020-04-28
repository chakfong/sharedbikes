package com.fly.rental.web;

import com.fly.common.dto.RespDTO;
import com.fly.rental.entity.Transaction;
import com.fly.rental.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @ApiOperation(value = "根据用户ID获取流水", notes = "根据用户ID获取流水")
    @GetMapping("/user/{userId}")
    public RespDTO leaseBike(@PathVariable("userId") Long userId) {
        List<Transaction> transactionList = orderService.getTransactionsByUserId(userId);
        return RespDTO.onSuc(transactionList);
    }
}
