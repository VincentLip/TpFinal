package com.example.commentservice.controller;

import com.example.commentservice.dto.CommentDtoResponse;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/add")
    public ResponseEntity<Comment> createComment(@RequestParam String rideId , @RequestParam String title, @RequestParam String description){
        return new ResponseEntity<>(commentService.createComment(rideId,title,description), HttpStatus.OK);
    }

    public ResponseEntity<List<CommentDtoResponse>> getAllPComment(){
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable (value = "commentId")String commentId){
        if(commentService.deleteCommentById(commentId)){
            return new ResponseEntity<>("Comment delete ",HttpStatus.OK);
        }
        return new ResponseEntity<>("Error durring deletion",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
