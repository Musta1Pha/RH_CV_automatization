package com.example.rh_cv_automatisation.jobOfferManagement.mappers;

import com.example.rh_cv_automatisation.Common.mappers.BaseMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.OfferEmploiRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobOfferMapper implements BaseMapper<OffreEmploi, OfferEmploiRequestDTO, OfferEmploiResponseDTO> {

   private RequiredSkillsMapper requiredSkillsMapper;

    public JobOfferMapper(RequiredSkillsMapper requiredSkillsMapper) {
        this.requiredSkillsMapper = requiredSkillsMapper;
    }

    @Override
    public OfferEmploiResponseDTO entityToDto(OffreEmploi offreEmploi) {
        OfferEmploiResponseDTO offerEmploiResponseDTO = new OfferEmploiResponseDTO();
        BeanUtils.copyProperties(offreEmploi, offerEmploiResponseDTO);
        offerEmploiResponseDTO.setRequiredSkills(requiredSkillsMapper.entityToDto(offreEmploi.getRequiredSkills()));
        return offerEmploiResponseDTO;
    }

    @Override
    public List<OffreEmploi> dtoToEntity(List<OfferEmploiRequestDTO> offerEmploiRequestDTOS) {
        return null;
    }

    @Override
    public OffreEmploi dtoToEntity(OfferEmploiRequestDTO offerEmploiRequestDTO) {
        OffreEmploi offreEmploi = new OffreEmploi();
        BeanUtils.copyProperties(offerEmploiRequestDTO,offreEmploi);
        return offreEmploi;
    }

    @Override
    public List<OfferEmploiResponseDTO> entityToDto(List<OffreEmploi> offreEmplois) {
        List<OfferEmploiResponseDTO> offerEmploiResponseDTOS = new ArrayList<>();
        for(OffreEmploi offreEmploi : offreEmplois){
            offerEmploiResponseDTOS.add(entityToDto(offreEmploi));
        }

        return offerEmploiResponseDTOS;
    }
}
