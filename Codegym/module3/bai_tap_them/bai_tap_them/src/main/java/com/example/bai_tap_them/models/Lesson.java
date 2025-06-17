package com.example.bai_tap_them.models;

public class Lesson {
    private int id;
    private String lessonCode;
    private String lessonName;
    private String lessonType;
    private String lessonDescription;
    private int courseId;
    private String courseName;

    public Lesson() {}

    public Lesson(int id, String lessonCode, String lessonName, String lessonType, String lessonDescription, int courseId) {
        this.id = id;
        this.lessonCode = lessonCode;
        this.lessonName = lessonName;
        this.lessonType = lessonType;
        this.lessonDescription = lessonDescription;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonCode() {
        return lessonCode;
    }

    public void setLessonCode(String lessonCode) {
        this.lessonCode = lessonCode;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
