package com.example.filrouge_loubna.serializers;

import com.example.filrouge_loubna.dto.model.UserDocumentDto;
import com.example.filrouge_loubna.dto.model.UserDto;
import com.example.filrouge_loubna.entities.User;
import com.example.filrouge_loubna.entities.UserDocument;
import com.example.filrouge_loubna.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class UserDocumentSerializer extends JsonSerializer<UserDocumentDto> {
    @Autowired
    UserRepository userRepository;

    @Override
    public void serialize(UserDocumentDto userDocument, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", userDocument.getId());
        jsonGenerator.writeStringField("document",userDocument.getDocument());

        Optional<User> user=userRepository.findById(userDocument.getUserId());
        if(user.isPresent()) {
            jsonGenerator.writeObjectFieldStart("user");
            jsonGenerator.writeNumberField("id", user.get().getId());
            jsonGenerator.writeStringField("firstName", user.get().getFirstname());
            jsonGenerator.writeStringField("lastName", user.get().getLastname());
            jsonGenerator.writeStringField("image", user.get().getImage());
            jsonGenerator.writeEndObject();
        }else{
            jsonGenerator.writeNullField("user");
        }

        jsonGenerator.writeEndObject();
    }
}

