package com.example.filrouge_loubna.dto.model;

import com.example.filrouge_loubna.serializers.AppoitementSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@ApiModel("Appointement")
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = AppoitementSerializer.class)
public class AppointementDto {
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime DateTimeAppointement;
    private String acceptanceMode;
    @Nullable()
    private LocalDateTime acceptanceDate;
    @Nullable()
    private LocalDateTime validationDate;
    private String validationMode;
    private Long userId;

    private Long medicalOfficeId;
    private PaymentDto payment;

}
