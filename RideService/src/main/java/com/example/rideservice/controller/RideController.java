package com.example.rideservice.controller;

import com.example.rideservice.dto.RideResponseDTO;
import com.example.rideservice.dto.UserDTO;
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
    public ResponseEntity<Ride> postRide( @RequestParam Date date,@RequestParam String depart, @RequestParam String arrival,@RequestParam int userId,  @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        RestClient<String, String> restClient = new RestClient<>();
        UserDTO userDTO = getUser(userId, token).getBody().getUserDTO();
        if(restClient.testToken(token, String.class) && userDTO.isDriver()) {
            Ride ride = rideService.createRide(date,depart,arrival,userId);
            return ResponseEntity.ok(ride);
        }
        return ResponseEntity.status(401).body(null);
    }

    @GetMapping("")
    public ResponseEntity<List<Ride>> getAllRide(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
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

    @PutMapping("{rideId}/add/{userId}")
    public ResponseEntity<Ride> addUser(@PathVariable int rideId, @PathVariable int userId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        RestClient<String, String> restClient = new RestClient<>();
        if(restClient.testToken(token, String.class)) {
            Ride ride = rideService.updateUserRide(rideId,userId);
            System.out.println(ride);
            return ResponseEntity.ok(ride);
        }
        return ResponseEntity.status(401).body(null);
    }


    @RequestMapping(value = "/arrival/{arrival}", method = RequestMethod.GET)
    public List<Ride> getRidebyArrival(@PathVariable("arrival") String arrival, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        List<Ride> rides = rideService.getRideByArrival(arrival);
        return rides;
    }

    @GetMapping("{rideId}")
    public ResponseEntity<Ride> get(@PathVariable int rideId,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        RestClient<String, String> restClient = new RestClient<>();
        if(restClient.testToken(token, String.class)) {
            Ride ride = rideService.getRideById(rideId);
            return ResponseEntity.ok(ride);
        }
        return ResponseEntity.status(401).body(null);
    }

    @DeleteMapping("delete/{rideId}")
    public void deleteRidebyId(@PathVariable int rideId,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        RestClient<String, String> restClient = new RestClient<>();

        if(restClient.testToken(token, String.class)) {
            rideService.deleteRide(rideId);
        }

    }

}
