package com.example.rh_cv_automatisation.candidateManagement.web;

import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.services.CandidateService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {
    private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping(path = "/PostulerOffre/{CandidateId}")
    public CandidateResponseDTO postulerOffre(@PathVariable Long CandidateId , @RequestBody Long OfferId) {
        return candidateService.postulerOffre(CandidateId,OfferId);
    }

}
