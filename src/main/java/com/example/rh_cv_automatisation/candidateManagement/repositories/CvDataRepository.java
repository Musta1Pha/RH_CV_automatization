package com.example.rh_cv_automatisation.candidateManagement.repositories;

import com.example.rh_cv_automatisation.candidateManagement.entities.CvData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvDataRepository extends JpaRepository<CvData,Long> {
    CvData findByName(String name);
}
