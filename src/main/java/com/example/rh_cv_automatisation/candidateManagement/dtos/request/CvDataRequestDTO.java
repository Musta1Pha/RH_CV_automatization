package com.example.rh_cv_automatisation.candidateManagement.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CvDataRequestDTO {
    private String name;
    private String type;
    private byte[] pdfData;
}
