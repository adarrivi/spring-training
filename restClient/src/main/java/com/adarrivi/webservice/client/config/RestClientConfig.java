package com.adarrivi.webservice.client.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import com.adarrivi.webservice.client.Employee;

@Configuration
@ComponentScan(basePackages = { "com.adarrivi" })
public class RestClientConfig {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(Employee.class);
        return jaxb2Marshaller;
    }

    @Bean
    public HttpMessageConverter<?> jaxb2MessageConverter(Jaxb2Marshaller jaxb2Marshaller) {
        return new MarshallingHttpMessageConverter(jaxb2Marshaller, jaxb2Marshaller);
    }

    @Bean
    public HttpMessageConverter<?> jsonMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    public RestTemplate restTemplate(HttpMessageConverter<?> jaxb2MessageConverter, HttpMessageConverter<?> jsonMessageConverter) {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = Arrays.asList(jaxb2MessageConverter, jsonMessageConverter);
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }
}
