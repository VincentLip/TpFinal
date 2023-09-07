package com.example.rideservice.dto;

import com.example.rideservice.entity.Ride;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RideResponseDTO {

    private List<Ride> rides;
    private UserDTO userDTO;
    private UserDTO userDTO1;

}
