package com.example.filrouge_loubna.dto.model;

import com.example.filrouge_loubna.serializers.PayementSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Payment")
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = PayementSerializer.class)
public class PaymentDto {
    private long id;
    private AppointementDto appointement;
    private boolean isOnline;
    private String onlineReference;
}
