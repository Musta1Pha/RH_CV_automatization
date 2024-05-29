package com.example.rh_cv_automatisation.recruiterManagement.services;

import com.example.rh_cv_automatisation.Common.services.CommunService;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.candidateManagement.repositories.CandidateRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.OfferEmploiRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.RequiredSkillsRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.RequiredSkillsResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.RequiredSkills;
import com.example.rh_cv_automatisation.jobOfferManagement.enums.status;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.CandidatureMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.JobOfferMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.RequiredSkillsMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.CandidatureRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.JobOfferRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.RequiredSkillsRepository;
import com.example.rh_cv_automatisation.recruiterManagement.mappers.RecruteurMapper;
import com.example.rh_cv_automatisation.recruiterManagement.repositories.RecruteurRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private CandidateRepository candidateRepository;
    private RecruteurRepository recruteurRepository;
    private RecruteurMapper recruteurMapper;
    private CommunService communService;

    public RecruteurServiceImpl(JobOfferMapper jobOfferMapper, RequiredSkillsMapper requiredSkillsMapper, JobOfferRepository jobOfferRepository, RequiredSkillsRepository requiredSkillsRepository, CandidatureRepository candidatureRepository, CandidatureMapper candidatureMapper, CandidateRepository candidateRepository, RecruteurRepository recruteurRepository, RecruteurMapper recruteurMapper, CommunService communService) {
        this.jobOfferMapper = jobOfferMapper;
        this.requiredSkillsMapper = requiredSkillsMapper;
        this.jobOfferRepository = jobOfferRepository;
        this.requiredSkillsRepository = requiredSkillsRepository;
        this.candidatureRepository = candidatureRepository;
        this.candidatureMapper = candidatureMapper;
        this.candidateRepository = candidateRepository;
        this.recruteurRepository = recruteurRepository;
        this.recruteurMapper = recruteurMapper;
        this.communService = communService;
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

        communService.sendNotificationOffre(offreEmploi.getID_Offer());

        return offerEmploiResponseDTO;
    }

    @Override
    public RequiredSkillsResponseDTO addSkill(RequiredSkillsRequestDTO requiredSkillsRequestDTO) {
        RequiredSkills requiredSkills = requiredSkillsMapper.dtoToEntity(requiredSkillsRequestDTO);
        requiredSkillsRepository.save(requiredSkills);

        return requiredSkillsMapper.entityToDto(requiredSkills);
    }

    @Override
    public CandidatureResponseDTO updateStatusCandidature(Long id, String s) {
        Candidature candidature = candidatureRepository.findById(id).get();
        candidature.setStatus(status.valueOf(s));
        candidatureRepository.save(candidature);

        return candidatureMapper.entityToDto(candidature);
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

    @Override
    public ResponseEntity<byte[]> DownloadCv(Long id) throws IOException{
        Candidate candidate = candidateRepository.findById(id).get();

        Path localCVPath = Path.of(candidate.getCv().getAbsolutePath());

        // Check if the file exists locally
        if (!Files.exists(localCVPath)) {
            throw new IOException("The CV file does not exist locally.");
        }

        // Read the file into a byte array
        byte[] cvBytes = Files.readAllBytes(localCVPath);

        // Set the content type and disposition headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "cv.pdf");

        // Send the file as a byte array in the response
        return ResponseEntity.ok()
                .headers(headers)
                .body(cvBytes);
    }





}
