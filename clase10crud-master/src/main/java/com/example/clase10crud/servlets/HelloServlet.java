package com.example.clase10crud.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// [protocolo+socket]    / [proyecto] / [ruta]
// http://localhost:8080 /            / hello-servlet
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    //solo para el método GET
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hola mundo</h1>");
        out.println("<h2>Hola Alex</h2>");
        out.println("</body></html>");
    }

}