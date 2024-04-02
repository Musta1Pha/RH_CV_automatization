package com.example.rh_cv_automatisation.jobOfferManagement.entities;

import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.jobOfferManagement.enums.status;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_Candidature;
    private status status;
    private Date dateCreation;
    private double noteEntretien;
    @ManyToOne
    private OffreEmploi offreEmploi;
    @ManyToOne
    private Candidate candidate;
}
