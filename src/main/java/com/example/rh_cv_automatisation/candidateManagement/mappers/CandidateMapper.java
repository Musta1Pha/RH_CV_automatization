package com.example.rh_cv_automatisation.candidateManagement.mappers;

import com.example.rh_cv_automatisation.Common.entities.Notification;
import com.example.rh_cv_automatisation.Common.mappers.BaseMapper;
import com.example.rh_cv_automatisation.Common.mappers.NotificationMapper;
import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.CandidatureMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidateMapper implements BaseMapper<Candidate, CandidateRequestDTO, CandidateResponseDTO> {
    private CandidatureMapper candidatureMapper ;
    private NotificationMapper notificationMapper;

    public CandidateMapper(CandidatureMapper candidatureMapper, NotificationMapper notificationMapper) {
        this.candidatureMapper = candidatureMapper;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public CandidateResponseDTO entityToDto(Candidate candidate) {
        CandidateResponseDTO candidateResponseDTO = new CandidateResponseDTO();
        BeanUtils.copyProperties(candidate,candidateResponseDTO);
        if(candidate.getCandidatures() != null)
            candidateResponseDTO.setCandidatures(candidatureMapper.entityToDto(candidate.getCandidatures()));
        if(candidate.getNotifications() != null)
            candidateResponseDTO.setNotifications(notificationMapper.entityToDto(candidate.getNotifications()));

        return candidateResponseDTO;
    }

    @Override
    public List<Candidate> dtoToEntity(List<CandidateRequestDTO> candidateRequestDTOS) {
        return null;
    }


    @Override
    public Candidate dtoToEntity(CandidateRequestDTO candidateRequestDTO) {
        Candidate candidate = new Candidate();
        BeanUtils.copyProperties(candidateRequestDTO,candidate);
        return candidate;
    }

    @Override
    public List<CandidateResponseDTO> entityToDto(List<Candidate> candidates) {
        List<CandidateResponseDTO> candidateResponseDTOS = new ArrayList<>();
        for(Candidate candidate : candidates){
            CandidateResponseDTO candidateResponseDTO = entityToDto(candidate);
            candidateResponseDTOS.add(candidateResponseDTO);
        }

        return candidateResponseDTOS;
    }
}
