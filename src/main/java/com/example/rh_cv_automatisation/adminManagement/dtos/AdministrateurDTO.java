package com.example.rh_cv_automatisation.adminManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AdministrateurDTO {
    private String nom;
    private String prenom;
    private String email;
    private String password;
}
