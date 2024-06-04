package com.example.rh_cv_automatisation.candidateManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CvData {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;
    private String type;
    @Lob
    private byte[] pdfData;
}
