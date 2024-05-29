package com.example.rh_cv_automatisation.Common.services;

import com.example.rh_cv_automatisation.Common.dtos.request.PropositionEntretienRequestDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.EntretienResponseDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.PropositionEntretienResponseDTO;

public interface CommunService {
    void sendNotificationOffre(Long offreId);

    PropositionEntretienResponseDTO proposerEntretien(PropositionEntretienRequestDTO propositionEntretienRequestDTO);

    EntretienResponseDTO choisirEntretien(Long entretienId);

    void sendNotificationEntretien();
}
