package com.example.rh_cv_automatisation.adminManagement.services;

import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.Role_RecruteurRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.response.RecruteurResponseDTO;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.response.Role_RecruteurResponseDTO;
import com.example.rh_cv_automatisation.recruiterManagement.entities.Role_Recruteur;
import com.example.rh_cv_automatisation.recruiterManagement.mappers.roleRecruteurMapper;
import com.example.rh_cv_automatisation.recruiterManagement.repositories.RecruteurRepository;
import com.example.rh_cv_automatisation.recruiterManagement.dtos.request.RecruteurRequestDTO;
import com.example.rh_cv_automatisation.recruiterManagement.entities.Recruteur;
import com.example.rh_cv_automatisation.recruiterManagement.mappers.RecruteurMapper;
import com.example.rh_cv_automatisation.recruiterManagement.repositories.RoleRecruteurRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class AdministrationServiceImpl implements AdministrationService {
    private RecruteurRepository recruteurRepository;
    private RoleRecruteurRepository roleRecruteurRepository;
    private RecruteurMapper recruteurMapper;
    private roleRecruteurMapper roleRecruteurMapper;


    public AdministrationServiceImpl(RecruteurRepository recruteurRepository, RoleRecruteurRepository roleRecruteurRepository, RecruteurMapper recruteurMapper, com.example.rh_cv_automatisation.recruiterManagement.mappers.roleRecruteurMapper roleRecruteurMapper) {
        this.recruteurRepository = recruteurRepository;
        this.roleRecruteurRepository = roleRecruteurRepository;
        this.recruteurMapper = recruteurMapper;
        this.roleRecruteurMapper = roleRecruteurMapper;
    }

    /**
     * Adds a new recruiter with the specified roles.
     *
     * @param recruteurRequestDTO The DTO containing information about the recruiter to be added.
     * @param rolesIds             The IDs of the roles to be assigned to the recruiter.
     * @return The DTO containing information about the added recruiter with assigned roles.
     */
    @Override
    public RecruteurResponseDTO addRecruteur(RecruteurRequestDTO recruteurRequestDTO,List<Long> rolesIds) {
        Recruteur recruteur = recruteurMapper.dtoToEntity(recruteurRequestDTO);
        List<Role_Recruteur> assignedRoles = new ArrayList<>();
        List<Long> notFoundRoles = new ArrayList<>();
        for (Long roleId : rolesIds) {
            try {
                Role_Recruteur roleRecruteur = roleRecruteurRepository.findById(roleId).get();
                assignedRoles.add(roleRecruteur);
            } catch (NoSuchElementException e) {
                notFoundRoles.add(roleId);
            }
            System.out.println("Roles not found: " + notFoundRoles);
        }
        recruteur.setRoles(assignedRoles);
        recruteurRepository.save(recruteur);

        RecruteurResponseDTO recruteurResponseDTO = recruteurMapper.entityToDto(recruteur);
        List<Role_RecruteurResponseDTO> roleRecruteurResponseDTOS = new ArrayList<>();
        for (Role_Recruteur roleRecruteur : assignedRoles) {
            Role_RecruteurResponseDTO roleRecruteurResponseDTO = roleRecruteurMapper.entityToDto(roleRecruteur);
            roleRecruteurResponseDTOS.add(roleRecruteurResponseDTO);
            recruteurResponseDTO.setRoles(roleRecruteurResponseDTOS);

        }
        return recruteurResponseDTO;
    }


    /**
     * Adds a new role using the information provided in the DTO.
     *
     * @param roleRecruteurDTO The DTO containing information about the role to be added.
     * @return The DTO containing information about the added role.
     */
    @Override
    public Role_RecruteurResponseDTO addRole(Role_RecruteurRequestDTO roleRecruteurDTO) {
        Role_Recruteur roleRecruteur = roleRecruteurMapper.dtoToEntity(roleRecruteurDTO);
        roleRecruteurRepository.save(roleRecruteur);

        return roleRecruteurMapper.entityToDto(roleRecruteur);
    }

    /**
     * Assigns roles to a recruiter identified by the provided ID.
     *
     * @param id    The ID of the recruiter.
     * @param roles The list of role IDs to be assigned to the recruiter.
     * @return The DTO containing information about the recruiter after role assignment.
     */
    @Override
    public RecruteurResponseDTO assignmentRoles(Long id, List<Long> roles) {
        Recruteur recruteur = recruteurRepository.findById(id).orElse(null);
        List<Role_Recruteur> assignedRoles = new ArrayList<>();
        List<Long> notFoundRoles = new ArrayList<>();
        for (Long roleId : roles) {
            try {
                Role_Recruteur roleRecruteur = roleRecruteurRepository.findById(roleId).get();
                if (!recruteur.getRoles().contains(roleRecruteur))
                    assignedRoles.add(roleRecruteur);
            } catch (NoSuchElementException e) {
                notFoundRoles.add(roleId);
            }
            System.out.println("Roles not found: " + notFoundRoles);
        }

        List<Role_Recruteur> ownedRoles = recruteur.getRoles();
        ownedRoles.addAll(assignedRoles);

        recruteur.setRoles(ownedRoles);
        recruteurRepository.save(recruteur);

        RecruteurResponseDTO recruteurResponseDTO = recruteurMapper.entityToDto(recruteur);
        List<Role_RecruteurResponseDTO> roleRecruteurResponseDTOS = new ArrayList<>();
        for (Role_Recruteur roleRecruteur : ownedRoles) {
            Role_RecruteurResponseDTO roleRecruteurResponseDTO = roleRecruteurMapper.entityToDto(roleRecruteur);
            roleRecruteurResponseDTOS.add(roleRecruteurResponseDTO);
            recruteurResponseDTO.setRoles(roleRecruteurResponseDTOS);

        }
        return recruteurResponseDTO;
    }

    /**
     * Removes roles from a recruiter identified by the provided ID.
     *
     * @param id The ID of the recruiter.
     * @param rolesId The list of role IDs to be removed from the recruiter.
     * @return The DTO containing information about the recruiter after roles removal.
     */
    @Override
    public RecruteurResponseDTO removeRoles(Long id, List<Long> rolesId) {
        Recruteur recruteur = recruteurRepository.findById(id).orElse(null);
        List<Role_Recruteur> assignedRoles = new ArrayList<>();
        List<Long> notFoundRoles = new ArrayList<>();

        for (Long roleId : rolesId){
            try {
                Role_Recruteur roleRecruteur = roleRecruteurRepository.findById(roleId).get();
                if(recruteur.getRoles().contains(roleRecruteur))
                    assignedRoles.add(roleRecruteur);
            } catch (NoSuchElementException e) {
                notFoundRoles.add(roleId);
            }
            System.out.println("Roles not found: " + notFoundRoles);
        }

        List<Role_Recruteur> roleRecruteurs = recruteur.getRoles();

        roleRecruteurs.removeAll(assignedRoles);
        recruteur.setRoles(roleRecruteurs);

        recruteurRepository.save(recruteur);

        RecruteurResponseDTO recruteurResponseDTO = recruteurMapper.entityToDto(recruteur);
        List<Role_RecruteurResponseDTO> roleRecruteurResponseDTOS = new ArrayList<>();
        for(Role_Recruteur roleRecruteur  : roleRecruteurs) {
            Role_RecruteurResponseDTO roleRecruteurResponseDTO = roleRecruteurMapper.entityToDto(roleRecruteur);
            roleRecruteurResponseDTOS.add(roleRecruteurResponseDTO);
            recruteurResponseDTO.setRoles(roleRecruteurResponseDTOS);

        }
        return recruteurResponseDTO;
    }

    /**
     * Deletes a role with the given ID.
     *
     * @param id The ID of the role to be deleted.
     * @return The list of DTOs containing information about all remaining roles.
     */
    @Override
    public List<Role_RecruteurResponseDTO> deleteRole(Long id) {
        Role_Recruteur roleRecruteur = roleRecruteurRepository.findById(id).orElse(null);
        List<Recruteur> recruteurs = recruteurRepository.findAll();

        for(Recruteur recruteur : recruteurs){
            List<Role_Recruteur> roles = recruteur.getRoles();
            roles.remove(roleRecruteur);
            recruteur.setRoles(roles);

            recruteurRepository.save(recruteur);
        }

        roleRecruteurRepository.deleteById(id);

        List<Role_RecruteurResponseDTO> roleRecruteurResponseDTOS = roleRecruteurRepository.findAll()
                .stream()
                .map(roleRecruteurMapper::entityToDto)
                .collect(Collectors.toList());

        return roleRecruteurResponseDTOS;
    }

}
