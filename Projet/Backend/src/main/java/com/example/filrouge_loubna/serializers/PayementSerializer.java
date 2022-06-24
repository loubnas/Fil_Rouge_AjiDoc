package com.example.filrouge_loubna.serializers;

import com.example.filrouge_loubna.dto.model.PaymentDto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PayementSerializer extends JsonSerializer<PaymentDto>{

    @Override
        public void serialize(PaymentDto payment, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id",payment.getId());
        jsonGenerator.writeBooleanField("isOnline",payment.isOnline());
        jsonGenerator.writeStringField("onlineReference", payment.getOnlineReference());
         jsonGenerator.writeEndObject();






    }
}
