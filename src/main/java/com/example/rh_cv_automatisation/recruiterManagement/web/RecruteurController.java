package com.example.rh_cv_automatisation.recruiterManagement.web;

import com.example.rh_cv_automatisation.Common.dtos.response.HoraireDisponibleResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.OfferEmploiCreationRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.RequiredSkillsRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.RequiredSkillsResponseDTO;
import com.example.rh_cv_automatisation.recruiterManagement.services.RecruteurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        return recruteurService.addOffer(offerEmploiRequestDTO.getOfferEmploiRequestDTO(), requiredSkillsIds);
    }

    @PostMapping(path = "/addSkill")
    public RequiredSkillsResponseDTO addSkill(@RequestBody RequiredSkillsRequestDTO requiredSkillsRequestDTO) {
        return recruteurService.addSkill(requiredSkillsRequestDTO);
    }

    @GetMapping(path = "/consulterCandidature/{id}")
    public List<CandidatureResponseDTO> consulterCandidature(@PathVariable Long id) {
        return recruteurService.consulterCandidature(id);
    }

   /* @GetMapping(path = "/DownloadCV/{id}")
    public ResponseEntity<byte[]> DownloadCv(@PathVariable Long id) throws IOException {
        return recruteurService.DownloadCv(id);
    }*/

   /* @PostMapping(path = "/AjoutHoraire/{id}")
    public List<HoraireDisponibleResponseDTO> AjoutHoraire(@PathVariable Long id, @RequestBody HoraireDisponibleRequestDTO horaireDisponibleRequestDTO) {
        return recruteurService.AjoutHoraire(id, horaireDisponibleRequestDTO);
    }

    @DeleteMapping(path = "/SupprimerHoraire/{id}")
    public List<HoraireDisponibleResponseDTO> SupprimerHoraire(@PathVariable Long id) {
        return recruteurService.SupprimerHoraire(id);
    }
*/
    @PutMapping(path = "/updateStatusCandidature/{id}")
    public CandidatureResponseDTO updateStatusCandidature(@PathVariable Long id,@RequestBody String s) {
        return recruteurService.updateStatusCandidature(id,s);
    }
}