package com.example.filrouge_loubna.dto.model;

import com.example.filrouge_loubna.serializers.UserDocumentSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("UserDucument")
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = UserDocumentSerializer.class)
public class UserDocumentDto {
    private Long id;
    private String document;
    private Long userId;
}
