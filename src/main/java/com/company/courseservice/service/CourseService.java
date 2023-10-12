package com.company.courseservice.service;

import com.company.courseservice.dao.CourseDao;
import com.company.courseservice.dto.Course;
import com.company.courseservice.dto.CourseRequestDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CourseService {
    private List<Course> courseDatabase = new ArrayList<>();


    // H2, DERBY, AeroSpike -> In memory Database
    private CourseDao courseDao;

    //create course object in DB
    public Course onboardNewCourse(CourseRequestDTO courseRequestDTO){
        courseRequestDTO.setCourseId(new Random().nextInt(3756));
        courseDatabase.add(courseRequestDTO);
        return course;
    }

    // load all the course from database
    public List<Course> viewAllCourse(){
        return courseDatabase;
    }

    // filter course by courseID
    public Course findByCourseId(Integer courseId){
        return courseDatabase.stream().filter(course -> course.getCourseId()==courseId).findFirst().orElse(null);
    }

    //delete course
    public void deleteCourse(int courseId){
        Course course =  findByCourseId(courseId);
        courseDatabase.remove(course);
    }

    //update course
    public Course updateCourse(int courseId, Course course){
        Course existingCourse = findByCourseId(courseId);
        courseDatabase.set(courseDatabase.indexOf(existingCourse),course);
        return course;
    }
}
