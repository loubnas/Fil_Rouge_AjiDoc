package com.example.filrouge_loubna.serializers;

import com.example.filrouge_loubna.dto.model.MedicalOfficeDto;
import com.example.filrouge_loubna.dto.model.MedicalOfficeStaffDto;
import com.example.filrouge_loubna.dto.model.UserDocumentDto;
import com.example.filrouge_loubna.dto.model.UserDto;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.entities.UserDocument;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserSerializer extends JsonSerializer<UserDto> {

    @Override
    public void serialize(UserDto user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        //verification des valeurs

        if(user.getId()!=0) {
            jsonGenerator.writeNumberField("id", user.getId());
        }else{
            jsonGenerator.writeNullField("id");
        }
        jsonGenerator.writeStringField("firstname",user.getFirstname());
        jsonGenerator.writeStringField("lastname",user.getLastname());
        jsonGenerator.writeStringField("adress",user.getAdress());
        jsonGenerator.writeStringField("phone",user.getPhone());
        jsonGenerator.writeStringField("ville",user.getVille());
        jsonGenerator.writeStringField("email",user.getEmail());
        jsonGenerator.writeStringField("password",user.getPassword());
        if(user.getValidatedBy()!=null) {
            jsonGenerator.writeNumberField("validatedBy", user.getValidatedBy().getId());
        }else{
            jsonGenerator.writeNullField("validatedBy");
        }
        if(user.getValidateDate()!=null) {
            jsonGenerator.writeStringField("validateDate",user.getValidateDate().toString());
        }else{
            jsonGenerator.writeNullField("validateDate");
        }
        jsonGenerator.writeStringField("type",user.getType());
        jsonGenerator.writeStringField("image",user.getImage());

        //EXTRA INFO :
        if(user.getUserExtraInfo()!=null) {
            jsonGenerator.writeObjectFieldStart("userExtraInfo");
            jsonGenerator.writeNumberField("id", user.getUserExtraInfo().getId());
            jsonGenerator.writeStringField("specialite", user.getUserExtraInfo().getSpecialite());
            jsonGenerator.writeStringField("diplome", user.getUserExtraInfo().getDiplome());
            jsonGenerator.writeEndObject();
        }
        else{
            jsonGenerator.writeNullField("userExtraInfo");
        }

        // MEDICAL OFFICE :
        if(user.getMedicalOffices()!=null) {
            jsonGenerator.writeArrayFieldStart("medicalOffices");
            for (MedicalOfficeDto m : user.getMedicalOffices()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", m.getId());
                jsonGenerator.writeStringField("name", m.getName());
                jsonGenerator.writeStringField("adress", m.getAdress());
                jsonGenerator.writeStringField("phone", m.getPhone());
if(m.getMedicalOfficeStaffList()!=null) {
    jsonGenerator.writeArrayFieldStart("staffList");
    for (MedicalOfficeStaffDto s : m.getMedicalOfficeStaffList()) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", s.getMedicalOfficeStaffUser().getId());
        jsonGenerator.writeStringField("firstname", s.getMedicalOfficeStaffUser().getFirstname());
        jsonGenerator.writeStringField("lastname", s.getMedicalOfficeStaffUser().getLastname());
        jsonGenerator.writeStringField("adress", s.getMedicalOfficeStaffUser().getAdress());
        jsonGenerator.writeStringField("phone", s.getMedicalOfficeStaffUser().getPhone());
        jsonGenerator.writeStringField("image", s.getMedicalOfficeStaffUser().getImage());
        jsonGenerator.writeNumberField("medicalOfficeId", s.getMedicalOfficeId());
        jsonGenerator.writeEndObject();
    }
    jsonGenerator.writeEndArray();
}else{
    jsonGenerator.writeNullField("staffList");
}

                jsonGenerator.writeEndObject();
            }

            jsonGenerator.writeEndArray();
        }
        else{
            jsonGenerator.writeNullField("medicalOffices");
        }

        //Documents:
        if(user.getUserDocumentList()!=null) {
            jsonGenerator.writeArrayFieldStart("documents");
            for (UserDocumentDto ud : user.getUserDocumentList()) {
                jsonGenerator.writeStartObject();
                if(ud.getId()!=null) {
                    jsonGenerator.writeNumberField("id", ud.getId());
                }else{
                    jsonGenerator.writeNullField("id");
                }
                jsonGenerator.writeStringField("document", ud.getDocument());

                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }
        else{
            jsonGenerator.writeNullField("documents");
        }





        jsonGenerator.writeEndObject();
    }
}
