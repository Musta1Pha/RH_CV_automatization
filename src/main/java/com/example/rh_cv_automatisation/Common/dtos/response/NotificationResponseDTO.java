package com.example.rh_cv_automatisation.Common.dtos.response;

import com.example.rh_cv_automatisation.Common.entities.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class NotificationResponseDTO {
    private Long id;
    private String message;
    private Date date_notification;
    @JsonIgnore
    private Utilisateur utilisateur;
}
