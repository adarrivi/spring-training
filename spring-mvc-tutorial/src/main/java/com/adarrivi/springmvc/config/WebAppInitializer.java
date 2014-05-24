package com.adarrivi.springmvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    @SuppressWarnings("resource")
    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.registerShutdownHook();
        rootContext.register(WebConfiguration.class);
        container.addListener(new ContextLoaderListener(rootContext));

        ServletRegistration.Dynamic dispatcher = container.addServlet("restServlet", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/webservice/*");
    }

}
