package com.example.rh_cv_automatisation.Common.mappers;

import com.example.rh_cv_automatisation.Common.dtos.request.NotificationRequestDTO;
import com.example.rh_cv_automatisation.Common.dtos.response.NotificationResponseDTO;
import com.example.rh_cv_automatisation.Common.entities.Notification;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMapper implements BaseMapper<Notification, NotificationRequestDTO, NotificationResponseDTO> {
    @Override
    public NotificationResponseDTO entityToDto(Notification notification) {
        NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO();
        BeanUtils.copyProperties(notification,notificationResponseDTO);

        return notificationResponseDTO;
    }

    @Override
    public List<Notification> dtoToEntity(List<NotificationRequestDTO> notificationRequestDTOS) {
        List<Notification> notifications = new ArrayList<>();
        for(NotificationRequestDTO notificationRequestDTO : notificationRequestDTOS){
            Notification notification = this.dtoToEntity(notificationRequestDTO);
            notifications.add(notification);
        }

        return notifications;
    }

    @Override
    public Notification dtoToEntity(NotificationRequestDTO notificationRequestDTO) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationRequestDTO,notification);

        return notification;
    }

    @Override
    public List<NotificationResponseDTO> entityToDto(List<Notification> notifications) {
        List<NotificationResponseDTO> notificationResponseDTOS = new ArrayList<>();
        for(Notification notification : notifications){
            NotificationResponseDTO notificationResponseDTO = this.entityToDto(notification);
            notificationResponseDTOS.add(notificationResponseDTO);
        }

        return notificationResponseDTOS;
    }
}
