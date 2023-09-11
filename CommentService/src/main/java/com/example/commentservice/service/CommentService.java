package com.example.commentservice.service;

import com.example.commentservice.dto.CommentDtoRequest;
import com.example.commentservice.dto.CommentDtoResponse;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.repository.CommentRepository;
import com.example.commentservice.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CommentService {


    Comment createComment(String rideId,String title,String description);

    List<CommentDtoResponse> getAllComments();

    CommentDtoResponse getCommentById(String commentId);

    boolean deleteCommentById(String commentId);

}
