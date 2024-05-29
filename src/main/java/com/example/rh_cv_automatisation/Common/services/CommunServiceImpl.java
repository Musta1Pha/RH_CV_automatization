package com.example.rh_cv_automatisation.Common.services;

import com.example.rh_cv_automatisation.Common.dtos.request.NotificationRequestDTO;
import com.example.rh_cv_automatisation.Common.dtos.request.PropositionEntretienRequestDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.EntretienResponseDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.NotificationResponseDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.PropositionEntretienResponseDTO;
import com.example.rh_cv_automatisation.Common.entities.Entretien;
import com.example.rh_cv_automatisation.Common.entities.PropositionEntretien;
import com.example.rh_cv_automatisation.Common.mappers.EntretienMapper;
import com.example.rh_cv_automatisation.Common.mappers.NotificationMapper;
import com.example.rh_cv_automatisation.Common.mappers.PropositionEntretienMapper;
import com.example.rh_cv_automatisation.Common.repositories.EntretienRepository;
import com.example.rh_cv_automatisation.Common.repositories.NotificationRepository;
import com.example.rh_cv_automatisation.Common.repositories.PropositionEntretienRepository;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.candidateManagement.mappers.CandidateMapper;
import com.example.rh_cv_automatisation.candidateManagement.repositories.CandidateRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.CandidatureRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.JobOfferRepository;
import com.example.rh_cv_automatisation.recruiterManagement.mappers.RecruteurMapper;
import com.example.rh_cv_automatisation.recruiterManagement.repositories.RecruteurRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CommunServiceImpl implements CommunService {
    private EntretienRepository entretienRepository;
    private NotificationRepository notificationRepository;
    private CandidatureRepository candidatureRepository;
    private JobOfferRepository jobOfferRepository;
    private CandidateRepository candidateRepository;
    private NotificationMapper notificationMapper;
    private CandidateMapper candidateMapper;
    private RecruteurMapper recruteurMapper;
    private PropositionEntretienMapper propositionEntretienMapper;
    private PropositionEntretienRepository propositionEntretienRepository;
    private EntretienMapper entretienMapper;
    private RecruteurRepository recruteurRepository;
    public CommunServiceImpl(EntretienRepository entretienRepository, NotificationRepository notificationRepository, CandidatureRepository candidatureRepository, JobOfferRepository jobOfferRepository, CandidateRepository candidateRepository, NotificationMapper notificationMapper, CandidateMapper candidateMapper, RecruteurMapper recruteurMapper, PropositionEntretienMapper propositionEntretienMapper, PropositionEntretienRepository propositionEntretienRepository, EntretienMapper entretienMapper, RecruteurRepository recruteurRepository) {
        this.entretienRepository = entretienRepository;
        this.notificationRepository = notificationRepository;
        this.candidatureRepository = candidatureRepository;
        this.jobOfferRepository = jobOfferRepository;
        this.candidateRepository = candidateRepository;
        this.notificationMapper = notificationMapper;
        this.candidateMapper = candidateMapper;
        this.recruteurMapper = recruteurMapper;
        this.propositionEntretienMapper = propositionEntretienMapper;
        this.propositionEntretienRepository = propositionEntretienRepository;
        this.entretienMapper = entretienMapper;
        this.recruteurRepository = recruteurRepository;
    }

    @Override
    public void sendNotificationOffre(Long offreId) {
        OffreEmploi offreEmploi = jobOfferRepository.findById(offreId).orElse(null);
        List<NotificationResponseDTO> notificationResponseDTOS = new ArrayList<>();

        for(Candidate candidate: candidateRepository.findAll()){
            NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO();
            notificationRequestDTO.setDate_notification(new Date());
            notificationRequestDTO.setMessage("Offre d'emploi "+offreEmploi.getFormation()+"a été ajouté avec succès");
            notificationRequestDTO.setUtilisateur(candidate);

            notificationRepository.save(notificationMapper.dtoToEntity(notificationRequestDTO));
        }
    }

    @Override
    public PropositionEntretienResponseDTO proposerEntretien(PropositionEntretienRequestDTO propositionEntretienRequestDTO){
        PropositionEntretien proposition = new PropositionEntretien();
        proposition.setCandidat(candidateRepository.findById(propositionEntretienRequestDTO.getCandidateId()).get());
        proposition.setRecruteur(recruteurRepository.findById(propositionEntretienRequestDTO.getRecruteurId()).get());
        proposition.setDateHeure(propositionEntretienRequestDTO.getDateHeure());

        propositionEntretienRepository.save(proposition);

        return propositionEntretienMapper.entityToDto(proposition);
    }

    @Override
    public EntretienResponseDTO choisirEntretien(Long entretienId){
        PropositionEntretien propositionEntretien = propositionEntretienRepository.findById(entretienId)
                .orElseThrow(() -> new RuntimeException("Proposition non trouvée"));
        if(propositionEntretien.isChoisiParCandidat()){
            throw new RuntimeException("Cette proposition a déjà été choisie");
        }

        Entretien entretien = new Entretien();
        entretien.setCandidat(propositionEntretien.getCandidat());
        entretien.setRecruteur(propositionEntretien.getRecruteur());
        entretien.setDateHeure(propositionEntretien.getDateHeure());
        propositionEntretien.setChoisiParCandidat(true);

        entretienRepository.save(entretien);

        return entretienMapper.entityToDto(entretien);
    }

    @Override
    public void sendNotificationEntretien() {

    }


}
