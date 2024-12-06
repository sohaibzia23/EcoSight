package com.example.EcoSight.repository;

import com.example.EcoSight.entity.User.Contributor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributorRepository extends JpaRepository<Contributor, Integer> {
}
