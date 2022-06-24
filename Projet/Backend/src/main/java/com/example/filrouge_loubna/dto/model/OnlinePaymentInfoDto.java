package com.example.filrouge_loubna.dto.model;


import com.example.filrouge_loubna.serializers.OnlinePaymentInfoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel("OnlinePaymentInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = OnlinePaymentInfoSerializer.class)
public class OnlinePaymentInfoDto {
    private Long id;
    private String paypal_email;

    private String paypal_APIkey;
    private MedicalOfficeDto medicalOffice;
}
