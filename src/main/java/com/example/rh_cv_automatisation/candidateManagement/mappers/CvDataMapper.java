package com.example.rh_cv_automatisation.candidateManagement.mappers;

import com.example.rh_cv_automatisation.Common.mappers.BaseMapper;
import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CvDataRequestDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CvDataResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.CvData;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CvDataMapper implements BaseMapper<CvData, CvDataRequestDTO, CvDataResponseDTO> {
    @Override
    public CvDataResponseDTO entityToDto(CvData cvData) {
        CvDataResponseDTO cvDataResponseDTO = new CvDataResponseDTO();
        BeanUtils.copyProperties(cvData,cvDataResponseDTO);

        return cvDataResponseDTO;
    }

    @Override
    public List<CvData> dtoToEntity(List<CvDataRequestDTO> cvDataRequestDTOS) {
        return null;
    }

    @Override
    public CvData dtoToEntity(CvDataRequestDTO cvDataRequestDTO) {
        CvData cvData = new CvData();
        BeanUtils.copyProperties(cvDataRequestDTO,cvData);

        return cvData;
    }

    @Override
    public List<CvDataResponseDTO> entityToDto(List<CvData> cvData) {
        return null;
    }
}
