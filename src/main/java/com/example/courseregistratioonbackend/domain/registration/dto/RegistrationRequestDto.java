package com.example.courseregistratioonbackend.domain.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationRequestDto {
    private long courseId;
    private long studentId;
}
