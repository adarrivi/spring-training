package com.adarrivi.springmvc.config;

import com.adarrivi.springmvc.core.domain.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.Arrays;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.adarrivi"})
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.ignoreAcceptHeader(true);
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.mediaType("xml", MediaType.APPLICATION_XML);
    }

    @Bean
    public View jsonView() {
        return new MappingJackson2JsonView();
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(Supplier.class);
        return jaxb2Marshaller;
    }

    @Bean
    public View xmlView(Jaxb2Marshaller jaxb2Marshaller) {
        return new MarshallingView(jaxb2Marshaller);
    }

    @Bean
    public ViewResolver viewResolver(ContentNegotiationManager manager, View jsonView, View xmlView) {
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setContentNegotiationManager(manager);
        viewResolver.setOrder(1);
        viewResolver.setDefaultViews(Arrays.asList(jsonView, xmlView));
        return viewResolver;
    }

}
