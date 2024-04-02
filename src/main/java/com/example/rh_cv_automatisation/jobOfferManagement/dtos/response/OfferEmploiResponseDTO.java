package com.example.rh_cv_automatisation.jobOfferManagement.dtos.response;
import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OfferEmploiResponseDTO {
    private Long ID_Offer;
    private String title;
    private String description;
    private List<RequiredSkillsResponseDTO> requiredSkills;
    private String offerBenefits;
    private String location;
    private List<CandidatureResponseDTO> candidatures;
}
