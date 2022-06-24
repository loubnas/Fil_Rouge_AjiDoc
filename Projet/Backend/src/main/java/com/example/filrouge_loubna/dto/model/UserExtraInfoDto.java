package com.example.filrouge_loubna.dto.model;


import com.example.filrouge_loubna.serializers.UserExtraInfoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@ApiModel("UserExtraInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = UserExtraInfoSerializer.class)
public class UserExtraInfoDto {
    private Long id;
    private UserDto user;
    private String specialite;
    private String diplome;
    private String description;

    private String specialiteImage;
    private String diplomeImage;
    private String cinImage;
}
