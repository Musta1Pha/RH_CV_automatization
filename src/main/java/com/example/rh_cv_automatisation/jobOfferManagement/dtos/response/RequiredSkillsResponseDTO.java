package com.example.rh_cv_automatisation.jobOfferManagement.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class RequiredSkillsResponseDTO {
    private Long id;
    private String skill;
}
