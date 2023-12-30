package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RestaurantDto {

    private long id;
    private String title;
    private String photoUrl;
    private  String content ;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

}
