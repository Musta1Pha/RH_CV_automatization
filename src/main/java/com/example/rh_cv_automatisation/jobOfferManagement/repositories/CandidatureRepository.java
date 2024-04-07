package com.example.rh_cv_automatisation.jobOfferManagement.repositories;

import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature,Long> {
    @Query("select c FROM Candidature c WHERE c.candidate = :candidate AND c.offreEmploi = :offreEmploi")
    Candidature chercherCandidadureExiste(@Param("candidate") Candidate candidate, @Param("offreEmploi") OffreEmploi offreEmploi);

    List<Candidature> findAllByCandidate(Candidate candidate);
    List<Candidature> findAllByOffreEmploi(OffreEmploi offreEmploi);
}
