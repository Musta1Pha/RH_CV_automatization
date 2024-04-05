package com.example.rh_cv_automatisation.jobOfferManagement.dtos.response;

import com.example.rh_cv_automatisation.jobOfferManagement.enums.status;
import lombok.*;

import java.util.Date;

@Data @Builder @AllArgsConstructor @NoArgsConstructor @ToString
public class CandidatureResponseDTO {
    private Long ID_Candidature;
    private status status;
    private Date dateCreation;
    private double noteEntretien;
    private double finalPercentage;
    private OfferEmploiResponseDTO offreEmploi;


}
