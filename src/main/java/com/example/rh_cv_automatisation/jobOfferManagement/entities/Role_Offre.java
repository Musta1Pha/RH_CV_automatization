package com.example.rh_cv_automatisation.jobOfferManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Role_Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Role_name;
}
