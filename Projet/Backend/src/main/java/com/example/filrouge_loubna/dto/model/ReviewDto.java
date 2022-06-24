package com.example.filrouge_loubna.dto.model;

import com.example.filrouge_loubna.serializers.ReviewsSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel("Reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonSerialize(using = ReviewsSerializer.class)
public class ReviewDto {
    private Long id;
    private String review;
    private float score;
    private LocalDateTime DateTime;
    private Long userId;
    private Long medicalOfficeId;
}
