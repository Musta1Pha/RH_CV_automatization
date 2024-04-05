package com.example.rh_cv_automatisation.candidateManagement.web;

import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.services.CandidateService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CandidateController {
    private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping(path = "/PostulerOffre/{CandidateId}")
    public CandidateResponseDTO postulerOffre(@PathVariable Long CandidateId , @RequestBody Map<String, Long> requestBody) {
        Long OfferId = requestBody.get("OfferId");
        System.out.println(OfferId);
        return candidateService.postulerOffre(CandidateId, OfferId);
    }

    @PostMapping(path = "CreationCompte")
    public CandidateResponseDTO CreationCompte(@RequestBody CandidateRequestDTO candidateRequestDTO){
        return candidateService.CreationCompte(candidateRequestDTO);
    }

}
