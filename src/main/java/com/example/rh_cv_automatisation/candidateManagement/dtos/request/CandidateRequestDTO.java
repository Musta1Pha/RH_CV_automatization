package com.example.rh_cv_automatisation.candidateManagement.dtos.request;

import com.example.rh_cv_automatisation.candidateManagement.entities.CvData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CandidateRequestDTO {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private CvDataRequestDTO cv;
}
