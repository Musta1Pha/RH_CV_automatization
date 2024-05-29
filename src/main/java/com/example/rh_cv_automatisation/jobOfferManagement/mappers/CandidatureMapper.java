package com.example.rh_cv_automatisation.jobOfferManagement.mappers;

import com.example.rh_cv_automatisation.Common.mappers.BaseMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.CandidatureRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidatureMapper implements BaseMapper<Candidature, CandidatureRequestDTO, CandidatureResponseDTO> {
    private JobOfferMapper jobOfferMapper;

    public CandidatureMapper(JobOfferMapper jobOfferMapper) {
        this.jobOfferMapper = jobOfferMapper;
    }

    @Override
    public CandidatureResponseDTO entityToDto(Candidature candidature) {
        CandidatureResponseDTO candidatureResponseDTO = new CandidatureResponseDTO();
        BeanUtils.copyProperties(candidature,candidatureResponseDTO);
        candidatureResponseDTO.setOffreEmploi(jobOfferMapper.entityToDto(candidature.getOffreEmploi()));
        return candidatureResponseDTO;
    }

    @Override
    public List<Candidature> dtoToEntity(List<CandidatureRequestDTO> candidatureRequestDTOS) {
        return null;
    }

    @Override
    public Candidature dtoToEntity(CandidatureRequestDTO candidatureRequestDTO) {
        Candidature candidature = new Candidature();
        BeanUtils.copyProperties(candidatureRequestDTO,candidature);
        return candidature;
    }

    @Override
    public List<CandidatureResponseDTO> entityToDto(List<Candidature> candidatures) {
        List<CandidatureResponseDTO> candidatureResponseDTOS = new ArrayList<>();
        for(Candidature candidature : candidatures){
            candidatureResponseDTOS.add(entityToDto(candidature));
        }
        return candidatureResponseDTOS;
    }
}
