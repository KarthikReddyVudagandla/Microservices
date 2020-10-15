package com.example.MIService.controller;

import com.example.MIService.entity.MovieInfoItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "info")
public class MovieInfoController {

    @GetMapping
    public String test(){
        return "This is MI service";
    }

    @GetMapping(value = "{MovieId}")
    public MovieInfoItem getMovieInfo(@PathVariable("MovieId")String MovieId){
        return new MovieInfoItem(MovieId, "XXX", "Description of XXX");
    }
}
