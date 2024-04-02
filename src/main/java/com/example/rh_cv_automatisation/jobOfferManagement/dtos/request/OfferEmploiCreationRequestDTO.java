package com.example.rh_cv_automatisation.jobOfferManagement.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OfferEmploiCreationRequestDTO {
    private OfferEmploiRequestDTO offerEmploiRequestDTO;
    private Map<String, List<Long>> requiredSkills;
}
