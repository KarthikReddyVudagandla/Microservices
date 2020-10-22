package com.example.UI.controller;

import com.example.UI.conf.AccessToken;
import com.example.UI.entity.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Controller
@EnableOAuth2Sso
public class UIController extends WebSecurityConfigurerAdapter {

    @Autowired
    RestTemplate restTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @RequestMapping(value = "/")
    public String loadUI(){
        return "home";
    }

    @RequestMapping(value = "/secure")
    public String loadSecureUI(){
        return "secure";
    }

    @RequestMapping(value = "/CatalogItems")
    public String loadMovieCatalogUI(Model model){

        HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.add("Authorization", AccessToken.getAccessToken());

        HttpEntity<CatalogItem> httpEntity = new HttpEntity<>(httpHeaders);
        try {
            ResponseEntity<CatalogItem[]> responseEntity = restTemplate.exchange("http://localhost:8084/mcs/movie/1", HttpMethod.GET, httpEntity, CatalogItem[].class);
            model.addAttribute("CatalogItems",responseEntity.getBody());
            System.out.println(responseEntity.getBody()+" ++++++++++++ "+responseEntity.getBody().length);
        }catch(HttpStatusCodeException e){
            ResponseEntity responseEntity = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }


        return "secure";
    }

}
