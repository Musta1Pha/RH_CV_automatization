package com.example.rh_cv_automatisation.recruiterManagement.mappers;

import com.example.rh_cv_automatisation.Common.mappers.BaseMapper;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.RecruteurRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.response.RecruteurResponseDTO;
import com.example.rh_cv_automatisation.recruiterManagement.entities.Recruteur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecruteurMapper implements BaseMapper<Recruteur, RecruteurRequestDTO, RecruteurResponseDTO> {
    private roleRecruteurMapper roleRecruteurMapper;

    public RecruteurMapper(com.example.rh_cv_automatisation.recruiterManagement.mappers.roleRecruteurMapper roleRecruteurMapper) {
        this.roleRecruteurMapper = roleRecruteurMapper;
    }

    @Override
    public RecruteurResponseDTO entityToDto(Recruteur recruteur) {
        RecruteurResponseDTO recruteurResponseDTO = new RecruteurResponseDTO();
        BeanUtils.copyProperties(recruteur,recruteurResponseDTO);
        recruteurResponseDTO.setRoles(roleRecruteurMapper.entityToDto(recruteur.getRoles()));
        return recruteurResponseDTO;
    }

    @Override
    public List<Recruteur> dtoToEntity(List<RecruteurRequestDTO> recruteurRequestDTOS) {
        return null;
    }

    @Override
    public Recruteur dtoToEntity(RecruteurRequestDTO recruteurRequestDTO) {
        Recruteur recruteur = new Recruteur();
        BeanUtils.copyProperties(recruteurRequestDTO,recruteur);

        return recruteur;
    }

    @Override
    public List<RecruteurResponseDTO> entityToDto(List<Recruteur> recruteurs) {
        List<RecruteurResponseDTO> recruteurResponseDTO = new ArrayList<>();
        for(Recruteur recruteur:recruteurs){
            recruteurResponseDTO.add(entityToDto(recruteur));
        }
        return recruteurResponseDTO;
    }

}
