package com.example.rh_cv_automatisation.candidateManagement.services;

import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.candidateManagement.mappers.CandidateMapper;
import com.example.rh_cv_automatisation.candidateManagement.repositories.CandidateRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.CandidatureRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import com.example.rh_cv_automatisation.jobOfferManagement.enums.status;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.CandidatureMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.CandidatureRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.JobOfferRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CandidateServiceImpl implements CandidateService{
    private JobOfferRepository jobOfferRepository;
    private CandidateRepository candidateRepository;
    private CandidatureRepository candidatureRepository;
    private CandidatureMapper candidatureMapper;
    private CandidateMapper candidateMapper;

    public CandidateServiceImpl(JobOfferRepository jobOfferRepository, CandidateRepository candidateRepository, CandidatureRepository candidatureRepository, CandidatureMapper candidatureMapper, CandidateMapper candidateMapper) {
        this.jobOfferRepository = jobOfferRepository;
        this.candidateRepository = candidateRepository;
        this.candidatureRepository = candidatureRepository;
        this.candidatureMapper = candidatureMapper;
        this.candidateMapper = candidateMapper;
    }

    @Override
    public CandidateResponseDTO postulerOffre(Long CandidateId , Long OfferId) {
        OffreEmploi offreEmploi = jobOfferRepository.findById(OfferId).get();
        Candidate candidate = candidateRepository.findById(CandidateId).get();
        List<Candidature> candidaturesCandidate = candidate.getCandidatures();
        List<Candidature> candidaturesOffreEmploi = offreEmploi.getCandidatures();

        for (Candidature candidature : candidaturesCandidate){
            if(candidature.getOffreEmploi().equals(offreEmploi)){
                return null;
            }
        }

        CandidatureRequestDTO candidatureRequestDTO = CandidatureRequestDTO.builder()
                .status(status.OPEN)
                .candidate(candidate)
                .dateCreation(new Date())
                .offreEmploi(offreEmploi)
                .noteEntretien(0)
                .build();

        Candidature candidature = candidatureMapper.dtoToEntity(candidatureRequestDTO);

        candidaturesCandidate.add(candidature);
        candidate.setCandidatures(candidaturesCandidate);
        candidaturesOffreEmploi.add(candidature);
        offreEmploi.setCandidatures(candidaturesOffreEmploi);


        CandidateResponseDTO candidateResponseDTO = candidateMapper.entityToDto(candidate);

        return candidateResponseDTO;
    }
}
