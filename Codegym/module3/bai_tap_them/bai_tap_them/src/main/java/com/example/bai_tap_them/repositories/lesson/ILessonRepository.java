package com.example.bai_tap_them.repositories.lesson;

import com.example.bai_tap_them.models.Lesson;

import java.util.List;

public interface ILessonRepository {
    List<Lesson> getAllLessons();
    Lesson getLessonById(int id);
    List<Lesson> searchLessons(String name, Integer courseId);
    List<Lesson> searchLessonsByName(String name);
    boolean addLesson(Lesson lesson);
    boolean updateLesson(Lesson lesson);
    boolean deleteLesson(int id);
}
