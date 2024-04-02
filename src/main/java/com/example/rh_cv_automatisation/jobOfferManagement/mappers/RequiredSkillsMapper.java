package com.example.rh_cv_automatisation.jobOfferManagement.mappers;

import com.example.rh_cv_automatisation.Common.mappers.BaseMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.RequiredSkillsRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.RequiredSkillsResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.RequiredSkills;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequiredSkillsMapper implements BaseMapper<RequiredSkills, RequiredSkillsRequestDTO, RequiredSkillsResponseDTO> {
    @Override
    public RequiredSkillsResponseDTO entityToDto(RequiredSkills requiredSkills) {
        RequiredSkillsResponseDTO requiredSkillsResponseDTO = new RequiredSkillsResponseDTO();
        BeanUtils.copyProperties(requiredSkills,requiredSkillsResponseDTO);
        return requiredSkillsResponseDTO;
    }

    @Override
    public List<RequiredSkills> entityToDto(List<RequiredSkillsRequestDTO> requiredSkillsRequestDTOS) {
        List<RequiredSkills> requiredSkills = new ArrayList<RequiredSkills>();
        for (RequiredSkillsRequestDTO requiredSkill : requiredSkillsRequestDTOS){
            requiredSkills.add(dtoToEntity(requiredSkill));
        }

        return requiredSkills;
    }

    @Override
    public RequiredSkills dtoToEntity(RequiredSkillsRequestDTO requiredSkillsRequestDTO) {
        RequiredSkills requiredSkills = new RequiredSkills();
        BeanUtils.copyProperties(requiredSkillsRequestDTO,requiredSkills);
        return requiredSkills;
    }
}
