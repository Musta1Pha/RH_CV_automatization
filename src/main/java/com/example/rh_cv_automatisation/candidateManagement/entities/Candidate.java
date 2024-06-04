package com.example.rh_cv_automatisation.candidateManagement.entities;

import com.example.rh_cv_automatisation.Common.entities.Utilisateur;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import javax.persistence.Lob;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Candidate extends Utilisateur {
    @OneToOne
    private CvData cv;
    @OneToMany(mappedBy = "candidate")
    private List<Candidature> candidatures;
    private boolean verified;
    private String verificationToken;

}
