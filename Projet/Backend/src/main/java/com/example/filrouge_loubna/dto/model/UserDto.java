package com.example.filrouge_loubna.dto.model;

import com.example.filrouge_loubna.serializers.UserSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@ApiModel("User")
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = UserSerializer.class)
public class UserDto  {

    private long id;
    private String firstname;
    private String lastname;
    private String adress;
    private String phone;
    private String ville;
    private String email;
    private String password;
    private String newpassword;
    private UserDto validatedBy;
    private LocalDateTime validateDate;
    private String type;
    private String image;
    private List<UserDocumentDto> userDocumentList;
    private UserExtraInfoDto userExtraInfo;
    private List<MedicalOfficeDto> medicalOffices;
    private MedicalOfficeStaffDto medicalOfficeStaff;
    private List<ReviewDto> reviewsList;
    private List<AppointementDto> appointementUserList;

}
















