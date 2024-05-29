package com.example.rh_cv_automatisation.Common.dtos.response;

import com.example.rh_cv_automatisation.recruiterManagement.dtos.response.RecruteurResponseDTO;
import com.example.rh_cv_automatisation.recruiterManagement.entities.Recruteur;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class HoraireDisponibleResponseDTO {
    private Long id;
    private RecruteurResponseDTO recruteur;
    private LocalDateTime DateHeureDebut;
    private LocalDateTime DateHeureFin;
}
