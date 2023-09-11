package com.example.commentservice.utils;


import com.example.commentservice.dto.CommentDtoRequest;
import com.example.commentservice.dto.CommentDtoResponse;
import com.example.commentservice.entity.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public CommentDtoResponse mapToDto(Comment comment) {

        ModelMapper mapper = new ModelMapper();
        CommentDtoResponse commentDtoResponse = mapper.map(comment, CommentDtoResponse.class);

        return commentDtoResponse;

    }

    public Comment mapToEntity(CommentDtoRequest commentDtoRequest) {

        ModelMapper mapper = new ModelMapper();
        Comment comment = mapper.map(commentDtoRequest, Comment.class);
        return  comment;


    }
}
