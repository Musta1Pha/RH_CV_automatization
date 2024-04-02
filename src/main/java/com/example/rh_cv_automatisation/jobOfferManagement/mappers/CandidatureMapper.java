package com.example.rh_cv_automatisation.jobOfferManagement.mappers;

import com.example.rh_cv_automatisation.Common.mappers.BaseMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.CandidatureRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandidatureMapper implements BaseMapper<Candidature, CandidatureRequestDTO, CandidatureResponseDTO> {
    @Override
    public CandidatureResponseDTO entityToDto(Candidature candidature) {
        CandidatureResponseDTO candidatureResponseDTO = new CandidatureResponseDTO();
        BeanUtils.copyProperties(candidature,candidatureResponseDTO);
        return candidatureResponseDTO;
    }

    @Override
    public List<Candidature> entityToDto(List<CandidatureRequestDTO> candidatureRequestDTOS) {
        return null;
    }

    @Override
    public Candidature dtoToEntity(CandidatureRequestDTO candidatureRequestDTO) {
        Candidature candidature = new Candidature();
        BeanUtils.copyProperties(candidatureRequestDTO,candidature);
        return candidature;
    }
}
