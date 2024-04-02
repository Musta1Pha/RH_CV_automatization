package com.example.rh_cv_automatisation.adminManagement.mappers;

import com.example.rh_cv_automatisation.adminManagement.dtos.AdministrateurDTO;
import com.example.rh_cv_automatisation.adminManagement.entities.Administrateur;
import org.springframework.beans.BeanUtils;

public class AdministrateurMapper {
    public Administrateur fromAdministrateurDTO(AdministrateurDTO administrateurDTO){
        Administrateur administrateur = new Administrateur();
        BeanUtils.copyProperties(administrateurDTO,administrateur);

        return administrateur;
    }

    public AdministrateurDTO fromAdministrateur(Administrateur administrateur){
        AdministrateurDTO administrateurDTO = new AdministrateurDTO();
        BeanUtils.copyProperties(administrateur,administrateurDTO);

        return administrateurDTO;
    }
}
