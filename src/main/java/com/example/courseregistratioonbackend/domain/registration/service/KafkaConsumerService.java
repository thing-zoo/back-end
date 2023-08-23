package com.example.courseregistratioonbackend.domain.registration.service;

import com.example.courseregistratioonbackend.domain.registration.dto.RegistrationRequestDto;
import com.example.courseregistratioonbackend.global.exception.GlobalException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final RegistrationService registrationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "registration", groupId = "${spring.kafka.consumer.group-id}", errorHandler = "kafkaErrorHandler")
    public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException, GlobalException {
        RegistrationRequestDto requestDto = objectMapper.readValue(record.value(), RegistrationRequestDto.class);
        log.info("consumer: registration -, Partition: {}, Offset: {}: {}",
                record.partition(), record.offset(), record.value());
        registrationService.register(requestDto.getCourseId(), requestDto.getStudentId());
    }
}
