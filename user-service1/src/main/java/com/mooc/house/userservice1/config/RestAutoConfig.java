package com.mooc.house.userservice1.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.google.common.collect.Lists;
import org.apache.http.client.HttpClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Configuration
public class RestAutoConfig {
    public static class RestTemplateConfig {
        @Bean
        @LoadBalanced
        RestTemplate lbRestTemplate(HttpClient httpClient) {
            RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
            template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            template.getMessageConverters().add(1, new FastJsonHttpMessageConverter5());
            return template;
        }

        @Bean
        RestTemplate directRestTemplate(HttpClient httpClient) {
            RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
            template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            template.getMessageConverters().add(1, new FastJsonHttpMessageConverter5());
            return template;
        }
    }

    /**
     * 修复 fastjson中MediaType.ALL的错误
     */
    public static class FastJsonHttpMessageConverter5 extends FastJsonHttpMessageConverter4 {
        public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

        public FastJsonHttpMessageConverter5() {
            setDefaultCharset(DEFAULT_CHARSET);
            setSupportedMediaTypes(Lists.newArrayList(MediaType.APPLICATION_JSON,new MediaType("application","*+")));
        }
    }
}
