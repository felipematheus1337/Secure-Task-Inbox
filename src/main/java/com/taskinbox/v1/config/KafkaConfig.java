package com.taskinbox.v1.config;

import com.taskinbox.v1.domain.model.TaskEvent;
import com.taskinbox.v1.kafka.consumer.TaskEventFilterStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJacksonJsonMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
@Slf4j
public class KafkaConfig {

    private final KafkaAdmin kafkaAdmin;
    private final TaskEventFilterStrategy filterStrategy;

    @Bean
    public RecordMessageConverter messageConverter() {
        return new StringJacksonJsonMessageConverter();
    }

    @Bean
    public KafkaListenerErrorHandler topicoErrorHandler() {
        return (m, e) -> {
            log.error("Error: {}", e.getMessage());
            return e.getMessage();
        };
    }

    @Bean
    public ConsumerFactory<String, TaskEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>(kafkaAdmin.getConfigurationProperties());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JacksonJsonDeserializer.class);

        JacksonJsonDeserializer<TaskEvent> delegate = new JacksonJsonDeserializer<>(TaskEvent.class);
        delegate.addTrustedPackages("*");
        delegate.setUseTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(delegate)
        );
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TaskEvent> filterKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TaskEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRecordFilterStrategy(filterStrategy);
        return factory;

    }


}
