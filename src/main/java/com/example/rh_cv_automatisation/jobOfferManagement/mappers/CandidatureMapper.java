package com.example.rh_cv_automatisation.jobOfferManagement.mappers;

import com.example.rh_cv_automatisation.Common.mappers.BaseMapper;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.CandidatureRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
            CandidatureResponseDTO candidatureResponseDTO = entityToDto(candidature);
            candidatureResponseDTOS.add(candidatureResponseDTO);
        }

        return candidatureResponseDTOS;
    }
}
