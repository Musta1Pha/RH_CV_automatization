package com.example.rh_cv_automatisation.candidateManagement.repositories;

import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
}
