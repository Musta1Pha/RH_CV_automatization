package com.example.rh_cv_automatisation.jobOfferManagement.dtos.request;

import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import com.example.rh_cv_automatisation.jobOfferManagement.enums.status;
import lombok.*;

import java.util.Date;

@Data @Builder @AllArgsConstructor @NoArgsConstructor @ToString
public class CandidatureRequestDTO {
    private status status;
    private Date dateCreation;
    private double noteEntretien;
    private OffreEmploi offreEmploi;
    private Candidate candidate;
}
