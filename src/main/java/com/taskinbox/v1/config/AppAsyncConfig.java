package com.taskinbox.v1.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "async-config")
@Getter
@Setter
public class AppAsyncConfig {

    private Integer threadPoolSize;
    private Integer threadPoolQueueSize;
    private Integer threadPoolTimeoutSeconds;

}
