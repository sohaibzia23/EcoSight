package com.example.EcoSight.services;


import com.example.EcoSight.entity.Photo.Photo;
import com.example.EcoSight.entity.Photo.PhotoId;
import com.example.EcoSight.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public Photo addPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public void deletePhoto(PhotoId photoId) {
        photoRepository.deleteById(photoId);
    }

}
