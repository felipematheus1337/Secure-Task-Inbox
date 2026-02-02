package com.taskinbox.v1.kafka.consumer;

import com.taskinbox.v1.domain.model.TaskEvent;

import com.taskinbox.v1.domain.model.enumerations.TaskEventType;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskEventFilterStrategy implements RecordFilterStrategy<String, TaskEvent> {

    @Override
    public boolean filter(ConsumerRecord<String, TaskEvent> record) {
        String eventType = safeEventType(record);
        if (eventType == null) return true;

        try {
            TaskEventType.valueOf(eventType);
            return false;
        } catch (IllegalArgumentException e) {
            log.warn("Evento nao monitorado: type={}, record={}", eventType, record);
            return true;
        }
    }

    private String safeEventType(ConsumerRecord<String, TaskEvent> record) {
        if (record == null || record.value() == null || record.value().getEventType() == null) {
            log.warn("Evento sem eventType: {}", record);
            return null;
        }
        return record.value().getEventType().name();
    }


}
