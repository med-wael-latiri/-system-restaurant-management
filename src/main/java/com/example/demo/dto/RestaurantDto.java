package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RestaurantDto {

    private long id;
    @NotEmpty(message = "Restaurant title should not be empty ")
    private String title;
    @NotEmpty(message = "Restaurant photoUrl should not be empty ")
    private String photoUrl;
    @NotEmpty(message = "Restaurant content should not be empty ")
    private  String content ;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

}
