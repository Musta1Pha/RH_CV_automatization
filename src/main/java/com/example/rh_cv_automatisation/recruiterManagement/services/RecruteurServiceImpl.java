package com.example.rh_cv_automatisation.recruiterManagement.services;

import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.OfferEmploiRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.RequiredSkillsRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.RequiredSkillsResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.RequiredSkills;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.CandidatureMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.JobOfferMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.RequiredSkillsMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.CandidatureRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.JobOfferRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.RequiredSkillsRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class RecruteurServiceImpl implements RecruteurService{
    private JobOfferMapper jobOfferMapper;
    private RequiredSkillsMapper requiredSkillsMapper;
    private JobOfferRepository jobOfferRepository;
    private RequiredSkillsRepository requiredSkillsRepository;
    private CandidatureRepository candidatureRepository;
    private CandidatureMapper candidatureMapper;

    public RecruteurServiceImpl(JobOfferMapper jobOfferMapper, RequiredSkillsMapper requiredSkillsMapper, JobOfferRepository jobOfferRepository, RequiredSkillsRepository requiredSkillsRepository, CandidatureRepository candidatureRepository, CandidatureMapper candidatureMapper) {
        this.jobOfferMapper = jobOfferMapper;
        this.requiredSkillsMapper = requiredSkillsMapper;
        this.jobOfferRepository = jobOfferRepository;
        this.requiredSkillsRepository = requiredSkillsRepository;
        this.candidatureRepository = candidatureRepository;
        this.candidatureMapper = candidatureMapper;
    }
    @Override
    public OfferEmploiResponseDTO addOffer(OfferEmploiRequestDTO offerEmploiRequestDTO,List<Long> requiredSkillsIds) {
        OffreEmploi offreEmploi = jobOfferMapper.dtoToEntity(offerEmploiRequestDTO);
        List<RequiredSkills> requiredSkills = requiredSkillsRepository.findAllById(requiredSkillsIds);

        offreEmploi.setRequiredSkills(requiredSkills);
        jobOfferRepository.save(offreEmploi);
        OfferEmploiResponseDTO offerEmploiResponseDTO = jobOfferMapper.entityToDto(offreEmploi);

        List<RequiredSkillsResponseDTO> requiredSkillsResponseDTO = requiredSkillsMapper.entityToDto(requiredSkills);
        offerEmploiResponseDTO.setRequiredSkills(requiredSkillsResponseDTO);

        return offerEmploiResponseDTO;
    }

    @Override
    public RequiredSkillsResponseDTO addSkill(RequiredSkillsRequestDTO requiredSkillsRequestDTO) {
        RequiredSkills requiredSkills = requiredSkillsMapper.dtoToEntity(requiredSkillsRequestDTO);
        requiredSkillsRepository.save(requiredSkills);

        return requiredSkillsMapper.entityToDto(requiredSkills);
    }

    @Override
    public List<CandidatureResponseDTO> consulterCandidature(Long offerId) {
       OffreEmploi offreEmploi = jobOfferRepository.findById(offerId).get();
       List<Candidature> candidatures = candidatureRepository.findAllByOffreEmploi(offreEmploi);
       List<CandidatureResponseDTO> candidatureResponseDTOS = new ArrayList<>();
       for(Candidature candidature : candidatures){
           OfferEmploiResponseDTO offerEmploiResponseDTO = jobOfferMapper.entityToDto(candidature.getOffreEmploi());
           CandidatureResponseDTO candidatureResponseDTO = candidatureMapper.entityToDto(candidature);

           candidatureResponseDTO.setOffreEmploi(offerEmploiResponseDTO);
           candidatureResponseDTOS.add(candidatureResponseDTO);
       }

        candidatureResponseDTOS.sort(Comparator.comparing(CandidatureResponseDTO::getFinalPercentage).reversed());

        return candidatureResponseDTOS;
    }
}
