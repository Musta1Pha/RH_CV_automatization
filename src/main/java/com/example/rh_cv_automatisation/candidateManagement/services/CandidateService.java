package com.example.rh_cv_automatisation.candidateManagement.services;

import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;

public interface CandidateService {
    CandidateResponseDTO postulerOffre(Long CandidateId , Long OfferId);
}
