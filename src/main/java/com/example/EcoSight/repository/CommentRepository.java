package com.example.EcoSight.repository;


import com.example.EcoSight.entity.Comment.Comment;
import com.example.EcoSight.entity.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> getAllCommentsOnASighting(Integer sightingID);

    List<Comment> getAllCommentsByAUser(Integer userID);

    List<Comment> getAllCommentsByAResearcher(Integer researcherID);


    interface ResearcherRepository extends JpaRepository<Contributor.Researcher, Integer> {


        Optional<Contributor.Researcher> findByID(Integer researcherID);


    }
}
