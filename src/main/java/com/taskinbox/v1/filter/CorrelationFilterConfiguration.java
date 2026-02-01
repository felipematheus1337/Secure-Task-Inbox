package com.taskinbox.v1.filter;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@ConfigurationPropertiesScan
@Configuration
public class CorrelationFilterConfiguration {


    @Bean
    @Order(1)
    public FilterRegistrationBean<CorrelationRequestHeaderFilter> servletRegistrationBean() {

        final FilterRegistrationBean<CorrelationRequestHeaderFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(createCorrelationRequestFilter());

        return registrationBean;

    }

    @Bean
    public CorrelationRequestHeaderFilter createCorrelationRequestFilter() {
        return new CorrelationRequestHeaderFilter();
    }
}
