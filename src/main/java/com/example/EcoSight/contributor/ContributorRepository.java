package com.example.EcoSight.contributor;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface ContributorRepository extends JpaRepository<Contributor, Integer> {

    Optional<Contributor> findByContributorID(Integer contributorID);

    Optional<Contributor> findByLastName(String uLastName);

    List<Contributor> findAll();


}
