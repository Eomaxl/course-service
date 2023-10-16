package com.company.courseservice.service;

import com.company.courseservice.dao.CourseDao;
import com.company.courseservice.dto.Course;
import com.company.courseservice.dto.CourseRequestDTO;
import com.company.courseservice.dto.CourseResponseDTO;
import com.company.courseservice.entity.CourseEntity;
import com.company.courseservice.util.AppUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseService {
    private List<Course> courseDatabase = new ArrayList<>();


    // H2, DERBY, AeroSpike -> In memory Database
    private CourseDao courseDao;

    //create course object in DB  -> POST
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO){
        // convert DTO to ENTITY
        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
        CourseEntity entity = courseDao.save(courseEntity);
        // CONVERT Entity -> RESPONSE DTO
         CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(entity);
         courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
        return courseResponseDTO;
    }

    // load all the course from database
    public List<CourseResponseDTO> viewAllCourse(){
        Iterable<CourseEntity> courseEntities = courseDao.findAll();
        return StreamSupport.stream(courseEntities.spliterator(),false).map(AppUtils::mapEntityToDTO).collect(Collectors.toList());
    }

    // filter course by courseID
    public CourseResponseDTO findByCourseId(Integer courseId){
        CourseEntity courseEntity = courseDao.findById(courseId).orElseThrow(() -> new RuntimeException(courseId+" not valid"));
        return AppUtils.mapEntityToDTO(courseEntity);
    }

    //delete course
    public void deleteCourse(int courseId){
        courseDao.deleteById(courseId);
    }

    //update course
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO){
        // get the existing object
        CourseEntity existingCourseEntity = courseDao.findById(courseId).orElse(null);
        // modify the existing object with the new value
        existingCourseEntity.setName(courseRequestDTO.getName());
        existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
        existingCourseEntity.setDuration(courseRequestDTO.getDuration());
        existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
        existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
        existingCourseEntity.setFees(courseRequestDTO.getFees());
        existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        existingCourseEntity.setDescription(courseRequestDTO.getDescription());
        // save modified value
        CourseEntity updatedCourseEntity = courseDao.save(existingCourseEntity);
        return AppUtils.mapEntityToDTO(updatedCourseEntity);
    }
}
