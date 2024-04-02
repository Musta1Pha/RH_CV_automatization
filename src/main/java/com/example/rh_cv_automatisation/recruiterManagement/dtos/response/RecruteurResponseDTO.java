package com.example.rh_cv_automatisation.recruiterManagement.dtos.response;

import com.example.rh_cv_automatisation.recruiterManagement.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RecruteurResponseDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Status status;
    private List<Role_RecruteurResponseDTO> roles;
}
