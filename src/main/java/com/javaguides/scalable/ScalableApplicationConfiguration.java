package com.javaguides.scalable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ScalableApplicationConfiguration {
    @Bean
    public ITemplateResolver svgTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/svg/");
        resolver.setSuffix(".svg");
        resolver.setTemplateMode("XML");

        return resolver;
    }
    // tag::passwordEncoder[]
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
    // end::passwordEncoder[]
}
