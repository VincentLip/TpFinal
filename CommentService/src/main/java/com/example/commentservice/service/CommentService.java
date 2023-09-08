package com.example.commentservice.service;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(int riderId, String title, String description) {
        Comment comment = Comment.builder().rideId(riderId).title(title).description(description).build();
        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> getAllComment(){
        return (List<Comment>) commentRepository.findAll();
    }

    public Comment getCommentById(int id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isPresent()) {
            return commentOptional.get();
        }
        throw new RuntimeException("Not found");
    }

    public void deleteRide(int id) {
        Comment comment = getCommentById(id);
        commentRepository.delete(comment);
    }

    public Comment updateComment(int id, Comment comment) {
        Comment oldComment= getCommentById(id);
        oldComment.setTitle(comment.getTitle());
        oldComment.setDescription(comment.getDescription());
        return commentRepository.save(oldComment);

    }
}
