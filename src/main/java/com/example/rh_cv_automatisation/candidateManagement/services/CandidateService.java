package com.example.rh_cv_automatisation.candidateManagement.services;

import com.example.rh_cv_automatisation.Common.dtos.response.EntretienResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.candidateManagement.entities.CvData;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CandidateService {
    String createAccount(CandidateRequestDTO candidateRequestDTO, MultipartFile file) throws IOException;
    boolean verifyEmail(String token);
    void sendVerificationEmail(Candidate candidate);
    String updateProfil(Long id,CandidateRequestDTO candidateRequestDTO,MultipartFile file) throws IOException;
    MultipartFile apply(Long candidateId, Long offerId);




    List<OfferEmploiResponseDTO> getOffres();
    OfferEmploiResponseDTO getOffre(Long offreId);

    //TODO : IMPLEMENT THE METHOD
    CandidateResponseDTO connect(CandidateRequestDTO candidateRequestDTO);


}
