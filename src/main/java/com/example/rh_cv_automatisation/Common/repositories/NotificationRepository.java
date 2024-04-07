package com.example.rh_cv_automatisation.Common.repositories;

import com.example.rh_cv_automatisation.Common.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
