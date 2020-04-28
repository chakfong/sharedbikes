package com.fly.rental.web;

import com.fly.common.dto.RespDTO;
import com.fly.rental.entity.Track;
import com.fly.rental.entity.Transaction;
import com.fly.rental.service.RentalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    RentalService rentalService;

    @ApiOperation(value = "租赁", notes = "租赁")
    @GetMapping("/lease")
    public RespDTO leaseBike(@RequestParam("userId") Long userId, @RequestParam("bikeId") Long bikeId,
                             @RequestParam("lat") Double lat, @RequestParam("lng") Double lng) {
        Track track = rentalService.leaseBike(userId, bikeId, lat, lng);
        return RespDTO.onSuc(track);
    }

    @ApiOperation(value = "租赁结算", notes = "租赁结算")
    @PostMapping("/settle")
    public RespDTO settleRental(@RequestParam("trackId")Long trackId,
                                @RequestParam("lat") Double lat,
                                @RequestParam("lng") Double lng){
        Track track = rentalService.findTrackById(trackId);
        Transaction transaction = rentalService.finish(track,lat,lng);
        return RespDTO.onSuc(transaction);
    }
}
