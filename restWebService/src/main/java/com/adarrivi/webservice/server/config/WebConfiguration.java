package com.adarrivi.webservice.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.adarrivi" })
@ImportResource("classpath:rest-servlet.xml")
public class WebConfiguration extends WebMvcConfigurerAdapter {

}
