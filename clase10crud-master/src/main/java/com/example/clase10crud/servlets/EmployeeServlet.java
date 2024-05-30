package com.example.clase10crud.servlets;

import com.example.clase10crud.beans.Employee;
import com.example.clase10crud.daos.EmployeeDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

//  http://localhost:8080/EmployeeServlet
@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        EmployeeDao employeeDao = new EmployeeDao();

        switch (action){
            case "lista":
                ArrayList<Employee> list = employeeDao.listar();

                request.setAttribute("lista", list);
                RequestDispatcher rd = request.getRequestDispatcher("employee/listaemp.jsp");
                rd.forward(request, response);
                break;
            case "new":
                request.getRequestDispatcher("employee/form_new.jsp").forward(request, response);
                break;
            case "edit":
                String id = request.getParameter("id");
                Employee employee = employeeDao.buscarPorId(Integer.parseInt(id));

                if (employee != null) {
                    request.setAttribute("employee", employee);
                    request.getRequestDispatcher("employee/form_edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                }
                break;
            case "del":
                String idd = request.getParameter("id");
                Employee employeeToDelete = employeeDao.buscarPorId(Integer.parseInt(idd));

                if (employeeToDelete != null) {
                    try {
                        employeeDao.borrar(Integer.parseInt(idd));
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        EmployeeDao employeeDao = new EmployeeDao();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action){
            case "crear":
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String birthDate = request.getParameter("birthDate");
                String hireDate = request.getParameter("hireDate");
                String gender = request.getParameter("gender");

                boolean isAllValid = true;

                if (firstName.length() > 35 || lastName.length() > 35) {
                    isAllValid = false;
                }

                if (isAllValid) {
                    Employee employee = new Employee();
                    employee.setFirst_name(firstName);
                    employee.setLast_name(lastName);
                    employee.setBirth_date(java.sql.Date.valueOf(birthDate));
                    employee.setHire_date(java.sql.Date.valueOf(hireDate));
                    employee.setGender(gender.equals("M"));

                    employeeDao.crear(employee);
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                } else {
                    request.getRequestDispatcher("employee/form_new.jsp").forward(request, response);
                }
                break;
            case "e":
                int empNo = Integer.parseInt(request.getParameter("empNo"));
                String firstName2 = request.getParameter("firstName");
                String lastName2 = request.getParameter("lastName");
                String birthDate2 = request.getParameter("birthDate");
                String hireDate2 = request.getParameter("hireDate");
                String gender2 = request.getParameter("gender");

                boolean isAllValid2 = true;

                if (firstName2.length() > 35 || lastName2.length() > 35) {
                    isAllValid2 = false;
                }

                if (isAllValid2) {
                    Employee employee = new Employee();
                    employee.setEmp_no(empNo);
                    employee.setFirst_name(firstName2);
                    employee.setLast_name(lastName2);
                    employee.setBirth_date(java.sql.Date.valueOf(birthDate2));
                    employee.setHire_date(java.sql.Date.valueOf(hireDate2));
                    employee.setGender(gender2.equals("M"));

                    employeeDao.actualizar(employee);
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                } else {
                    request.setAttribute("employee", employeeDao.buscarPorId(empNo));
                    request.getRequestDispatcher("employee/form_edit.jsp").forward(request, response);
                }
                break;
            case "s":
                String textBuscar = request.getParameter("textoBuscar");
                ArrayList<Employee> lista = employeeDao.buscarIdOrTitle(textBuscar);

                request.setAttribute("lista", lista);
                request.setAttribute("busqueda", textBuscar);
                request.getRequestDispatcher("employee/listaemp.jsp").forward(request, response);
                break;
        }
    }
}
