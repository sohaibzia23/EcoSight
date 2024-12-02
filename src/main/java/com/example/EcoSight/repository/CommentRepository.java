package com.example.EcoSight.repository;


import com.example.EcoSight.entity.Comment.Comment;
import com.example.EcoSight.entity.Comment.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentId> {

    List<Comment> getAllCommentsOnASighting(Integer sightingID);

    List<Comment> getAllCommentsByAUser(Integer userID);

    List<Comment> getAllCommentsByAResearcher(Integer researcherID);


}
