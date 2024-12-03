package com.example.EcoSight.repository;


import com.example.EcoSight.entity.Photo.Photo;
import com.example.EcoSight.entity.Photo.PhotoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, PhotoId> {
}
