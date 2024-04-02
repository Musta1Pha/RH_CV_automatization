package com.example.rh_cv_automatisation.adminManagement.entities;

import com.example.rh_cv_automatisation.Common.entities.Utilisateur;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity @Data @AllArgsConstructor @Builder
public class Administrateur extends Utilisateur {
}
