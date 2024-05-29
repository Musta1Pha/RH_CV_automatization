package com.example.rh_cv_automatisation.Common.dtos.response;

import com.example.rh_cv_automatisation.Common.enums.Resultat;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class EntretienResponseDTO {
    private Long id;
    private HoraireDisponibleResponseDTO heureEntretien;
    private Resultat resultat;
    private CandidateResponseDTO candidate;
}
