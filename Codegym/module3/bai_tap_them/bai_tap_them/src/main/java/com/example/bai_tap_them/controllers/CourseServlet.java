package com.example.bai_tap_them.controllers;

import com.example.bai_tap_them.models.Course;
import com.example.bai_tap_them.services.course.ICourseService;
import com.example.bai_tap_them.services.course.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {
    private ICourseService courseService;

    @Override
    public void init() {
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
                List<Course> courses = courseService.getAllCourses();
                request.setAttribute("courses", courses);
                request.getRequestDispatcher("/views/course/list.jsp").forward(request, response);
                break;
            case "view":
                int id = Integer.parseInt(request.getParameter("id"));
                Course course = courseService.getCourseById(id);
                request.setAttribute("course", course);
                request.getRequestDispatcher("/views/course/view.jsp").forward(request, response);
                break;
            case "edit":
                int editId = Integer.parseInt(request.getParameter("id"));
                Course editCourse = courseService.getCourseById(editId);
                request.setAttribute("course", editCourse);
                request.getRequestDispatcher("/views/course/edit.jsp").forward(request, response);
                break;
            case "add":
                request.getRequestDispatcher("/views/course/add.jsp").forward(request, response);
                break;
            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                courseService.deleteCourse(deleteId);
                response.sendRedirect("/course?action=list");
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
                Course course = new Course();
                course.setCourseName(request.getParameter("courseName"));
                course.setCourseLanguage(request.getParameter("courseLanguage"));

                if (courseService.addCourse(course)) {
                    response.sendRedirect("/course?action=list");
                } else {
                    request.setAttribute("error", "Failed to add course.");
                    request.getRequestDispatcher("/views/course/add.jsp").forward(request, response);
                }
                break;
            case "update":
                Course updatedCourse = new Course();
                updatedCourse.setId(Integer.parseInt(request.getParameter("id")));
                updatedCourse.setCourseName(request.getParameter("courseName"));
                updatedCourse.setCourseLanguage(request.getParameter("courseLanguage"));

                if (courseService.updateCourse(updatedCourse)) {
                    response.sendRedirect("/course?action=list");
                } else {
                    request.setAttribute("error", "Failed to update course.");
                    request.getRequestDispatcher("/views/course/edit.jsp").forward(request, response);
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}