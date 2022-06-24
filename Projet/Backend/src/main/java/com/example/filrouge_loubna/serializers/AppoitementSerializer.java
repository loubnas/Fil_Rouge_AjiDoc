package com.example.filrouge_loubna.serializers;

import com.example.filrouge_loubna.dto.model.AppointementDto;
import com.example.filrouge_loubna.entities.Appointement;
import com.example.filrouge_loubna.entities.MedicalOffice;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.repositories.MedicalOfficeRepository;
import com.example.filrouge_loubna.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class AppoitementSerializer  extends JsonSerializer<AppointementDto> {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MedicalOfficeRepository medicalOfficeRepository;
    @Override
    public void serialize(AppointementDto appointement, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", appointement.getId());
        if(appointement.getDateTimeAppointement()!=null) {
            jsonGenerator.writeStringField("appointementDate", appointement.getDateTimeAppointement().toString());
        }else{
            jsonGenerator.writeNullField("appointementDate");
        }
        jsonGenerator.writeStringField("acceptanceMode", appointement.getAcceptanceMode());
        if(appointement.getAcceptanceDate()!=null) {
            jsonGenerator.writeStringField("acceptanceDate", appointement.getAcceptanceDate().toString());
    }else{
        jsonGenerator.writeNullField("acceptanceDate");
    }
        jsonGenerator.writeStringField("validationMode", appointement.getValidationMode());

        if(appointement.getValidationDate()!=null) {
            jsonGenerator.writeStringField("validationDate", appointement.getValidationDate().toString());
}else{
        jsonGenerator.writeNullField("validationDate");
        }


        Optional<User> user=userRepository.findById(appointement.getUserId());
        if(user.isPresent()) {
            jsonGenerator.writeObjectFieldStart("user");
            jsonGenerator.writeNumberField("id", user.get().getId());
            jsonGenerator.writeStringField("firstName", user.get().getFirstname());
            jsonGenerator.writeStringField("lastName", user.get().getLastname());
            jsonGenerator.writeStringField("image",  user.get().getImage());
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeNullField("user");

        }
        Optional<MedicalOffice> mo=medicalOfficeRepository.findById(appointement.getMedicalOfficeId());
        if(mo.isPresent()) {

            jsonGenerator.writeObjectFieldStart("medicalOffice");
            jsonGenerator.writeNumberField("id", mo.get().getId());
            jsonGenerator.writeStringField("name", mo.get().getName());
            jsonGenerator.writeStringField("adress", mo.get().getAdress());
            jsonGenerator.writeStringField("phone", mo.get().getPhone());
            if(mo.get().getAdministrator()!=null) {
                jsonGenerator.writeObjectFieldStart("administrator");

                jsonGenerator.writeStringField("firstname", mo.get().getAdministrator().getFirstname());
                jsonGenerator.writeStringField("lastname", mo.get().getAdministrator().getLastname());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndObject();
        } else {
            jsonGenerator.writeNullField("medicalOffice");

        }
        if(appointement.getPayment()!=null){
            jsonGenerator.writeObjectFieldStart("payment");
            jsonGenerator.writeBooleanField("isOnline", appointement.getPayment().isOnline());
            jsonGenerator.writeStringField("onlineReference", appointement.getPayment().getOnlineReference());
            jsonGenerator.writeEndObject();
        }
        else{
            jsonGenerator.writeNullField("payment");
        }

        jsonGenerator.writeEndObject();
    }
}
