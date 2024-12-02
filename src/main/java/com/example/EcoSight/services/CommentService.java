package com.example.EcoSight.services;


import com.example.EcoSight.contributor.ContributorRepository;
import com.example.EcoSight.entity.Comment.Comment;
import com.example.EcoSight.entity.Comment.CommentId;
import com.example.EcoSight.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsBySighting(Integer sightingID) {
        return commentRepository.getAllCommentsOnASighting(sightingID);
    }

    public List<Comment> getCommentsByUser(Integer userID) {
        return commentRepository.getAllCommentsByAUser(userID);
    }

    public List<Comment> getCommentsByResearcher(Integer researcherID) {
        return commentRepository.getAllCommentsByAResearcher(researcherID);
    }

    public void deleteComment(CommentId commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            commentRepository.deleteById(commentId);
        }
        else {
            throw new RuntimeException("COMMENT NOT FOUND");
        }
    }

}
