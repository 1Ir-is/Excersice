package com.example.bai_tap_them.services.lesson;

import com.example.bai_tap_them.models.Lesson;
import com.example.bai_tap_them.repositories.lesson.ILessonRepository;

import java.util.List;

public class LessonService implements ILessonService {
    private final ILessonRepository lessonRepository;

    public LessonService(ILessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.getAllLessons();
    }

    @Override
    public Lesson getLessonById(int id) {
        return lessonRepository.getLessonById(id);
    }

    @Override
    public boolean addLesson(Lesson lesson) {
        return lessonRepository.addLesson(lesson);
    }

    @Override
    public boolean updateLesson(Lesson lesson) {
        return lessonRepository.updateLesson(lesson);
    }

    @Override
    public boolean deleteLesson(int id) {
        return lessonRepository.deleteLesson(id);
    }

    @Override
    public List<Lesson> searchLessonsByName(String name) {
        return lessonRepository.searchLessonsByName(name);
    }

    @Override
    public List<Lesson> searchLessons(String name, Integer courseId) {
        return lessonRepository.searchLessons(name, courseId);
    }
}