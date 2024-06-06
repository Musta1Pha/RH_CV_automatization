package com.example.rh_cv_automatisation.jobOfferManagement.dtos.request;

import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.RequiredSkills;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OfferEmploiRequestDTO {
    private String title;
    private String description;
    private int formation;
    private int experience;
    private List<RequiredSkillsRequestDTO> requiredSkills;
    private String offerBenefits;
    private String location;

}
