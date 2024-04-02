package com.example.rh_cv_automatisation.jobOfferManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class RequiredSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skill;
}
