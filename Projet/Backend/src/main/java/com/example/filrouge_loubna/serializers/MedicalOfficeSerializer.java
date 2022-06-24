package com.example.filrouge_loubna.serializers;

import com.example.filrouge_loubna.dto.model.MedicalOfficeDto;
import com.example.filrouge_loubna.dto.model.MedicalOfficeStaffDto;
import com.example.filrouge_loubna.dto.model.ReviewDto;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class MedicalOfficeSerializer extends JsonSerializer<MedicalOfficeDto> {
    @Autowired
    UserRepository userRepository;
    @Override
    public void serialize(MedicalOfficeDto medicalOffice, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", medicalOffice.getId());
        jsonGenerator.writeStringField("name", medicalOffice.getName());
        jsonGenerator.writeStringField("adress", medicalOffice.getAdress());
        jsonGenerator.writeStringField("phone", medicalOffice.getPhone());
        jsonGenerator.writeStringField("image", medicalOffice.getImage());

        if(medicalOffice.getAdministrator()!=null) {
            jsonGenerator.writeObjectFieldStart("administrator");
            jsonGenerator.writeNumberField("id", medicalOffice.getAdministrator().getId());
            jsonGenerator.writeStringField("firstname", medicalOffice.getAdministrator().getFirstname());
            jsonGenerator.writeStringField("lastname", medicalOffice.getAdministrator().getLastname());
            jsonGenerator.writeStringField("email", medicalOffice.getAdministrator().getEmail());
            jsonGenerator.writeStringField("phone", medicalOffice.getAdministrator().getPhone());
            jsonGenerator.writeStringField("image", medicalOffice.getAdministrator().getImage());
            jsonGenerator.writeStringField("ville", medicalOffice.getAdministrator().getVille());
            if(medicalOffice.getAdministrator().getUserExtraInfo()!=null) {
                jsonGenerator.writeObjectFieldStart("userExtraInfo");
                jsonGenerator.writeStringField("specialite", medicalOffice.getAdministrator().getUserExtraInfo().getSpecialite());
                jsonGenerator.writeStringField("diplome", medicalOffice.getAdministrator().getUserExtraInfo().getDiplome());

                jsonGenerator.writeStringField("description", medicalOffice.getAdministrator().getUserExtraInfo().getDescription());
                jsonGenerator.writeEndObject();
            }
            else{
                jsonGenerator.writeNullField("userExtraInfo");
            }
            jsonGenerator.writeEndObject();
        }else {
            jsonGenerator.writeNullField("administrator");
        }

        if(medicalOffice.getOnlinePaymentInfo()!=null) {
            jsonGenerator.writeObjectFieldStart("onlinePaymentInfo");
            jsonGenerator.writeNumberField("id", medicalOffice.getOnlinePaymentInfo().getId());
            jsonGenerator.writeStringField("email", medicalOffice.getOnlinePaymentInfo().getPaypal_email());
            jsonGenerator.writeStringField("key", medicalOffice.getOnlinePaymentInfo().getPaypal_APIkey());
            jsonGenerator.writeEndObject();
        }else {
                jsonGenerator.writeNullField("onlinePaymentInfo");
            }



            if(medicalOffice.getMedicalOfficeStaffList()!=null) {
            jsonGenerator.writeArrayFieldStart("staff");
            for (MedicalOfficeStaffDto mos : medicalOffice.getMedicalOfficeStaffList()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("staffId", mos.getId());
                jsonGenerator.writeNumberField("userId", mos.getMedicalOfficeStaffUser().getId());
                jsonGenerator.writeStringField("firstname", mos.getMedicalOfficeStaffUser().getFirstname());
                jsonGenerator.writeStringField("lastname", mos.getMedicalOfficeStaffUser().getLastname());
                jsonGenerator.writeStringField("image", mos.getMedicalOfficeStaffUser().getImage());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
        else{
            jsonGenerator.writeNullField("staff");
        }

        if(medicalOffice.getReviewsMedicalOfficeList()!=null) {
            jsonGenerator.writeArrayFieldStart("reviews");
            for (ReviewDto r : medicalOffice.getReviewsMedicalOfficeList()) {

                //jsonGenerator.writeStartObject();
                /*jsonGenerator.writeNumberField("id", r.getId());

                Optional<User> u=userRepository.findById(r.getUserId());
                if(u.isPresent()) {
                    jsonGenerator.writeNumberField("userId", r.getUserId());
                    jsonGenerator.writeStringField("userName", u.get().getFirstname()+" "+u.get().getLastname());
                    jsonGenerator.writeStringField("userImage", u.get().getImage());

                }
                jsonGenerator.writeStringField("date", r.getDateTime().toString());
                jsonGenerator.writeStringField("review", r.getReview());
                jsonGenerator.writeNumberField("score", r.getScore());*/
                jsonGenerator.writeObject(r);
                //jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
        else{
            jsonGenerator.writeNullField("reviews");
        }





        jsonGenerator.writeEndObject();
    }
}
