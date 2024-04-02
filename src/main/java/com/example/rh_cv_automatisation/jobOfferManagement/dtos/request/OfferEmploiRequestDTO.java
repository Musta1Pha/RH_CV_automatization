package com.example.rh_cv_automatisation.jobOfferManagement.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OfferEmploiRequestDTO {
    private String title;
    private String description;
    private List<RequiredSkillsRequestDTO> requiredSkills;
    private String offerBenefits;
    private String location;
}
