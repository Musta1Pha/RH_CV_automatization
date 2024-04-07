package com.example.rh_cv_automatisation.candidateManagement.services;

import com.example.rh_cv_automatisation.candidateManagement.dtos.request.CandidateRequestDTO;
import com.example.rh_cv_automatisation.candidateManagement.dtos.response.CandidateResponseDTO;
import com.example.rh_cv_automatisation.candidateManagement.entities.Candidate;
import com.example.rh_cv_automatisation.candidateManagement.mappers.CandidateMapper;
import com.example.rh_cv_automatisation.candidateManagement.repositories.CandidateRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.CandidatureRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.request.OfferEmploiRequestDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.CandidatureResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.dtos.response.OfferEmploiResponseDTO;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.Candidature;
import com.example.rh_cv_automatisation.jobOfferManagement.entities.OffreEmploi;
import com.example.rh_cv_automatisation.jobOfferManagement.enums.status;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.CandidatureMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.mappers.JobOfferMapper;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.CandidatureRepository;
import com.example.rh_cv_automatisation.jobOfferManagement.repositories.JobOfferRepository;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CandidateServiceImpl implements CandidateService{
    private JobOfferRepository jobOfferRepository;
    private CandidateRepository candidateRepository;
    private CandidatureRepository candidatureRepository;
    private CandidatureMapper candidatureMapper;
    private CandidateMapper candidateMapper;
    private JobOfferMapper jobOfferMapper;

    public CandidateServiceImpl(JobOfferRepository jobOfferRepository, CandidateRepository candidateRepository, CandidatureRepository candidatureRepository, CandidatureMapper candidatureMapper, CandidateMapper candidateMapper, JobOfferMapper jobOfferMapper) {
        this.jobOfferRepository = jobOfferRepository;
        this.candidateRepository = candidateRepository;
        this.candidatureRepository = candidatureRepository;
        this.candidatureMapper = candidatureMapper;
        this.candidateMapper = candidateMapper;
        this.jobOfferMapper = jobOfferMapper;
    }
    //TODO : Add coverage from model later
    @Override
    public CandidateResponseDTO postulerOffre(Long candidateId, Long offerId) {
        Candidate candidate = candidateRepository.findById(candidateId).get();
        OffreEmploi offreEmploi = jobOfferRepository.findById(offerId).get();

        List<Candidature> candidatures = candidatureRepository.findAllByCandidate(candidate);

        Candidature candidatureTest = candidatureRepository.chercherCandidadureExiste(candidate, offreEmploi);
        CandidateResponseDTO candidateResponseDTO = null;

        if (candidatureTest == null) {
            try {

                String cvPath = candidate.getCv().getPath();
                List<String> keywords = List.of(offreEmploi.getTitle(), offreEmploi.getDescription());
                String keywordsString = String.join(",", keywords);
                String experience = String.valueOf(offreEmploi.getExperience());

                URL githubScriptUrl = new URL("https://raw.githubusercontent.com/Musta1Pha/CV_MODEL_ADRIA/master/ADRIA_CV_MODEL.py");
                Path tempScriptFilePath = Files.createTempFile("ADRIA_CV_MODEL", ".py");

                try (InputStream inputStream = githubScriptUrl.openStream();
                     InputStreamReader streamReader = new InputStreamReader(inputStream);
                     BufferedReader reader = new BufferedReader(streamReader);
                     FileWriter writer = new FileWriter(tempScriptFilePath.toFile())) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.write("\n");
                    }
                }

                ProcessBuilder builder = new ProcessBuilder("python", ((Path) tempScriptFilePath).toAbsolutePath().toString(), cvPath, keywordsString, offreEmploi.getFormation(), experience);
                builder.redirectErrorStream(true);
                Process process = builder.start();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String lastLine = null;
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lastLine = line;
                    }
                    double finalPercentage = Double.parseDouble(lastLine);
                    // (ne peut pas postuler si % <)
                    if(finalPercentage < 50) return null;
                    CandidatureRequestDTO candidatureRequestDTO = CandidatureRequestDTO.builder()
                            .status(status.OPEN)
                            .dateCreation(new Date())
                            .finalPercentage(finalPercentage)
                            .noteEntretien(0)
                            .build();

                    Candidature candidature = candidatureMapper.dtoToEntity(candidatureRequestDTO);
                    candidature.setCandidate(candidate);
                    candidature.setOffreEmploi(offreEmploi);

                    candidatureRepository.save(candidature);

                    candidatures.add(candidature);

                    offreEmploi.setCandidatures(candidatures);
                    candidate.setCandidatures(candidatures);

                    jobOfferRepository.save(offreEmploi);
                    candidateRepository.save(candidate);

                    List<CandidatureResponseDTO> candidatureResponseDTOs = new ArrayList<>();
                    for (Candidature candidature1 : candidatures) {
                        CandidatureResponseDTO candidatureResponseDTO = candidatureMapper.entityToDto(candidature1);
                        OfferEmploiResponseDTO offerEmploiResponseDTO = jobOfferMapper.entityToDto(candidature1.getOffreEmploi());
                        candidatureResponseDTO.setOffreEmploi(offerEmploiResponseDTO);
                        candidatureResponseDTOs.add(candidatureResponseDTO);
                    }

                    candidateResponseDTO = candidateMapper.entityToDto(candidate);
                    candidateResponseDTO.setCandidatures(candidatureResponseDTOs);


                    process.waitFor();

                    Files.deleteIfExists(tempScriptFilePath);
                    return candidateResponseDTO;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
            return null;
    }


    @Override
    public CandidateResponseDTO CreationCompte(CandidateRequestDTO candidateRequestDTO) {
        Candidate candidate = new Candidate();
        candidate.setNom(candidateRequestDTO.getNom());
        candidate.setEmail(candidateRequestDTO.getEmail());
        candidate.setPrenom(candidateRequestDTO.getPrenom());
        candidate.setCv(candidateRequestDTO.getCv());
        candidate.setPassword(candidateRequestDTO.getPassword());

        candidateRepository.save(candidate);

        return candidateMapper.entityToDto(candidate);
    }
}
