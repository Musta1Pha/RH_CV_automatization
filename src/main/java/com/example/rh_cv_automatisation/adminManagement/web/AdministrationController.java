package com.example.rh_cv_automatisation.adminManagement.web;

import com.example.rh_cv_automatisation.adminManagement.repositories.AdministrateurRepository;
import com.example.rh_cv_automatisation.adminManagement.services.AdministrationService;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.RecruteurCreationRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.RecruteurRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.response.RecruteurResponseDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.response.Role_RecruteurResponseDTO;
import com.example.rh_cv_automatisation.recruiterManagement.enums.Status;
import com.example.rh_cv_automatisation.recruiterManagement.repositories.RecruteurRepository;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.Role_RecruteurRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.entities.Recruteur;
import com.example.rh_cv_automatisation.recruiterManagement.mappers.RecruteurMapper;
import com.example.rh_cv_automatisation.recruiterManagement.mappers.roleRecruteurMapper;
import com.example.rh_cv_automatisation.recruiterManagement.repositories.RoleRecruteurRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AdministrationController {
    private AdministrateurRepository administrateurRepository;
    private RecruteurRepository recruteurRepository;
    private RoleRecruteurRepository roleRecruteurRepository;
    private AdministrationService administrationService;
    private RecruteurMapper recruteurMapper;
    private roleRecruteurMapper roleRecruteurMapper;

    public AdministrationController(AdministrateurRepository administrateurRepository, RecruteurRepository recruteurRepository, RoleRecruteurRepository roleRecruteurRepository, AdministrationService administrationService, RecruteurMapper recruteurMapper, com.example.rh_cv_automatisation.recruiterManagement.mappers.roleRecruteurMapper roleRecruteurMapper) {
        this.administrateurRepository = administrateurRepository;
        this.recruteurRepository = recruteurRepository;
        this.roleRecruteurRepository = roleRecruteurRepository;
        this.administrationService = administrationService;
        this.recruteurMapper = recruteurMapper;
        this.roleRecruteurMapper = roleRecruteurMapper;
    }

    @PostMapping(path = "/addRecruiter")
    public RecruteurResponseDTO addRecruiter(@RequestBody RecruteurCreationRequestDTO requestDTO) {
        List<Long> rolesIds = requestDTO.getRoles().get("rolesIds");
        return administrationService.addRecruteur(requestDTO.getRecruteurRequestDTO(), rolesIds);
    }

    @PostMapping(path = "/addRole")
    public Role_RecruteurResponseDTO addRole(@RequestBody Role_RecruteurRequestDTO roleRecruteurDTO){
        return administrationService.addRole(roleRecruteurDTO);
    }

    @PostMapping(path = "/assignmentRoles/{id}")
    public RecruteurResponseDTO assignmentRoles(@PathVariable Long id, @RequestBody Map<String, List<Long>> roles) {
        List<Long> rolesIds = roles.get("rolesIds");
        return administrationService.assignmentRoles(id,rolesIds);
    }

    @DeleteMapping(path = "/removeRoles/{id}")
    public RecruteurResponseDTO removeRoles(@PathVariable Long id, @RequestBody Map<String, List<Long>> requestBody) {
        List<Long> rolesIds = requestBody.get("rolesIds");
        return administrationService.removeRoles(id, rolesIds);
    }

    @DeleteMapping(path = "/deleteRole/{id}")
    public List<Role_RecruteurResponseDTO> deleteRole(@PathVariable Long id){
        return administrationService.deleteRole(id);
    }

    @PostMapping(path ="/suspendRecruiter/{id}")
    public RecruteurResponseDTO blockRecruiter(@PathVariable Long id){
        Recruteur recruteur = recruteurRepository.findById(id).orElse(null);
        recruteur.setStatus(Status.ACCÈS_BLOQUÉ);
        List<Role_RecruteurResponseDTO> roleRecruteurs = recruteur.getRoles().stream()
                .map(roleRecruteurMapper::entityToDto)
                .collect(Collectors.toList());

        RecruteurResponseDTO recruteurResponseDTO = recruteurMapper.entityToDto(recruteur);
        recruteurResponseDTO.setRoles(roleRecruteurs);

        return recruteurResponseDTO;
    }

    @PostMapping(path ="/unblockRecruiter/{id}")
    public RecruteurResponseDTO unblockRecruiter(@PathVariable Long id){
        Recruteur recruteur = recruteurRepository.findById(id).orElse(null);
        recruteur.setStatus(Status.ACCÈS_PERMIS);
        List<Role_RecruteurResponseDTO> roleRecruteurs = recruteur.getRoles().stream()
                .map(roleRecruteurMapper::entityToDto)
                .collect(Collectors.toList());

        RecruteurResponseDTO recruteurResponseDTO = recruteurMapper.entityToDto(recruteur);
        recruteurResponseDTO.setRoles(roleRecruteurs);

        return recruteurResponseDTO;
    }

    @GetMapping(path = "/getAllRecruiters")
    public List<RecruteurResponseDTO> getallRecruiters(){
        List<RecruteurResponseDTO> recruteurResponseDTOS = new ArrayList<>();
        for(Recruteur recruteur : recruteurRepository.findAll()){
            RecruteurResponseDTO recruteurResponseDTO = recruteurMapper.entityToDto(recruteur);
            List<Role_RecruteurResponseDTO> roleRecruteurResponseDTOS = recruteur.getRoles()
                    .stream()
                    .map(roleRecruteurMapper::entityToDto)
                    .collect(Collectors.toList());
            recruteurResponseDTO.setRoles(roleRecruteurResponseDTOS);

            recruteurResponseDTOS.add(recruteurResponseDTO);
        }

        return recruteurResponseDTOS;
    }

    @GetMapping(path = "/getRecruiter/{id}")
    public RecruteurResponseDTO getRecruiter(@PathVariable Long id){
        Recruteur recruteur = recruteurRepository.findById(id).orElse(null);
        List<Role_RecruteurResponseDTO> roleRecruteurs = recruteur.getRoles().stream()
                .map(roleRecruteurMapper::entityToDto)
                .collect(Collectors.toList());

        RecruteurResponseDTO recruteurResponseDTO = recruteurMapper.entityToDto(recruteur);
        recruteurResponseDTO.setRoles(roleRecruteurs);
        return recruteurResponseDTO;
    }
}
