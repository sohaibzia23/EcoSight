package com.example.EcoSight.controllers;



import com.example.EcoSight.entity.Comment.Comment;
import com.example.EcoSight.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor


public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        try{
            Comment newComment = commentService.addComment(comment);
            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
        }

        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
        try {
            commentService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
