package com.example.rh_cv_automatisation.Common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date date_notification;

    @ManyToOne
    private Utilisateur utilisateur;
}
