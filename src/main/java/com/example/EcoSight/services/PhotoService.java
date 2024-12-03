package com.example.EcoSight.services;


import com.example.EcoSight.entity.Photo.Photo;
import com.example.EcoSight.entity.Photo.PhotoId;
import com.example.EcoSight.repository.PhotoRepository;
import com.example.EcoSight.repository.SightingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PhotoService {

    private final PhotoRepository photoRepository;


    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;

    }

    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public void deletePhoto(PhotoId photoId) {
        photoRepository.deleteById(photoId);
    }


    public Optional<Photo> getPhotoById(PhotoId photoId) {
        return photoRepository.findById(photoId);
    }

    public List<Photo> getPhotoBySightingId(Integer sightingId) {
        return photoRepository.findById(sightingId);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
}
