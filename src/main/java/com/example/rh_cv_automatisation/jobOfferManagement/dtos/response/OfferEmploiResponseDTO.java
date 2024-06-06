package com.example.rh_cv_automatisation.jobOfferManagement.dtos.response;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int formation;
    private int experience;
    private List<RequiredSkillsResponseDTO> requiredSkills;
    private String offerBenefits;
    private String location;
    @JsonIgnore
    private List<CandidatureResponseDTO> candidatures;
}
