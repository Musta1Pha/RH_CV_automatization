package com.example.rh_cv_automatisation.recruiterManagement.web;

import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.OfferEmploiCreationRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.RequiredSkillsRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.RequiredSkillsResponseDTO;
import com.example.rh_cv_automatisation.recruiterManagement.services.RecruteurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecruteurController {
    private RecruteurService recruteurService;

    public RecruteurController(RecruteurService recruteurService) {
        this.recruteurService = recruteurService;
    }

    @PostMapping(path = "/addOffer")
    public OfferEmploiResponseDTO addOffer(@RequestBody OfferEmploiCreationRequestDTO offerEmploiRequestDTO) {
        List<Long> requiredSkillsIds = offerEmploiRequestDTO.getRequiredSkills().get("skillIds");
        return recruteurService.addOffer(offerEmploiRequestDTO.getOfferEmploiRequestDTO(),requiredSkillsIds);
    }

    @PostMapping(path = "/addSkill")
    public RequiredSkillsResponseDTO addSkill(@RequestBody RequiredSkillsRequestDTO requiredSkillsRequestDTO){
        return recruteurService.addSkill(requiredSkillsRequestDTO);
    }

    @GetMapping(path = "/consulterCandidature/{id}")
    public List<CandidatureResponseDTO> consulterCandidature(@PathVariable Long id){
        return recruteurService.consulterCandidature(id);
    }
}
