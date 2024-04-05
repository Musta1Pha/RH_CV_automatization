package com.example.rh_cv_automatisation.recruiterManagement.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class RecruteurCreationRequestDTO {
    private RecruteurRequestDTO recruteurRequestDTO;
    private Map<String, List<Long>> roles;
}