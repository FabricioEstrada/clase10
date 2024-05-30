package com.example.clase10crud.daos;

import com.example.clase10crud.beans.Employee;
import com.example.clase10crud.beans.Job;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao {
    public ArrayList<Employee> listar(){

        ArrayList<Employee> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from employees";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmp_no(rs.getInt(1));
                employee.setBirth_date(rs.getDate(2));
                employee.setFirst_name(rs.getString(3));
                employee.setLast_name(rs.getString(4));
                employee.setGender(rs.getBoolean(5));
                employee.setHire_date(rs.getDate(6));
                lista.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
    public void crear(Date birth, String first, String last, boolean gender, Date hire){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "insert into jobs (birth_date, first_name,last_name,gender,hire_date) values (?,?,?,?,?)";
        String sql2= "select max(emp_no) from employees";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql);PreparedStatement pstmt2 = connection.prepareStatement(sql2)){
            int id;

            pstmt2.setInt(1,id);
            pstmt.setDate(1,birth);
            pstmt.setString(2,first);
            pstmt.setString(3,last);
            pstmt.setBoolean(4,gender);
            pstmt.setDate(5,hire);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
