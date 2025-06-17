package controller;

import model.Category;
import service.category.ICategoryService;
import service.category.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

    private final ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                // thường xóa dùng POST nhưng nếu cần GET xóa thì gọi deleteCategory ở đây
                deleteCategory(request, response);
                break;
            case "list":
            default:
                listCategories(request, response);
                break;
        }
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/views/category/list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/category/create.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect(request.getContextPath() + "/categories");
            return;
        }
        int id = Integer.parseInt(idStr);
        Category category = categoryService.findById(id);
        if (category == null) {
            response.sendRedirect(request.getContextPath() + "/categories");
            return;
        }
        request.setAttribute("category", category);
        request.getRequestDispatcher("/views/category/edit.jsp").forward(request, response);
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            categoryService.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/categories");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                insertCategory(request, response);
                break;
            case "edit":
                updateCategory(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/categories");
        }
    }

    private void insertCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "Tên danh mục không được để trống");
            request.getRequestDispatcher("/views/category/create.jsp").forward(request, response);
            return;
        }
        Category category = new Category();
        category.setName(name);
        categoryService.save(category);
        response.sendRedirect(request.getContextPath() + "/categories");
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        if (idStr == null || name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "Dữ liệu không hợp lệ");
            showEditForm(request, response);
            return;
        }
        int id = Integer.parseInt(idStr);
        Category category = categoryService.findById(id);
        if (category == null) {
            response.sendRedirect(request.getContextPath() + "/categories");
            return;
        }
        category.setName(name);
        categoryService.update(category);
        response.sendRedirect(request.getContextPath() + "/categories");
    }
}
