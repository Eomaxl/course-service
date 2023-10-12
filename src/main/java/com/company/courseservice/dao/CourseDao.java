package com.company.courseservice.dao;

import com.company.courseservice.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<CourseEntity,Integer> {
}
