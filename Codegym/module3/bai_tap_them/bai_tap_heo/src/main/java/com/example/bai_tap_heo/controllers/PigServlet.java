package com.example.bai_tap_heo.controllers;

import com.example.bai_tap_heo.models.Pig;
import com.example.bai_tap_heo.models.Origin;
import com.example.bai_tap_heo.services.pig.IPigService;
import com.example.bai_tap_heo.services.pig.PigService;
import com.example.bai_tap_heo.repositories.pig.PigRepository;
import com.example.bai_tap_heo.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/pig")
public class PigServlet extends HttpServlet {
    private IPigService pigService;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = JDBCUtils.getConnection();
            pigService = new PigService(new PigRepository(connection));
        } catch (Exception e) {
            throw new ServletException("Failed to initialize PigService due to database connection error.", e);
        }
    }

    private void setOriginsAttribute(HttpServletRequest request) {
        List<Origin> origins = pigService.getAllOrigins();
        request.setAttribute("origins", origins);
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
                String statusParam = request.getParameter("status");
                String originIdParam = request.getParameter("originId");
                String pidNumber = request.getParameter("pidNumber");

                Boolean sold = null;
                Integer originId = null;

                if ("sold".equalsIgnoreCase(statusParam)) {
                    sold = true;
                } else if ("unsold".equalsIgnoreCase(statusParam)) {
                    sold = false;
                }


                if (originIdParam != null && !originIdParam.isEmpty()) {
                    try {
                        originId = Integer.parseInt(originIdParam);
                    } catch (NumberFormatException ignored) {}
                }

                List<Pig> pigs = pigService.searchPigs(sold, pidNumber, originId);
                setOriginsAttribute(request);

                request.setAttribute("pigs", pigs);
                request.setAttribute("selectedStatus", statusParam);
                request.setAttribute("selectedOriginId", originId);
                request.setAttribute("searchedPidNumber", pidNumber);
                request.getRequestDispatcher("/views/pig/list.jsp").forward(request, response);
                break;

            case "view":
                int id = Integer.parseInt(request.getParameter("id"));
                Pig pig = pigService.getPigById(id);
                request.setAttribute("pig", pig);
                request.getRequestDispatcher("/views/pig/view.jsp").forward(request, response);
                break;

            case "edit":
                int editId = Integer.parseInt(request.getParameter("id"));
                Pig editPig = pigService.getPigById(editId);
                setOriginsAttribute(request);
                request.setAttribute("pig", editPig);
                request.getRequestDispatcher("/views/pig/edit.jsp").forward(request, response);
                break;

            case "add":
                setOriginsAttribute(request);
                request.getRequestDispatcher("/views/pig/add.jsp").forward(request, response);
                break;

            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                pigService.deletePig(deleteId);
                response.sendRedirect("/pig?action=list");
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
                Pig pig = new Pig();
                pig.setPidNumber(request.getParameter("pidNumber"));
                pig.setEntryDate(LocalDate.parse(request.getParameter("entryDate")));
                pig.setEntryWeight(Double.parseDouble(request.getParameter("entryWeight")));
                pig.setExitDate(request.getParameter("exitDate") != null && !request.getParameter("exitDate").isEmpty()
                        ? LocalDate.parse(request.getParameter("exitDate")) : null);
                pig.setExitWeight(request.getParameter("exitWeight") != null && !request.getParameter("exitWeight").isEmpty()
                        ? Double.parseDouble(request.getParameter("exitWeight")) : pig.getEntryWeight());
                pig.setOrigin(new Origin(Integer.parseInt(request.getParameter("originId")), null));
                pig.setSold("true".equalsIgnoreCase(request.getParameter("sold")));

                pigService.addPig(pig);
                response.sendRedirect("/pig?action=list");
                break;

            case "update":
                Pig updatedPig = new Pig();
                updatedPig.setId(Integer.parseInt(request.getParameter("id")));
                updatedPig.setPidNumber(request.getParameter("pidNumber"));
                updatedPig.setEntryDate(LocalDate.parse(request.getParameter("entryDate")));
                updatedPig.setEntryWeight(Double.parseDouble(request.getParameter("entryWeight")));
                updatedPig.setExitDate(request.getParameter("exitDate") != null && !request.getParameter("exitDate").isEmpty()
                        ? LocalDate.parse(request.getParameter("exitDate")) : null);
                updatedPig.setExitWeight(request.getParameter("exitWeight") != null && !request.getParameter("exitWeight").isEmpty()
                        ? Double.parseDouble(request.getParameter("exitWeight")) : updatedPig.getEntryeight());
                updatedPig.setOrigin(new Origin(Integer.parseInt(request.getParameter("originId")), null));

                String soldParam = request.getParameter("sold");
                boolean sold = "true".equalsIgnoreCase(soldParam);
                updatedPig.setSold(sold);

                pigService.updatePig(updatedPig);
                response.sendRedirect("/pig?action=list");
                break;

            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("id"));
                pigService.deletePig(deleteId);
                response.sendRedirect("/pig?action=list");
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
