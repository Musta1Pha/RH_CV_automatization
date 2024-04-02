package com.example.rh_cv_automatisation.jobOfferManagement.repositories;

import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.RequiredSkills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequiredSkillsRepository extends JpaRepository<RequiredSkills,Long> {
}
