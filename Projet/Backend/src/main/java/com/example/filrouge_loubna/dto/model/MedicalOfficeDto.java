package com.example.filrouge_loubna.dto.model;



import com.example.filrouge_loubna.serializers.MedicalOfficeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@ApiModel("MedicalOffice")
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = MedicalOfficeSerializer.class)
public class MedicalOfficeDto {
    private Long id;
    private String name;
    private String phone;
    private String adress;
    private String image;
    private UserDto administrator;
    private List<MedicalOfficeStaffDto> medicalOfficeStaffList=new ArrayList<>();
    private OnlinePaymentInfoDto OnlinePaymentInfo;
    private List<ReviewDto> reviewsMedicalOfficeList=new ArrayList<>();


}
