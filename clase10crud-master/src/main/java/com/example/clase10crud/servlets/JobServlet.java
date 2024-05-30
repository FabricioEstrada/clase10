package com.example.clase10crud.servlets;

import com.example.clase10crud.beans.Job;
import com.example.clase10crud.daos.JobDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "JobServlet", value = "/JobServlet")
public class JobServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        JobDao jobDao = new JobDao();

        switch (action){
            case "lista":
                //saca del modelo
                ArrayList<Job> list = jobDao.listar();

                //mandar la lista a la vista -> job/listaemp.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("job/listaemp.jsp");
                rd.forward(request,response);
                break;
            case "new":
                request.getRequestDispatcher("job/form_new.jsp").forward(request,response);
                break;
            case "edit":
                String id = request.getParameter("id");
                Job job = jobDao.buscarPorId(id);

                if(job != null){
                    request.setAttribute("job",job);
                    request.getRequestDispatcher("job/form_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                }
                break;
            case "del":
                String idd = request.getParameter("id");
                Job jobb = jobDao.buscarPorId(idd);

                if(jobb != null){
                    try {
                        jobDao.borrar(idd);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/JobServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");


        JobDao jobDao = new JobDao();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action){
            case "crear"://voy a crear un nuevo trabajo
                String jobId = request.getParameter("jobId");
                String jobTitle = request.getParameter("jobTitle");
                String minSalary = request.getParameter("minSalary");
                String maxSalary = request.getParameter("maxSalary");

                boolean isAllValid = true;

                if(jobTitle.length() > 35){
                    isAllValid = false;
                }

                if(jobId.length() > 10){
                    isAllValid = false;
                }

                if(isAllValid){

                    Job job = jobDao.buscarPorId(jobId);

                    if(job == null){
                        jobDao.crear(jobId,jobTitle,Integer.parseInt(minSalary),Integer.parseInt(maxSalary));
                        response.sendRedirect(request.getContextPath() + "/JobServlet");
                    }else{
                        request.getRequestDispatcher("job/form_new.jsp").forward(request,response);
                    }
                }else{
                    request.getRequestDispatcher("job/form_new.jsp").forward(request,response);
                }
                break;
            case "e": //voy a actualizar
                String jobId2 = request.getParameter("jobId");
                String jobTitle2 = request.getParameter("jobTitle");
                String minSalary2 = request.getParameter("minSalary");
                String maxSalary2 = request.getParameter("maxSalary");

                boolean isAllValid2 = true;

                if(jobTitle2.length() > 35){
                    isAllValid2 = false;
                }

                if(jobId2.length() > 10){
                    isAllValid2 = false;
                }
                if(isAllValid2){
                    Job job = new Job();
                    job.setJobId(jobId2);
                    job.setJobTitle(jobTitle2);
                    job.setMinSalary(Integer.parseInt(minSalary2));
                    job.setMaxSalary(Integer.parseInt(maxSalary2));

                    jobDao.actualizar(job);
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                }else{
                    request.setAttribute("job",jobDao.buscarPorId(jobId2));
                    request.getRequestDispatcher("job/form_edit.jsp").forward(request,response);
                }
                break;
            case "s":
                String textBuscar = request.getParameter("textoBuscar");
                ArrayList<Job> lista = jobDao.buscarIdOrTitle(textBuscar);

                request.setAttribute("lista",lista);
                request.setAttribute("busqueda",textBuscar);
                request.getRequestDispatcher("job/listaemp.jsp").forward(request,response);

                break;
        }
    }
}
