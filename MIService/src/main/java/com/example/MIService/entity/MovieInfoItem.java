package com.example.MIService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieInfoItem {
    String MovieId;
    String MovieName;
    String description;
}
