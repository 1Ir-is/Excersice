package com.example.bai_tap_them.controllers;

import com.example.bai_tap_them.models.Course;
import com.example.bai_tap_them.models.Lesson;
import com.example.bai_tap_them.services.course.CourseService;
import com.example.bai_tap_them.services.course.ICourseService;
import com.example.bai_tap_them.services.lesson.ILessonService;
import com.example.bai_tap_them.services.lesson.LessonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lesson")
public class LessonServlet extends HttpServlet {
    private ILessonService lessonService;
    private ICourseService courseService;

    @Override
    public void init() {
        lessonService = new LessonService(new com.example.bai_tap_them.repositories.lesson.LessonRepository());
        courseService = new CourseService(new com.example.bai_tap_them.repositories.course.CourseRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                String search = request.getParameter("search");
                String courseIdParam = request.getParameter("courseId");
                Integer courseId = (courseIdParam != null && !courseIdParam.isEmpty()) ? Integer.parseInt(courseIdParam) : null;

                List<Lesson> lessons = lessonService.searchLessons(search, courseId);
                List<Course> courseList = courseService.getAllCourses();
                request.setAttribute("lessons", lessons);
                request.setAttribute("courses", courseList);
                request.getRequestDispatcher("/views/lesson/list.jsp").forward(request, response);
                break;
            case "view":
                int id = Integer.parseInt(request.getParameter("id"));
                Lesson lesson = lessonService.getLessonById(id);
                request.setAttribute("lesson", lesson);
                request.getRequestDispatcher("/views/lesson/view.jsp").forward(request, response);
                break;
            case "edit":
                int editId = Integer.parseInt(request.getParameter("id"));
                Lesson editLesson = lessonService.getLessonById(editId);
                List<Course> courseListForEdit = courseService.getAllCourses();
                request.setAttribute("lesson", editLesson);
                request.setAttribute("courses", courseListForEdit);
                request.getRequestDispatcher("/views/lesson/edit.jsp").forward(request, response);
                break;
            case "add":
                List<Course> courseListForAdd = courseService.getAllCourses();
                request.setAttribute("courses", courseListForAdd);
                request.getRequestDispatcher("/views/lesson/add.jsp").forward(request, response);
                break;
            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                boolean isDeleted = lessonService.deleteLesson(deleteId);
                if (isDeleted) {
                    response.sendRedirect("/lesson?action=list");
                } else {
                    request.setAttribute("errorMessage", "Xoa that bai");
                    request.getRequestDispatcher("/views/error.jsp").forward(request, response);
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                Lesson lesson = new Lesson();
                lesson.setLessonCode(request.getParameter("lessonCode"));
                lesson.setLessonName(request.getParameter("lessonName"));
                lesson.setLessonType(request.getParameter("lessonType"));
                lesson.setLessonDescription(request.getParameter("lessonDescription"));
                lesson.setCourseId(Integer.parseInt(request.getParameter("courseId")));

                lessonService.addLesson(lesson);
                response.sendRedirect("/lesson?action=list");
                break;
            case "update":
                Lesson updatedLesson = new Lesson();
                updatedLesson.setId(Integer.parseInt(request.getParameter("id")));
                updatedLesson.setLessonCode(request.getParameter("lessonCode"));
                updatedLesson.setLessonName(request.getParameter("lessonName"));
                updatedLesson.setLessonType(request.getParameter("lessonType"));
                updatedLesson.setLessonDescription(request.getParameter("lessonDescription"));
                updatedLesson.setCourseId(Integer.parseInt(request.getParameter("courseId")));

                lessonService.updateLesson(updatedLesson);
                response.sendRedirect("/lesson?action=list");
                break;
            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                boolean isDeleted = lessonService.deleteLesson(deleteId);
                if (isDeleted) {
                    response.sendRedirect("/lesson?action=list");
                } else {
                    request.setAttribute("errorMessage", "Xóa thất bại");
                    request.getRequestDispatcher("/views/error.jsp").forward(request, response);
                }
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}