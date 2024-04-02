package com.example.rh_cv_automatisation.adminManagement.repositories;

import com.example.rh_cv_automatisation.adminManagement.entities.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur,Long> {
}
