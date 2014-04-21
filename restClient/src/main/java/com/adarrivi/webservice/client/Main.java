package com.adarrivi.webservice.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.adarrivi.webservice.client.config.RestClientConfig;

public class Main {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RestClientConfig.class);
        context.registerShutdownHook();
        RestClient restClient = context.getBean(RestClient.class);
        restClient.doRestCalls();
    }
}
