package com.example.rh_cv_automatisation.Common.dtos.request;

import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.RecruteurRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class PropositionEntretienRequestDTO {
    private LocalDateTime dateHeure;
    //private Long offreId;
    private Long recruteurId;
    private Long candidateId;
}
