package com.example.bai_tap_them.repositories.lesson;

import com.example.bai_tap_them.models.Lesson;
import com.example.bai_tap_them.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LessonRepository implements ILessonRepository {

    @Override
    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        String sql = "SELECT * FROM lesson";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                lessons.add(new Lesson(
                        resultSet.getInt("id"),
                        resultSet.getString("lesson_code"),
                        resultSet.getString("lesson_name"),
                        resultSet.getString("lesson_type"),
                        resultSet.getString("lesson_description"),
                        resultSet.getInt("course_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    @Override
    public Lesson getLessonById(int id) {
        String sql = "SELECT * FROM lesson WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Lesson(
                            resultSet.getInt("id"),
                            resultSet.getString("lesson_code"),
                            resultSet.getString("lesson_name"),
                            resultSet.getString("lesson_type"),
                            resultSet.getString("lesson_description"),
                            resultSet.getInt("course_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addLesson(Lesson lesson) {
        String[] validLessonTypes = {"Nghe", "Đọc", "Nói", "Viết"};
        boolean isValidType = Arrays.asList(validLessonTypes).contains(lesson.getLessonType());
        if (!isValidType) {
            throw new IllegalArgumentException("Invalid lesson type: " + lesson.getLessonType());
        }

        String sql = "INSERT INTO lesson (lesson_code, lesson_name, lesson_type, lesson_description, course_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lesson.getLessonCode());
            preparedStatement.setString(2, lesson.getLessonName());
            preparedStatement.setString(3, lesson.getLessonType());
            preparedStatement.setString(4, lesson.getLessonDescription());
            preparedStatement.setInt(5, lesson.getCourseId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding lesson to database: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean updateLesson(Lesson lesson) {
        String sql = "UPDATE lesson SET lesson_code = ?, lesson_name = ?, lesson_type = ?, lesson_description = ?, course_id = ? WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lesson.getLessonCode());
            preparedStatement.setString(2, lesson.getLessonName());
            preparedStatement.setString(3, lesson.getLessonType());
            preparedStatement.setString(4, lesson.getLessonDescription());
            preparedStatement.setInt(5, lesson.getCourseId());
            preparedStatement.setInt(6, lesson.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteLesson(int id) {
        String sql = "DELETE FROM lesson WHERE id = ?";
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Lesson> searchLessonsByName(String name) {
        String sql = "SELECT * FROM lesson WHERE lesson_name LIKE ?";
        List<Lesson> lessons = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setLessonCode(resultSet.getString("lesson_code"));
                lesson.setLessonName(resultSet.getString("lesson_name"));
                lesson.setLessonType(resultSet.getString("lesson_type"));
                lesson.setLessonDescription(resultSet.getString("lesson_description"));
                lesson.setCourseId(resultSet.getInt("course_id"));
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    @Override
    public List<Lesson> searchLessons(String name, Integer courseId) {
        StringBuilder sql = new StringBuilder("SELECT l.*, c.course_name FROM lesson l JOIN courses c ON l.course_id = c.id WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            sql.append(" AND l.lesson_name LIKE ?");
            params.add("%" + name + "%");
        }
        if (courseId != null) {
            sql.append(" AND l.course_id = ?");
            params.add(courseId);
        }

        List<Lesson> lessons = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setLessonCode(resultSet.getString("lesson_code"));
                lesson.setLessonName(resultSet.getString("lesson_name"));
                lesson.setLessonType(resultSet.getString("lesson_type"));
                lesson.setLessonDescription(resultSet.getString("lesson_description"));
                lesson.setCourseId(resultSet.getInt("course_id"));
                lesson.setCourseName(resultSet.getString("course_name"));
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

}