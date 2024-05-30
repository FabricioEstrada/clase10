package com.example.clase10crud.daos;


import com.example.clase10crud.beans.Job;

import java.sql.*;
import java.util.ArrayList;

public class JobDao {

    public ArrayList<Job> listar(){

        ArrayList<Job> lista = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from jobs";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getString(1));
                job.setJobTitle(rs.getString(2));
                job.setMinSalary(rs.getInt(3));
                job.setMaxSalary(rs.getInt(4));

                lista.add(job);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public int getMaxSalary(){

        int salario = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select max(max_salary) from jobs";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                salario = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return salario;
    }

    public void crear(String jobId, String jobTitle, int minSalary, int maxSalary){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "insert into jobs (job_id, job_title, min_salary,max_salary) values (?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,jobId);
            pstmt.setString(2,jobTitle);
            pstmt.setInt(3,minSalary);
            pstmt.setInt(4,maxSalary);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Job buscarPorId(String id){

        Job job = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from jobs where job_id = ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    job = new Job();
                    job.setJobId(rs.getString(1));
                    job.setJobTitle(rs.getString(2));
                    job.setMinSalary(rs.getInt(3));
                    job.setMaxSalary(rs.getInt(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return job;
    }

    public void actualizar(Job job){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "update jobs set job_title = ?, min_salary = ?, max_salary = ? where job_id = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,job.getJobTitle());
            pstmt.setInt(2,job.getMinSalary());
            pstmt.setInt(3,job.getMaxSalary());
            pstmt.setString(4,job.getJobId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrar(String jobId) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "delete from jobs where job_id = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,jobId);
            pstmt.executeUpdate();

        }
    }

    public ArrayList<Job> buscarIdOrTitle(String name){

        ArrayList<Job> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from jobs where job_id = ? or lower(job_title) like lower(?);";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,name);
            pstmt.setString(2,"%" + name + "%");

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Job job = new Job();
                    job.setJobId(rs.getString(1));
                    job.setJobTitle(rs.getString(2));
                    job.setMinSalary(rs.getInt(3));
                    job.setMaxSalary(rs.getInt(4));

                    lista.add(job);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}
