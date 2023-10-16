package com.company.courseservice.controller;

import com.company.courseservice.dto.Course;
import com.company.courseservice.dto.CourseRequestDTO;
import com.company.courseservice.dto.CourseResponseDTO;
import com.company.courseservice.dto.ServiceResponse;
import com.company.courseservice.service.CourseService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping
    public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody CourseRequestDTO courseRequestDTO){
       CourseResponseDTO newCourse =  courseService.onboardNewCourse(courseRequestDTO);
       return new ServiceResponse<>(HttpStatus.CREATED,newCourse); //201
    }

    @GetMapping()
    public ServiceResponse<List<CourseResponseDTO>> findAllCourse(){
        List<CourseResponseDTO> courseResponseDTOS = courseService.viewAllCourse();
        return new ServiceResponse<>(HttpStatus.OK, courseResponseDTOS);
    }

    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourse(@PathVariable  Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK, courseService.findByCourseId(courseId));
    }

    @GetMapping("/search/request")
    public ServiceResponse<CourseResponseDTO> findCourseUsingRequestParam(@RequestParam(required=false) Integer courseId){
        CourseResponseDTO responseDTO = courseService.findByCourseId(courseId);
        return new ServiceResponse<>(HttpStatus.OK, responseDTO);
    }

    @DeleteMapping("/{courseId")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId){
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO course){
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId,course);
        return new ServiceResponse<>(HttpStatus.OK, courseResponseDTO);
    }
}
