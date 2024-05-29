package com.example.rh_cv_automatisation.Common.mappers;

import com.example.rh_cv_automatisation.Common.dtos.request.EntretienRequestDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.EntretienResponseDTO;
import com.example.rh_cv_automatisation.Common.entities.Entretien;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntretienMapper implements BaseMapper<Entretien, EntretienRequestDTO, EntretienResponseDTO> {

    @Override
    public EntretienResponseDTO entityToDto(Entretien entretien) {
        EntretienResponseDTO entretienResponseDTO = new EntretienResponseDTO();
        BeanUtils.copyProperties(entretien,entretienResponseDTO);
        return entretienResponseDTO;
    }

    @Override
    public List<Entretien> dtoToEntity(List<EntretienRequestDTO> entretienRequestDTOS) {
        List<Entretien> entretiens = new ArrayList<>();
        for(EntretienRequestDTO entretienRequestDTO : entretienRequestDTOS){
            Entretien entretien = this.dtoToEntity(entretienRequestDTO);
            entretiens.add(entretien);
        }
        return entretiens;
    }

    @Override
    public Entretien dtoToEntity(EntretienRequestDTO entretienRequestDTO) {
        Entretien entretien = new Entretien();
        BeanUtils.copyProperties(entretienRequestDTO,entretien);
        return entretien;
    }

    @Override
    public List<EntretienResponseDTO> entityToDto(List<Entretien> entretiens) {
        List<EntretienResponseDTO> entretienResponseDTOS = new ArrayList<>();
        for(Entretien entretien : entretiens){
            EntretienResponseDTO entretienResponseDTO = this.entityToDto(entretien);
            entretienResponseDTOS.add(entretienResponseDTO);
        }
        return entretienResponseDTOS;
    }
}
