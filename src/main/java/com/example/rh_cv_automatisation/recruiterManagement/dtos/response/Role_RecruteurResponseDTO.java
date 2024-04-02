package com.example.rh_cv_automatisation.recruiterManagement.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role_RecruteurResponseDTO {
    private Long id;
    private String role_name;
}
