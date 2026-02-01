package com.taskinbox.v1.config;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync()
@ComponentScan("com.taskinbox.v1.domain.service")
@RequiredArgsConstructor
public class SpringAsyncConfig implements AsyncConfigurer {

    private final AppAsyncConfig appAsyncConfig;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setMaxPoolSize(appAsyncConfig.getThreadPoolSize());
        exec.setCorePoolSize(appAsyncConfig.getThreadPoolSize());
        exec.setQueueCapacity(appAsyncConfig.getThreadPoolQueueSize());
        exec.afterPropertiesSet();
        return exec;
    }
}
