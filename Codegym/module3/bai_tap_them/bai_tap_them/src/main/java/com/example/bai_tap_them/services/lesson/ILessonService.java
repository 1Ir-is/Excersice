package com.example.bai_tap_them.services.lesson;

import com.example.bai_tap_them.models.Lesson;
import java.util.List;

public interface ILessonService {
    List<Lesson> getAllLessons();
    Lesson getLessonById(int id);
    boolean addLesson(Lesson lesson);
    boolean updateLesson(Lesson lesson);
    boolean deleteLesson(int id);
    List<Lesson> searchLessonsByName(String name);
    List<Lesson> searchLessons(String name, Integer courseId);
}