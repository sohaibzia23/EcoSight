package com.example.EcoSight.controllers;


import com.example.EcoSight.entity.Photo.Photo;
import com.example.EcoSight.entity.Photo.PhotoId;
import com.example.EcoSight.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor

public class PhotoController {

    private final PhotoService photoService;

    @PostMapping
    public ResponseEntity<Photo> addPhoto(@RequestBody Photo photo) {
        try {
            Photo newPhoto = photoService.addPhoto(photo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePhoto(@PathVariable PhotoId photoId) {
        try {
            photoService.deletePhoto(photoId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
