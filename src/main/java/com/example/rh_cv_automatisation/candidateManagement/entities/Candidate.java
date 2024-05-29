package com.example.rh_cv_automatisation.candidateManagement.entities;

import com.example.rh_cv_automatisation.Common.entities.Utilisateur;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.File;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Candidate extends Utilisateur {
    private File cv;

    @OneToMany(mappedBy = "candidate")
    private List<Candidature> candidatures;
    private boolean verified;
    private String verificationToken;

}
