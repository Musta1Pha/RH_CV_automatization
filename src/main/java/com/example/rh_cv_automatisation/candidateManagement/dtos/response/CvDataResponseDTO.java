package com.example.rh_cv_automatisation.candidateManagement.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CvDataResponseDTO {
    public Long id;
    private String name;
    private String type;
    private byte[] pdfData;
}
