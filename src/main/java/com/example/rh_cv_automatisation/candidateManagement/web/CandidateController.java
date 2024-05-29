package com.example.rh_cv_automatisation.candidateManagement.web;

import com.example.rh_cv_automatisation.Common.dtos.response.EntretienResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.services.CandidateService;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CandidateController {
    private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping(path = "/apply/{candidateId}")
    public CandidateResponseDTO apply(@PathVariable Long candidateId , @RequestBody Map<String, Long> requestBody) {
        Long offerId = requestBody.get("offerId");
        return candidateService.apply(candidateId, offerId);
    }
    @GetMapping(path="/getOffres")
    public List<OfferEmploiResponseDTO> getOffres(){
        return candidateService.getOffres();
    }

    @GetMapping(path = "/getOffre/{offreId}")
    public OfferEmploiResponseDTO getOffre(@PathVariable Long offreId){
        return candidateService.getOffre(offreId);
    }

    @PostMapping(path = "/createAccount")
    public CandidateResponseDTO createAccount(@RequestBody CandidateRequestDTO candidateRequestDTO){
        return candidateService.createAccount(candidateRequestDTO);
    }

    @PutMapping(path = "/updateProfil/{id}")
    public CandidateResponseDTO updateProfil(@PathVariable Long id,@RequestBody CandidateRequestDTO candidateRequestDTO){
        return candidateService.updateProfil(id,candidateRequestDTO);
    }

  /*  @PostMapping(path = "/entretienHoraire/{candidateId}")
    public EntretienResponseDTO entretienHoraire(@PathVariable Long candidateId , @RequestBody Long id) {
        return candidateService.entretienHoraire(candidateId,id);
    }*/

   /* @PostMapping(path = "/cancelEntretien/{id}")
    public HoraireDisponible cancelEntretien(@PathVariable Long id){
        return candidateService.cancelEntretien(id);
    }*/

    @GetMapping("/verify-email")
    public void verifyEmail(@RequestParam("token") String token) {
        candidateService.verifyEmail(token);
    }

    @PostMapping(path = "/connect")
    public CandidateResponseDTO connect(@RequestBody CandidateRequestDTO candidateRequestDTO){
        return candidateService.connect(candidateRequestDTO);
    }
}
