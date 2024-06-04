package com.example.rh_cv_automatisation.candidateManagement.services;

import com.example.rh_cv_automatisation.Common.dtos.response.EntretienResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CandidateService {
    CandidateResponseDTO createAccount(CandidateRequestDTO candidateRequestDTO, MultipartFile file) throws IOException;
    //CandidateResponseDTO apply(Long candidateId , Long offerId);
    //CandidateResponseDTO updateProfil(Long id,CandidateRequestDTO candidateRequestDTO);
    List<OfferEmploiResponseDTO> getOffres();
    OfferEmploiResponseDTO getOffre(Long offreId);
    boolean verifyEmail(String token);
    void sendVerificationEmail(Candidate candidate);

    //TODO : IMPLEMENT THE METHOD
    CandidateResponseDTO connect(CandidateRequestDTO candidateRequestDTO);
}
