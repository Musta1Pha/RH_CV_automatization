package com.example.rh_cv_automatisation.jobOfferManagement.repositories;

import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatureRepository extends JpaRepository<Candidature,Long> {
}
