package com.example.rh_cv_automatisation.candidateManagement.dtos.request;

import com.example.rh_cv_automatisation.Common.entities.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CandidateRequestDTO {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private List<Notification> notifications;
    private File cv;
}
