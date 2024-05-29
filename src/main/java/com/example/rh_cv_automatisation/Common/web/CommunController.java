package com.example.rh_cv_automatisation.Common.web;


import com.example.rh_cv_automatisation.Common.dtos.request.PropositionEntretienRequestDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.EntretienResponseDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.PropositionEntretienResponseDTO;
import com.example.rh_cv_automatisation.Common.services.CommunService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


@RestController
public class CommunController {
    private CommunService communService;

    public CommunController(CommunService communService) {
        this.communService = communService;
    }

    @GetMapping(path = "/sendNotification/{offreId}")
    public void sendNotificationOffre(@PathVariable Long offreId){
         communService.sendNotificationOffre(offreId);
    }

    @Scheduled(fixedRate = 60000)
    public void sendNotificationEntretien(){
        communService.sendNotificationEntretien();
    }

    @PostMapping(path = "/proposerEntretien")
    public PropositionEntretienResponseDTO proposerEntretien(@RequestBody PropositionEntretienRequestDTO propositionEntretienRequestDTO){
        return communService.proposerEntretien(propositionEntretienRequestDTO);
    }

    @PostMapping(path = "/choisirEntretien/{entretienId}")
    public EntretienResponseDTO choisirEntretien(@PathVariable Long entretienId){
        return communService.choisirEntretien(entretienId);
    }

}