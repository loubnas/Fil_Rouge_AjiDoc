package com.example.filrouge_loubna.serializers;

import com.example.filrouge_loubna.dto.model.UserExtraInfoDto;
import com.example.filrouge_loubna.entities.UserExtraInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserExtraInfoSerializer extends JsonSerializer<UserExtraInfoDto> {
    @Override
    public void serialize(UserExtraInfoDto userExtraInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", userExtraInfo.getId());
        jsonGenerator.writeStringField("diplome",userExtraInfo.getDiplome());
        jsonGenerator.writeStringField("specialite",userExtraInfo.getSpecialite());

        jsonGenerator.writeObjectFieldStart("user");
        jsonGenerator.writeNumberField("id", userExtraInfo.getUser().getId());
        jsonGenerator.writeStringField("firstName", userExtraInfo.getUser().getFirstname());
        jsonGenerator.writeStringField("lastName", userExtraInfo.getUser().getLastname());
        jsonGenerator.writeStringField("image", userExtraInfo.getUser().getImage());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
