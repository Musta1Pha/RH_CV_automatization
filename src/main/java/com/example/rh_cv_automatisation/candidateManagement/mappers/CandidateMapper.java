package com.example.rh_cv_automatisation.candidateManagement.mappers;

import com.example.rh_cv_automatisation.Common.mappers.BaseMapper;
import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.CandidatureRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandidateMapper implements BaseMapper<Candidate, CandidateRequestDTO, CandidateResponseDTO> {
    @Override
    public CandidateResponseDTO entityToDto(Candidate candidate) {
        CandidateResponseDTO candidateResponseDTO = new CandidateResponseDTO();
        BeanUtils.copyProperties(candidate,candidateResponseDTO);
        return candidateResponseDTO;
    }
    @Override
    public List<Candidate> entityToDto(List<CandidateRequestDTO> candidateRequestDTOS) {
        return null;
    }

    @Override
    public Candidate dtoToEntity(CandidateRequestDTO candidateRequestDTO) {
        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(candidateRequestDTO,candidate);
        return candidate;
    }
}
