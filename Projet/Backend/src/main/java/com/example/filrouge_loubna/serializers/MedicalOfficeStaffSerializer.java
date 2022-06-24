package com.example.filrouge_loubna.serializers;

import com.example.filrouge_loubna.dto.model.MedicalOfficeStaffDto;
import com.example.filrouge_loubna.entities.MedicalOffice;
import com.example.filrouge_loubna.entities.MedicalOfficeStaff;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.repositories.MedicalOfficeRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class MedicalOfficeStaffSerializer extends JsonSerializer<MedicalOfficeStaffDto> {
    @Autowired
    MedicalOfficeRepository medicalOfficeRepository;
    @Override
    public void serialize(MedicalOfficeStaffDto medicalOfficeStaff, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", medicalOfficeStaff.getId());
if(medicalOfficeStaff.getMedicalOfficeStaffUser()!=null){
        jsonGenerator.writeObjectFieldStart("user");
        jsonGenerator.writeNumberField("id", medicalOfficeStaff.getMedicalOfficeStaffUser().getId());
        jsonGenerator.writeStringField("firstName", medicalOfficeStaff.getMedicalOfficeStaffUser().getFirstname());
        jsonGenerator.writeStringField("lastName", medicalOfficeStaff.getMedicalOfficeStaffUser().getLastname());
        jsonGenerator.writeStringField("image", medicalOfficeStaff.getMedicalOfficeStaffUser().getImage());
        jsonGenerator.writeEndObject();
}

        Optional<MedicalOffice> medicalOffice=medicalOfficeRepository.findById(medicalOfficeStaff.getMedicalOfficeId());
        if(medicalOffice.isPresent()) {
            jsonGenerator.writeObjectFieldStart("medicalOffice");
            jsonGenerator.writeNumberField("id", medicalOffice.get().getId());
            jsonGenerator.writeStringField("adress", medicalOffice.get().getAdress());
            jsonGenerator.writeStringField("phone", medicalOffice.get().getPhone());
            jsonGenerator.writeEndObject();
        }else{
            jsonGenerator.writeNullField("medicalOffice");
        }




        jsonGenerator.writeEndObject();
    }
}
