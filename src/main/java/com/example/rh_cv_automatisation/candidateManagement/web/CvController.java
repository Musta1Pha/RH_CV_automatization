package com.example.rh_cv_automatisation.candidateManagement.web;

import com.example.rh_cv_automatisation.candidateManagement.entities.CvData;
import com.example.rh_cv_automatisation.candidateManagement.services.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/cv")
public class CvController {

    private StorageService service;

    public CvController(StorageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> uploadCV(@RequestParam("cv") MultipartFile file) throws IOException {
        CvData uploadImage = service.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=service.downloadFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/pdf"))
                .body(imageData);

    }
}
