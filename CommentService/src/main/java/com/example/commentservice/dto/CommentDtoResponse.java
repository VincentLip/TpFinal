package com.example.commentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDtoResponse {

    private String commentId;
    private String title;
    private String description;
    private int rideId;
}
