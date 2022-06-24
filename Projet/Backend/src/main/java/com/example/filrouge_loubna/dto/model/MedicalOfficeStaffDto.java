package com.example.filrouge_loubna.dto.model;


import com.example.filrouge_loubna.entities.MedicalOffice;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.serializers.MedicalOfficeStaffSerializer;
import com.example.filrouge_loubna.serializers.UserSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("MedicalOfficeStaff")
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = MedicalOfficeStaffSerializer.class)
public class MedicalOfficeStaffDto {
    private Long id;
    private UserDto medicalOfficeStaffUser;
    private Long medicalOfficeId;
}
