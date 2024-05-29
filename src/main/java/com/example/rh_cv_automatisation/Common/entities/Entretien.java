package com.example.rh_cv_automatisation.Common.entities;

import com.example.rh_cv_automatisation.Common.enums.Resultat;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.recruiterManagement.entities.Recruteur;
import com.example.rh_cv_automatisation.recruiterManagement.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateHeure;
    @ManyToOne
    private Recruteur recruteur;
    @ManyToOne
    private Candidate candidat;

}
