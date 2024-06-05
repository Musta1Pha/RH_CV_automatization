package com.example.rh_cv_automatisation.candidateManagement.services;

import com.example.rh_cv_automatisation.Common.mappers.EntretienMapper;
import com.example.rh_cv_automatisation.Common.repositories.EntretienRepository;
import com.example.rh_cv_automatisation.Common.services.CommunService;
import com.example.rh_cv_automatisation.ai_management.services.Llama2RestService;
import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.candidateManagement.entities.CvData;
import com.example.rh_cv_automatisation.candidateManagement.mappers.CandidateMapper;
import com.example.rh_cv_automatisation.candidateManagement.mappers.CvDataMapper;
import com.example.rh_cv_automatisation.candidateManagement.repositories.CandidateRepository;
import com.example.rh_cv_automatisation.candidateManagement.repositories.CvDataRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.CandidatureMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.JobOfferMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.CandidatureRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.JobOfferRepository;
import com.example.rh_cv_automatisation.recruiterManagement.services.RecruteurService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private Llama2RestService llama2RestService;
    private CvDataMapper cvDataMapper;
    private CvDataRepository cvDataRepository;
    private StorageService service;


    public CandidateServiceImpl(JobOfferRepository jobOfferRepository, CandidateRepository candidateRepository, CandidatureRepository candidatureRepository, CandidatureMapper candidatureMapper, CandidateMapper candidateMapper, JobOfferMapper jobOfferMapper, JavaMailSender javaMailSender, EntretienMapper entretienMapper, EntretienRepository entretienRepository, RecruteurService recruteurService, CommunService communService, Llama2RestService llama2RestService, CvDataMapper cvDataMapper, CvDataRepository cvDataRepository, StorageService service) {
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
        this.llama2RestService = llama2RestService;
        this.cvDataMapper = cvDataMapper;
        this.cvDataRepository = cvDataRepository;
        this.service = service;
    }
  /*  @Override
    public CandidateResponseDTO apply(Long candidateId, Long offerId) {
        Candidate candidate = candidateRepository.findById(candidateId).get();
        OffreEmploi offreEmploi = jobOfferRepository.findById(offerId).get();

        List<Candidature> candidatures = candidatureRepository.findAllByCandidate(candidate);

        Candidature candidatureTest = candidatureRepository.chercherCandidadureExiste(candidate, offreEmploi);
        CandidateResponseDTO candidateResponseDTO = null;

        CandidateRequestDTO candidateRequestDTO = new CandidateRequestDTO();
        candidateRequestDTO.setCv((MultipartFile) candidate.getCvPath());

        List<RequiredSkillsRequestDTO> keywords = new ArrayList<>();
        for(RequiredSkills skill : offreEmploi.getRequiredSkills()){
            RequiredSkillsRequestDTO requiredSkillsRequestDTO = new RequiredSkillsRequestDTO(skill.getSkill());
            keywords.add(requiredSkillsRequestDTO);
        }

        if (candidatureTest == null) {
            CandidatureRequestDTO candidatureRequestDTO = CandidatureRequestDTO.builder()
                    .status(status.OPEN)
                    .dateCreation(new Date())
                    .finalPercentage(llama2RestService.chatWithFile(offreEmploi.getExperience(), offreEmploi.getFormation(), keywords, candidateRequestDTO.getCv()))
                    .noteEntretien(0)
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
    }*/

    @Override
    public String createAccount(CandidateRequestDTO candidateRequestDTO, MultipartFile file) throws IOException {
        String verificationToken = UUID.randomUUID().toString();

        Candidate candidate = new Candidate();
        candidate.setNom(candidateRequestDTO.getNom());
        candidate.setEmail(candidateRequestDTO.getEmail());
        candidate.setPrenom(candidateRequestDTO.getPrenom());
        candidate.setPassword(candidateRequestDTO.getPassword());
        candidate.setVerified(false);
        candidate.setVerificationToken(verificationToken);

        CvData cvData = service.uploadFile(file);

        candidate.setCv(cvData);

        candidateRepository.save(candidate);
        sendVerificationEmail(candidate);
        return "You have been successfully registred";
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
    public CandidateResponseDTO connect(CandidateRequestDTO candidateRequestDTO) {
        return null;
    }

    @Override
    public String updateProfil(Long id,CandidateRequestDTO candidateRequestDTO,MultipartFile file) throws IOException {
        Candidate candidate = candidateRepository.findById(id).get();
        if (candidateRequestDTO.getNom() != null) candidate.setNom(candidateRequestDTO.getNom());
        if (candidateRequestDTO.getPrenom() != null) candidate.setPrenom(candidateRequestDTO.getPrenom());
        if (file != null) candidate.setCv(service.uploadFile(file));

        candidateRepository.save(candidate);

        return "Profil updated successfully";
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
