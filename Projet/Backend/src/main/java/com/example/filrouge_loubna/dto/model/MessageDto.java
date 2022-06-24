package com.example.filrouge_loubna.dto.model;

import com.example.filrouge_loubna.serializers.AppoitementSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Message")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MessageDto {

    private Long id;
    private String name;
    private String phone;
    private String message;

}
