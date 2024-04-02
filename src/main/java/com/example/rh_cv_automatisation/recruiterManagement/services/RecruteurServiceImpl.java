package com.example.rh_cv_automatisation.recruiterManagement.services;

import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.OfferEmploiRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.RequiredSkillsRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.RequiredSkillsResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.RequiredSkills;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.JobOfferMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.RequiredSkillsMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.JobOfferRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.RequiredSkillsRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecruteurServiceImpl implements RecruteurService{
    private JobOfferMapper jobOfferMapper;
    private RequiredSkillsMapper requiredSkillsMapper;
    private JobOfferRepository jobOfferRepository;
    private RequiredSkillsRepository requiredSkillsRepository;

    public RecruteurServiceImpl(JobOfferMapper jobOfferMapper, RequiredSkillsMapper requiredSkillsMapper, JobOfferRepository jobOfferRepository, RequiredSkillsRepository requiredSkillsRepository) {
        this.jobOfferMapper = jobOfferMapper;
        this.requiredSkillsMapper = requiredSkillsMapper;
        this.jobOfferRepository = jobOfferRepository;
        this.requiredSkillsRepository = requiredSkillsRepository;
    }
    @Override
    public OfferEmploiResponseDTO addOffer(OfferEmploiRequestDTO offerEmploiRequestDTO,List<Long> requiredSkillsIds) {
        OffreEmploi offreEmploi = jobOfferMapper.dtoToEntity(offerEmploiRequestDTO);
        List<RequiredSkills> requiredSkills = requiredSkillsRepository.findAllById(requiredSkillsIds);

        offreEmploi.setRequiredSkills(requiredSkills);
        jobOfferRepository.save(offreEmploi);
        OfferEmploiResponseDTO offerEmploiResponseDTO = jobOfferMapper.entityToDto(offreEmploi);

        return offerEmploiResponseDTO;
    }

    @Override
    public RequiredSkillsResponseDTO addSkill(RequiredSkillsRequestDTO requiredSkillsRequestDTO) {
        RequiredSkills requiredSkills = requiredSkillsMapper.dtoToEntity(requiredSkillsRequestDTO);
        RequiredSkillsResponseDTO requiredSkillsResponseDTO = requiredSkillsMapper.entityToDto(requiredSkills);
        return requiredSkillsResponseDTO;
    }
}
