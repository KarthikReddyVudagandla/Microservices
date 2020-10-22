package com.example.MCService.controller;


import com.example.MCService.entity.CatalogItem;
import com.example.MCService.entity.MovieInfoItem;
import com.example.MCService.entity.UserRatingItem;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("movie")
@EnableHystrix
@RefreshScope
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${serviceName: default value}")
    private String serviceName;

    @GetMapping
    @PreAuthorize("hasAuthority('CAN_READ')")
    public String test(){
       return serviceName;
    }

    @GetMapping(value="{UserId}")
    @HystrixCommand(fallbackMethod = "getCatalogFallback")
    @PreAuthorize("hasAuthority('CAN_READ')")
    public List<CatalogItem> getCatalog(@PathVariable String UserId){
        UserRatingItem userRatingItem= restTemplate.getForObject("http://RatingDataService/ratings/"+UserId, UserRatingItem.class);

        return userRatingItem.getRatingItemList().stream().map(rating -> {
            MovieInfoItem movie = restTemplate.getForObject("http://MovieInfoService/info/" + rating.getMovieId(), MovieInfoItem.class);
            return new CatalogItem(movie.getMovieName(), movie.getDescription(), rating.getRating());
        }).collect(Collectors.toList());
    }

    public List<CatalogItem> getCatalogFallback(@PathVariable String UserId){

        System.out.println("Fall back Method called by Hystrix- circuit breaker");
        return Collections.EMPTY_LIST;
    }

}
