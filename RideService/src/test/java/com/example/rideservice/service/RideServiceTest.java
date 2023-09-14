package com.example.rideservice.service;

import com.example.rideservice.entity.Ride;
import com.example.rideservice.repository.RideRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class RideServiceTest {

    @Mock
    RideRepository rideRepository;
    @InjectMocks
    RideService rideService;

    @Test
    void testRide(){


    }
}
