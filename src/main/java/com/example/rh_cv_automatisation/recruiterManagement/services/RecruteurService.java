package com.example.rh_cv_automatisation.recruiterManagement.services;

import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.OfferEmploiRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.RequiredSkillsRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.RequiredSkillsResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;

import java.util.List;

public interface RecruteurService {
    OfferEmploiResponseDTO addOffer(OfferEmploiRequestDTO offerEmploiRequestDTO, List<Long> requiredSkillsIds);

    RequiredSkillsResponseDTO addSkill(RequiredSkillsRequestDTO requiredSkillsRequestDTO);

    List<CandidatureResponseDTO> consulterCandidature(Long offerId);
}
