package com.example.rh_cv_automatisation.recruiterManagement.mappers;

import com.example.rh_cv_automatisation.Common.mappers.BaseMapper;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.Role_RecruteurRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.response.Role_RecruteurResponseDTO;
import com.example.rh_cv_automatisation.recruiterManagement.entities.Role_Recruteur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class roleRecruteurMapper implements BaseMapper<Role_Recruteur, Role_RecruteurRequestDTO, Role_RecruteurResponseDTO> {
    @Override
    public Role_RecruteurResponseDTO entityToDto(Role_Recruteur roleRecruteur) {
        Role_RecruteurResponseDTO roleRecruteurResponseDTO = new Role_RecruteurResponseDTO();
        BeanUtils.copyProperties(roleRecruteur,roleRecruteurResponseDTO);

        return roleRecruteurResponseDTO;
    }

    @Override
    public List<Role_Recruteur> entityToDto(List<Role_RecruteurRequestDTO> roleRecruteurRequestDTOS) {
        List<Role_Recruteur> roles = roleRecruteurRequestDTOS.stream()
                .map(roleDTO -> dtoToEntity(roleDTO))
                .collect(Collectors.toList());

        return roles;
    }

    @Override
    public Role_Recruteur dtoToEntity(Role_RecruteurRequestDTO roleRecruteurRequestDTO) {
        Role_Recruteur roleRecruteur = new Role_Recruteur();
        BeanUtils.copyProperties(roleRecruteurRequestDTO,roleRecruteur);

        return roleRecruteur;
    }


}
