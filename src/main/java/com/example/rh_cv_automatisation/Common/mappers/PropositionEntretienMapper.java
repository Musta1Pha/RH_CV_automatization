package com.example.rh_cv_automatisation.Common.mappers;

import com.example.rh_cv_automatisation.Common.dtos.request.PropositionEntretienRequestDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.PropositionEntretienResponseDTO;
import com.example.rh_cv_automatisation.Common.entities.PropositionEntretien;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropositionEntretienMapper implements BaseMapper<PropositionEntretien, PropositionEntretienRequestDTO, PropositionEntretienResponseDTO> {
    @Override
    public PropositionEntretienResponseDTO entityToDto(PropositionEntretien propositionEntretien) {
        PropositionEntretienResponseDTO propositionEntretienResponseDTO = new PropositionEntretienResponseDTO();
        BeanUtils.copyProperties(propositionEntretien,PropositionEntretienResponseDTO.class);
        return propositionEntretienResponseDTO;
    }

    @Override
    public List<PropositionEntretien> dtoToEntity(List<PropositionEntretienRequestDTO> propositionEntretienRequestDTOS) {
        return null;
    }

    @Override
    public PropositionEntretien dtoToEntity(PropositionEntretienRequestDTO propositionEntretienRequestDTO) {
        PropositionEntretien propositionEntretien = new PropositionEntretien();
        BeanUtils.copyProperties(propositionEntretienRequestDTO,PropositionEntretien.class);
        return propositionEntretien;
    }

    @Override
    public List<PropositionEntretienResponseDTO> entityToDto(List<PropositionEntretien> propositionEntretiens) {
        return null;
    }
}
