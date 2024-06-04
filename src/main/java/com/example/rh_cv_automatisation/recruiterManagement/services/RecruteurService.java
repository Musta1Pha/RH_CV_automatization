package com.example.rh_cv_automatisation.recruiterManagement.services;

import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.OfferEmploiRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.RequiredSkillsRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.RequiredSkillsResponseDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.HoraireDisponibleResponseDTO;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface RecruteurService {
    OfferEmploiResponseDTO addOffer(OfferEmploiRequestDTO offerEmploiRequestDTO, List<Long> requiredSkillsIds);

    RequiredSkillsResponseDTO addSkill(RequiredSkillsRequestDTO requiredSkillsRequestDTO);

    CandidatureResponseDTO updateStatusCandidature(Long id, String s);

    List<CandidatureResponseDTO> consulterCandidature(Long offerId);

  //  ResponseEntity<byte[]> DownloadCv(Long id) throws IOException;
    // List<HoraireDisponibleResponseDTO> AjoutHoraire(Long id,HoraireDisponibleRequestDTO horaireDisponibleRequestDTO);
    //List<HoraireDisponibleResponseDTO> SupprimerHoraire(Long id);

}
