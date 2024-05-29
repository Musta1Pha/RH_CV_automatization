package com.example.rh_cv_automatisation.candidateManagement.services;

import com.example.rh_cv_automatisation.Common.dtos.request.EntretienRequestDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.EntretienResponseDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.HoraireDisponibleResponseDTO;
import com.example.rh_cv_automatisation.Common.entities.Entretien;
import com.example.rh_cv_automatisation.Common.enums.Resultat;
import com.example.rh_cv_automatisation.Common.mappers.EntretienMapper;
import com.example.rh_cv_automatisation.Common.repositories.EntretienRepository;
import com.example.rh_cv_automatisation.Common.services.CommunService;
import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.candidateManagement.mappers.CandidateMapper;
import com.example.rh_cv_automatisation.candidateManagement.repositories.CandidateRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.CandidatureRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import com.example.rh_cv_automatisation.jobOfferManagement.enums.status;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.CandidatureMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.JobOfferMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.CandidatureRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.JobOfferRepository;
import com.example.rh_cv_automatisation.recruiterManagement.services.RecruteurService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class CandidateServiceImpl implements CandidateService{
    private JobOfferRepository jobOfferRepository;
    private CandidateRepository candidateRepository;
    private CandidatureRepository candidatureRepository;
    private CandidatureMapper candidatureMapper;
    private CandidateMapper candidateMapper;
    private JobOfferMapper jobOfferMapper;
    private JavaMailSender javaMailSender;
    private EntretienMapper entretienMapper;
    private EntretienRepository entretienRepository;
    private RecruteurService recruteurService;
    private CommunService communService;

    public CandidateServiceImpl(JobOfferRepository jobOfferRepository, CandidateRepository candidateRepository, CandidatureRepository candidatureRepository, CandidatureMapper candidatureMapper, CandidateMapper candidateMapper, JobOfferMapper jobOfferMapper, JavaMailSender javaMailSender, EntretienMapper entretienMapper, EntretienRepository entretienRepository, RecruteurService recruteurService, CommunService communService) {
        this.jobOfferRepository = jobOfferRepository;
        this.candidateRepository = candidateRepository;
        this.candidatureRepository = candidatureRepository;
        this.candidatureMapper = candidatureMapper;
        this.candidateMapper = candidateMapper;
        this.jobOfferMapper = jobOfferMapper;
        this.javaMailSender = javaMailSender;
        this.entretienMapper = entretienMapper;
        this.entretienRepository = entretienRepository;
        this.recruteurService = recruteurService;
        this.communService = communService;
    }
    @Override
    public CandidateResponseDTO apply(Long candidateId, Long offerId) {
        Candidate candidate = candidateRepository.findById(candidateId).get();
        OffreEmploi offreEmploi = jobOfferRepository.findById(offerId).get();

        List<Candidature> candidatures = candidatureRepository.findAllByCandidate(candidate);

        Candidature candidatureTest = candidatureRepository.chercherCandidadureExiste(candidate, offreEmploi);
        CandidateResponseDTO candidateResponseDTO = null;

        if (candidatureTest == null) {
            CandidatureRequestDTO candidatureRequestDTO = CandidatureRequestDTO.builder()
                    .status(status.OPEN)
                    .dateCreation(new Date())
                    .build();

            Candidature candidature = candidatureMapper.dtoToEntity(candidatureRequestDTO);
            candidature.setCandidate(candidate);
            candidature.setOffreEmploi(offreEmploi);

            candidatureRepository.save(candidature);

            candidatures.add(candidature);

            offreEmploi.setCandidatures(candidatures);
            candidate.setCandidatures(candidatures);

            jobOfferRepository.save(offreEmploi);
            candidateRepository.save(candidate);

            List<CandidatureResponseDTO> candidatureResponseDTOs = new ArrayList<>();
            for (Candidature candidature1 : candidatures) {
                CandidatureResponseDTO candidatureResponseDTO = candidatureMapper.entityToDto(candidature1);
                candidatureResponseDTOs.add(candidatureResponseDTO);
            }

            candidateResponseDTO = candidateMapper.entityToDto(candidate);
            candidateResponseDTO.setCandidatures(candidatureResponseDTOs);

            communService.sendNotificationOffre(offerId);

        }
        return candidateResponseDTO;
    }

    @Override
    public CandidateResponseDTO createAccount(CandidateRequestDTO candidateRequestDTO) {
        String verificationToken = UUID.randomUUID().toString();

        Candidate candidate = new Candidate();
        candidate.setNom(candidateRequestDTO.getNom());
        candidate.setEmail(candidateRequestDTO.getEmail());
        candidate.setPrenom(candidateRequestDTO.getPrenom());
        candidate.setPassword(candidateRequestDTO.getPassword());
        candidate.setCv(candidateRequestDTO.getCv());
        candidate.setVerified(false);
        candidate.setVerificationToken(verificationToken);

        candidateRepository.save(candidate);

        sendVerificationEmail(candidate);

        return candidateMapper.entityToDto(candidate);
    }

    @Override
    public void sendVerificationEmail(Candidate candidate) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(candidate.getEmail());
        mailMessage.setSubject("Verify Your Email Address");
        mailMessage.setText("Please click on the following link to verify your email address: "
                + "http://localhost:8081/verify-email?token=" + candidate.getVerificationToken());

        javaMailSender.send(mailMessage);
    }
    @Override
    public CandidateResponseDTO connect(CandidateRequestDTO candidateRequestDTO) {
        return null;
    }
    @Override
    public boolean verifyEmail(String token) {
        Candidate user = candidateRepository.findByVerificationToken(token);
        if (user != null) {
            user.setVerified(true);
            candidateRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public CandidateResponseDTO updateProfil(Long id,CandidateRequestDTO candidateRequestDTO) {
        Candidate candidate = candidateRepository.findById(id).get();
        if (candidateRequestDTO.getNom() != null) candidate.setNom(candidateRequestDTO.getNom());
        if (candidateRequestDTO.getPrenom() != null) candidate.setPrenom(candidateRequestDTO.getPrenom());
        if (candidateRequestDTO.getCv() != null) candidate.setCv(candidateRequestDTO.getCv());

        candidateRepository.save(candidate);

        return candidateMapper.entityToDto(candidate);
    }

    @Override
    public List<OfferEmploiResponseDTO> getOffres() {
        return jobOfferMapper.entityToDto(jobOfferRepository.findAll());
    }

    @Override
    public OfferEmploiResponseDTO getOffre(Long offreId) {
        return jobOfferMapper.entityToDto(jobOfferRepository.findById(offreId).get());
    }

}
