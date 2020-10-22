package com.example.RDService.controller;

import com.example.RDService.entity.RatingItem;
import com.example.RDService.entity.UserRatingItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ratings")
public class RatingsDataController {

    @GetMapping
    public String test(){
        return "This is a test function";
    }

    @GetMapping(value = "{UserId}")
    public UserRatingItem getRatings(@PathVariable("UserId") String UserId){

        List<RatingItem> rl = new ArrayList<>();
        rl.add(new RatingItem("MovieId1",5));
        rl.add(new RatingItem("MovieId2",10));
        rl.add(new RatingItem("MovieId3",8));
        rl.add(new RatingItem("MovieId4",7));


        UserRatingItem uri = new UserRatingItem(UserId,rl);

        return uri;
    }
}
