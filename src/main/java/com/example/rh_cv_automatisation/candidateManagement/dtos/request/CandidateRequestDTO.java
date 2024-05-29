package com.example.rh_cv_automatisation.candidateManagement.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CandidateRequestDTO {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private File cv;
}
