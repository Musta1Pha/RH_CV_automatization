package com.example.rh_cv_automatisation.recruiterManagement.entities;
import com.example.rh_cv_automatisation.Common.entities.Utilisateur;
import com.example.rh_cv_automatisation.recruiterManagement.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Recruteur extends Utilisateur {
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany
    private List<Role_Recruteur> roles;
}
