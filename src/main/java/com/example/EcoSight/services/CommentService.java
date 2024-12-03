package com.example.EcoSight.services;


import com.example.EcoSight.entity.Comment.Comment;
import com.example.EcoSight.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Integer commendId) {
        commentRepository.deleteById(commendId);
    }
}
