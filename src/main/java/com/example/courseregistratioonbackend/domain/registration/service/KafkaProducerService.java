package com.example.courseregistratioonbackend.domain.registration.service;

import com.example.courseregistratioonbackend.domain.registration.dto.RegistrationRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final String TOPIC_NAME = "registration";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public String send(RegistrationRequestDto requestDto) throws JsonProcessingException {
        String jsonObject = objectMapper.writeValueAsString(requestDto);
        log.info("producer: " + TOPIC_NAME + ": " + jsonObject);
        kafkaTemplate.send(TOPIC_NAME, jsonObject);
        return "수강 신청 요청중...";
    }

}
