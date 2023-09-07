package com.example.rideservice.repository;

import com.example.rideservice.entity.Ride;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends CrudRepository<Ride,Integer> {

    List<Ride> findRideByUserId(int userId);

}
