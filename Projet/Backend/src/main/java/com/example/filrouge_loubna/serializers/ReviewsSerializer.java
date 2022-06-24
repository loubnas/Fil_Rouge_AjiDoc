package com.example.filrouge_loubna.serializers;

import com.example.filrouge_loubna.dto.model.ReviewDto;
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

public class ReviewsSerializer extends JsonSerializer<ReviewDto> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MedicalOfficeRepository medicalOfficeRepository;
    @Override
    public void serialize(ReviewDto reviews, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", reviews.getId());
        jsonGenerator.writeStringField("review",reviews.getReview());
        jsonGenerator.writeNumberField("score",reviews.getScore());


        Optional<User> user=userRepository.findById(reviews.getUserId());

        if(user.isPresent()) {
    jsonGenerator.writeObjectFieldStart("user");
    jsonGenerator.writeNumberField("id", user.get().getId());
    jsonGenerator.writeStringField("firstName", user.get().getFirstname());
    jsonGenerator.writeStringField("lastName",user.get().getLastname());
    jsonGenerator.writeStringField("image", user.get().getImage());
    jsonGenerator.writeEndObject();
}else{
    jsonGenerator.writeNullField("user");
}

        Optional<MedicalOffice>  medicalOffice =medicalOfficeRepository.findById(reviews.getMedicalOfficeId());
        if(medicalOffice.isPresent()) {
    jsonGenerator.writeObjectFieldStart("medicalOffice");
    jsonGenerator.writeNumberField("id", medicalOffice.get().getId());
            jsonGenerator.writeStringField("name", medicalOffice.get().getName());
    jsonGenerator.writeStringField("adress", medicalOffice.get().getAdress());
    jsonGenerator.writeStringField("phone", medicalOffice.get().getPhone());
    jsonGenerator.writeEndObject();
}else{
    jsonGenerator.writeNullField("medicalOffice");
}
        jsonGenerator.writeEndObject();

    }
}
