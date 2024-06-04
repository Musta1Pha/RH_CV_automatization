package com.example.rh_cv_automatisation.candidateManagement.dtos.response;

import com.example.rh_cv_automatisation.Common.dtos.response.NotificationResponseDTO;
import com.example.rh_cv_automatisation.Common.entities.Notification;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateResponseDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private List<NotificationResponseDTO> notifications;
    private CvDataResponseDTO cv;
    private List<CandidatureResponseDTO> candidatures;

}