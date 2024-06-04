package com.example.rh_cv_automatisation;

import com.example.rh_cv_automatisation.adminManagement.entities.Administrateur;
import com.example.rh_cv_automatisation.adminManagement.repositories.AdministrateurRepository;
import com.example.rh_cv_automatisation.adminManagement.services.AdministrationServiceImpl;
import com.example.rh_cv_automatisation.adminManagement.web.AdministrationController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RhCvAutomatisationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RhCvAutomatisationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AdministrationServiceImpl administrationService, AdministrationController administrationController,
                                        AdministrateurRepository administrateurRepository){
        return args -> {
            Administrateur administrateur = new Administrateur();
            administrateur.setEmail("admin@gmail.com");
            administrateur.setNom("admin");
            administrateur.setPrenom("none");
            administrateur.setPassword("1234");

            administrateurRepository.save(administrateur);







        };
    }

}
