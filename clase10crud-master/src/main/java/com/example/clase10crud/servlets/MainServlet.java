package com.example.clase10crud.servlets;

import com.example.clase10crud.daos.JobDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MainServlet", value = "")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JobDao jobDao = new JobDao();
        int maxSalary = jobDao.getMaxSalary();

        String vista = "index.jsp";
        request.setAttribute("maxSalary",maxSalary);
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}