package com.example.rh_cv_automatisation.recruiterManagement.dtos.request;

import com.example.rh_cv_automatisation.recruiterManagement.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class RecruteurRequestDTO {
    private String nom;
    private String prenom;
    private String email;
    private Status status;
    private String password;
    private List<Role_RecruteurRequestDTO> roles;
}
