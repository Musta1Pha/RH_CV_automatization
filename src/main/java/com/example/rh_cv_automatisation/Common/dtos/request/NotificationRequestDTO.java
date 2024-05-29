package com.example.rh_cv_automatisation.Common.dtos.request;

import com.example.rh_cv_automatisation.Common.entities.Utilisateur;
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
public class NotificationRequestDTO {
    private String message;
    private Date date_notification;
    private Utilisateur utilisateur;
}
