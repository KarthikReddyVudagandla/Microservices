package com.example.MCService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRatingItem {
    String UserId;
    List<RatingItem> ratingItemList;
}
