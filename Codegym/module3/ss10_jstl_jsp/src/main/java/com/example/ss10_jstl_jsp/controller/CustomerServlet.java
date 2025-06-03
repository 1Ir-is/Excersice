package com.example.ss10_jstl_jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "customerServlet", value = "/customer-list")
public class CustomerServlet extends HttpServlet {
    public static class Customer {
        private String name;
        private String birthDate;
        private String address;

        public Customer(String name, String birthDate, String address) {
            this.name = name;
            this.birthDate = birthDate;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public String getAddress() {
            return address;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("John Doe", "1990-01-01", "New York"));
        customers.add(new Customer("Jane Smith", "1985-05-15", "Los Angeles"));
        customers.add(new Customer("Alice Johnson", "1992-07-20", "Chicago"));

        request.setAttribute("customerList", customers);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}