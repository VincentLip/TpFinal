package com.example.commentservice.service.Impl;

import com.example.commentservice.dto.CommentDtoRequest;
import com.example.commentservice.dto.CommentDtoResponse;
import com.example.commentservice.entity.Comment;
import com.example.commentservice.repository.CommentRepository;
import com.example.commentservice.service.CommentService;
import com.example.commentservice.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private Mapper mapper;

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(String rideId, String title, String description) {
        Comment comment = Comment.builder().rideId(rideId).title(title).description(description).build();
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public List<CommentDtoResponse> getAllComments() {
        List<Comment> comments = (List<Comment>) commentRepository.findAll();
        List<CommentDtoResponse> commentsDtoResponses = new ArrayList<>();
        for (Comment c: comments) {
            CommentDtoResponse commentsDtoResponse = mapper.mapToDto(c);
            commentsDtoResponses.add(commentsDtoResponse);
        }
        return commentsDtoResponses;
    }

    @Override
    public CommentDtoResponse getCommentById(String commentId) {
        Optional<Comment> comments = commentRepository.findById(commentId);
        if(comments.isPresent()){
            return mapper.mapToDto(comments.get());
        }
        return null;
    }

    @Override
    public boolean deleteCommentById(String commentId) {
            Comment comment = getCommentByIdEntity(commentId);
            commentRepository.delete(comment);
            return true;
    }

    private Comment getCommentByIdEntity(String commentId) {
        Optional<Comment> comments = commentRepository.findById(commentId);
        if(comments.isPresent()){
            return comments.get();
        }
        return null;
    }
}
