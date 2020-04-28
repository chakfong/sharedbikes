package com.fly.rental.web;

import com.fly.common.dto.RespDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/ticket")
public class TicketController {

    @ApiOperation(value = "根据用户ID获取流水", notes = "根据用户ID获取流水")
    @PostMapping("/post")
    public RespDTO postTicket() {
        return RespDTO.onSuc(null);
    }
}
