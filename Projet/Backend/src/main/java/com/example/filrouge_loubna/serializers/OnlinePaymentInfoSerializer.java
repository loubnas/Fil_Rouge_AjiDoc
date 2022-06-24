package com.example.filrouge_loubna.serializers;

import com.example.filrouge_loubna.dto.model.OnlinePaymentInfoDto;
import com.example.filrouge_loubna.entities.OnlinePaymentInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class OnlinePaymentInfoSerializer extends JsonSerializer<OnlinePaymentInfoDto> {
    @Override
    public void serialize(OnlinePaymentInfoDto onlinePaymentInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id",onlinePaymentInfo.getId());
        jsonGenerator.writeStringField("email",onlinePaymentInfo.getPaypal_email());
        jsonGenerator.writeStringField("key",onlinePaymentInfo.getPaypal_APIkey());

        jsonGenerator.writeObjectFieldStart("medicalOffice");
        jsonGenerator.writeNumberField("id", onlinePaymentInfo.getMedicalOffice().getId());
        jsonGenerator.writeStringField("adress", onlinePaymentInfo.getMedicalOffice().getAdress());
        jsonGenerator.writeStringField("phone", onlinePaymentInfo.getMedicalOffice().getPhone());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();
    }
}
