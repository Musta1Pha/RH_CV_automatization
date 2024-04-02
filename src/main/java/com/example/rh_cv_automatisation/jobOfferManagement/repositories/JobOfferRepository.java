package com.example.rh_cv_automatisation.jobOfferManagement.repositories;

import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOfferRepository extends JpaRepository<OffreEmploi,Long> {
}
