package com.example.rideservice.service;

import com.example.rideservice.dto.RideResponseDTO;
import com.example.rideservice.dto.UserDTO;
import com.example.rideservice.entity.Ride;
import com.example.rideservice.repository.RideRepository;
import com.example.rideservice.tool.RestClient;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {

    private final RideRepository rideRepository;


    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public Ride createRide(Date date ,String depart,String arrival,int userId) {
        Ride ride = Ride.builder().date(date).depart(depart).arrival(arrival).userId(userId).build();
        rideRepository.save(ride);
        return ride;
    }

    public List<Ride> getAllRide(){
        return (List<Ride>) rideRepository.findAll();
    }

    public Ride getRideById(int id) {
        Optional<Ride> rideOptional = rideRepository.findById(id);
        if(rideOptional.isPresent()) {
            return rideOptional.get();
        }
        throw new RuntimeException("Not found");
    }

    public RideResponseDTO getRideByUserId(int userId) {
        RestClient<UserDTO, String> restClient = new RestClient<>();
        RideResponseDTO rideResponseDTO = RideResponseDTO.builder()
                .rides(rideRepository.findRideByUserId(userId))
                .userDTO(restClient.getUser("user/"+userId, UserDTO.class))
                .build();
        return rideResponseDTO;
    }

    public List<Ride> getRideByArrival(String arrival) {
        List<Ride> rides = rideRepository.findRideByArrival(arrival);
        return rides;
    }

    public List<Ride> getRideByDepart(String arrival) {
        List<Ride> rides = rideRepository.findRideByDepart(arrival);
        return rides;
    }

    public void deleteRide(int id) {
            Ride ride = getRideById(id);
            rideRepository.delete(ride);
    }

    public Ride updateUserRide(int rideId,int userId) {
        Ride oldRide= getRideById(rideId);
        oldRide.setUserId1(userId);
        return rideRepository.save(oldRide);

    }

    public Ride updateRide(int id, Ride ride) {
        Ride oldRide= getRideById(id);
        oldRide.setDate(ride.getDate());
        oldRide.setDepart(ride.getDepart());
        oldRide.setArrival(ride.getArrival());
            return rideRepository.save(oldRide);

    }

}
