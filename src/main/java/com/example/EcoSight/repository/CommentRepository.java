package com.example.EcoSight.repository;

import com.example.EcoSight.entity.Comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
