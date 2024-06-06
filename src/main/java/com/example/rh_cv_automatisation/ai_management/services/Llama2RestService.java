package com.example.rh_cv_automatisation.ai_management.services;

import com.example.rh_cv_automatisation.candidateManagement.entities.CvData;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.RequiredSkillsRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.RequiredSkills;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Llama2RestService {
    public Integer chatWithFile(int experience, int education, List<RequiredSkillsRequestDTO> keywords, CvData cvData);
    public String extractTextFromPdf(MultipartFile file);
}
