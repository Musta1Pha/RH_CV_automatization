package com.example.rh_cv_automatisation.ai_management.web;

import com.example.rh_cv_automatisation.ai_management.services.Llama2RestService;
import com.example.rh_cv_automatisation.candidateManagement.entities.CvData;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.RequiredSkillsRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.RequiredSkills;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class Llama2RestController {

    @Value("${lmstudio.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private Llama2RestService llama2RestService;

    public Llama2RestController(RestTemplate restTemplate, Llama2RestService llama2RestService) {
        this.restTemplate = restTemplate;
        this.llama2RestService = llama2RestService;
    }

    @PostMapping(path = "/chat-with-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Integer chatWithFile( @RequestParam("experience") int experience,
                                @RequestParam("education") int education,
                                @RequestParam("keywords") List<RequiredSkillsRequestDTO> keywords,
                                @RequestParam("cvData") CvData cvData) {

        return llama2RestService.chatWithFile(experience, education, keywords, cvData);
    }


}

