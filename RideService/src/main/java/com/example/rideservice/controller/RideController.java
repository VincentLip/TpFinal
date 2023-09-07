package com.example.rideservice.controller;

import com.example.rideservice.dto.RideResponseDTO;
import com.example.rideservice.entity.Ride;
import com.example.rideservice.service.RideService;
import com.example.rideservice.tool.RestClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/ride")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }


    @PostMapping("")
    public ResponseEntity<Ride> post(@RequestParam Date date, @RequestParam String departTown, @RequestParam String arrivalTown,  @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        RestClient<String, String> restClient = new RestClient<>();
        if(restClient.testToken(token, String.class)) {
            Ride ride = rideService.createRide(date,departTown,arrivalTown );
            return ResponseEntity.ok(ride);
        }
        return ResponseEntity.status(401).body(null);
    }

    @GetMapping("")
    public ResponseEntity<List<Ride>> getAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        RestClient<String, String> restClient = new RestClient<>();
        if(restClient.testToken(token, String.class)) {
            List<Ride> rides = rideService.getAllRide();
            return ResponseEntity.ok(rides);
        }
        return ResponseEntity.status(401).body(null);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<RideResponseDTO> getUser(@PathVariable int userId,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        RestClient<String, String> restClient = new RestClient<>();
        if(restClient.testToken(token, String.class)) {
            return ResponseEntity.ok(rideService.getRideByUserId(userId));
        }
        return ResponseEntity.status(401).body(null);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ride> get(@PathVariable int id,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        RestClient<String, String> restClient = new RestClient<>();
        if(restClient.testToken(token, String.class)) {
            Ride ride = rideService.getRideById(id);
            return ResponseEntity.ok(ride);
        }
        return ResponseEntity.status(401).body(null);
    }

}
