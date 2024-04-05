package com.example.rh_cv_automatisation.jobOfferManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class OffreEmploi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Offer;
    private String title;
    private String description;
    private String formation;
    private double experience;

    @ManyToMany
    private List<RequiredSkills> requiredSkills;

    private String offerBenefits;
    private String location;

    @OneToMany(mappedBy = "offreEmploi")
    private List<Candidature> candidatures;
}
