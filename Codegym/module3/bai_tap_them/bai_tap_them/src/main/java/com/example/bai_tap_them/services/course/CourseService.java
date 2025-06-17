package com.example.bai_tap_them.services.course;

import com.example.bai_tap_them.models.Course;
import com.example.bai_tap_them.repositories.course.ICourseRepository;

import java.util.List;

public class CourseService implements ICourseService {
    private final ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    @Override
    public Course getCourseById(int id) {
        return courseRepository.getCourseById(id);
    }

    @Override
    public boolean addCourse(Course course) {
        return courseRepository.addCourse(course);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseRepository.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(int id) {
        return courseRepository.deleteCourse(id);
    }
}