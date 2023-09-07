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

    public Ride createRide(Date date,String departTown,String arrivalTown) {
        Ride ride = Ride.builder().date(date).departTown(departTown).arrivalTown(arrivalTown).build();
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
                .userDTO(restClient.getUtilisateur("user/"+userId, UserDTO.class))
                .build();
        return rideResponseDTO;
    }
    public void deleteRide(int id) {
            Ride ride = getRideById(id);
            rideRepository.delete(ride);
    }

    public Ride updateRide(int id, Ride ride) {
        Ride oldRide= getRideById(id);
        oldRide.setDate(ride.getDate());
        oldRide.setDepartTown(ride.getDepartTown());
        oldRide.setArrivalTown(ride.getArrivalTown());
            return rideRepository.save(oldRide);

    }

}
