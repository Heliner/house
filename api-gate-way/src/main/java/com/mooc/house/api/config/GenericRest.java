package com.mooc.house.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class GenericRest {
    @Autowired
    private RestTemplate lbRestTemplate;
    @Autowired
    private RestTemplate directRestTemplate;

    private static final String directFlag = "direct://";

    public <T>ResponseEntity<T> post(String url, Object reqBody, ParameterizedTypeReference<T> parameterizedTypeReference){
        RestTemplate template =getRestTempate(url);
        url.replace(directFlag,"");
        return template.exchange(url, HttpMethod.POST,new HttpEntity<>(reqBody),parameterizedTypeReference);
    }
    public <T>ResponseEntity<T> get(String url, ParameterizedTypeReference<T> parameterizedTypeReference){
        RestTemplate template =getRestTempate(url);
        url.replace(directFlag,"");
        return template.exchange(url, HttpMethod.GET,HttpEntity.EMPTY,parameterizedTypeReference);
    }

    private RestTemplate getRestTempate(String url) {
        RestTemplate template = null;
        if(url.contains(directFlag)){
            template = directRestTemplate;
        }else
            template=lbRestTemplate;
        return  template;
    }
}
