package com.example.bai_tap_them.services.course;

import com.example.bai_tap_them.models.Course;
import java.util.List;

public interface ICourseService {
    List<Course> getAllCourses();
    Course getCourseById(int id);
    boolean addCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(int id);
}