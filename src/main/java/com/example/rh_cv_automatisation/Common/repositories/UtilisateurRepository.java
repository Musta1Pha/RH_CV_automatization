package com.example.rh_cv_automatisation.Common.repositories;

import com.example.rh_cv_automatisation.Common.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
}
