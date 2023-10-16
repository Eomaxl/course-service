package com.company.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<CourseResponseDTO> {
    private HttpStatus status;
    private CourseResponseDTO response;
}
