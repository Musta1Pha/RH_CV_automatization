package com.example.rh_cv_automatisation.Common.dtos.response;

import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.RecruteurRequestDTO;

import java.time.LocalDateTime;

public class PropositionEntretienResponseDTO {
    private Long id;
    private LocalDateTime dateHeure;
    private RecruteurRequestDTO recruteur;
    private CandidateRequestDTO candidate;
}
