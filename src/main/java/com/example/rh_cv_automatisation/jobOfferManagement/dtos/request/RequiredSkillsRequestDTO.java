package com.example.rh_cv_automatisation.jobOfferManagement.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RequiredSkillsRequestDTO {
    private String skill;
}
