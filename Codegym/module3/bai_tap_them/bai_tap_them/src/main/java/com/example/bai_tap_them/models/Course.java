package com.example.bai_tap_them.models;

public class Course {
    private int id;
    private String courseName;
    private String courseLanguage;

    public Course() {}

    public Course(int id, String courseName, String courseLanguage) {
        this.id = id;
        this.courseName = courseName;
        this.courseLanguage = courseLanguage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLanguage() {
        return courseLanguage;
    }

    public void setCourseLanguage(String courseLanguage) {
        this.courseLanguage = courseLanguage;
    }
}
