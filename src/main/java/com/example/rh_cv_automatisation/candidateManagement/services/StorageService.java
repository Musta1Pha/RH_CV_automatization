package com.example.rh_cv_automatisation.candidateManagement.services;

import com.example.rh_cv_automatisation.candidateManagement.entities.CvData;
import com.example.rh_cv_automatisation.candidateManagement.repositories.CvDataRepository;
import com.example.rh_cv_automatisation.candidateManagement.utils.CvUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static com.example.rh_cv_automatisation.candidateManagement.utils.CvUtils.decompressCv;

@Service
public class StorageService {
    private CvDataRepository repository;

    public StorageService(CvDataRepository repository) {
        this.repository = repository;
    }

    public CvData uploadFile(MultipartFile file) throws IOException {
        CvData cvData = repository.save(CvData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .pdfData(CvUtils.compressCv(file.getBytes()))
                .build());

        if(cvData != null){
            return cvData;
        }

        return null;
    }

    public byte[] downloadFile(String fileName){
        Optional<CvData> dbCvData = Optional.ofNullable(repository.findByName(fileName));
        byte[] images=CvUtils.decompressCv(dbCvData.get().getPdfData());
        return images;
    }



}
