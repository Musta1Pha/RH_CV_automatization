package com.example.rh_cv_automatisation.Common.mappers;

import java.util.List;

public interface BaseMapper<ENTITY, REQUEST_DTO, RESPONSE_DTO> {
    RESPONSE_DTO entityToDto(ENTITY entity);
    List<ENTITY> dtoToEntity(List<REQUEST_DTO> requestDtos);
    ENTITY dtoToEntity(REQUEST_DTO requestDto);
    List<RESPONSE_DTO> entityToDto(List<ENTITY> entities);

}
