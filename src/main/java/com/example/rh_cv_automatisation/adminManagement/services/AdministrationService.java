package com.example.rh_cv_automatisation.adminManagement.services;

import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.RecruteurRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.Role_RecruteurRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.response.RecruteurResponseDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.response.Role_RecruteurResponseDTO;

import java.util.List;

public interface AdministrationService {
    RecruteurResponseDTO addRecruteur(RecruteurRequestDTO recruteurRequestDTO,List<Long> rolesIds);

    Role_RecruteurResponseDTO addRole(Role_RecruteurRequestDTO roleRecruteurDTO);

    RecruteurResponseDTO assignmentRoles(Long id, List<Long> roles);

    RecruteurResponseDTO removeRoles(Long id, List<Long> rolesId);
    List<Role_RecruteurResponseDTO> deleteRole(Long id);
}
