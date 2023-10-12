package com.company.courseservice.util;

import com.company.courseservice.dto.CourseRequestDTO;
import com.company.courseservice.entity.CourseEntity;

public class AppUtils {
    public CourseEntity mapDTOToEntity(CourseRequestDTO courseRequestDTO){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseId(courseEntity.getCourseId());
    }
}
