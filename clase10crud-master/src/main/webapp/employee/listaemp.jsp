
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.clase10crud.beans.Job" %>
<%@ page import="com.example.clase10crud.beans.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" scope="request" type="ArrayList<com.example.clase10crud.beans.Employee>" />
<html>
<head>
    <title>Trabajos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="clearfix mt-3 mt-2">
        <a href="<%=request.getContextPath()%>/EmployeeServlet">
            <h1 class="float-start link-dark">Lista de Trabajos</h1>
        </a>
        <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/EmployeeServlet?action=new">Crear Empleado</a>
    </div>
    <hr/>
    <form method="post" action="<%=request.getContextPath()%>/EmployeeServlet?action=s">
        <div class="form-floating mt-3">
            <input type="text" class="form-control" id="floatingInput"
                   placeholder="Por ID o por nombre" name="textoBuscar" value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : "" %>">
            <label for="floatingInput">Buscar Empleado</label>
        </div>
    </form>
    <table class="table table-striped mt-3">
        <tr class="table-primary">
            <th>Emp_no</th>
            <th>Birth Date</th>
            <th>First Name</th>
            <th>Gender</th>
            <th>Hire Date</th>
            <th></th>
            <th></th>
        </tr>
        <% for (Employee employee : lista) { %>
        <tr>
            <td><%=employee.getEmp_no()  %>
            </td>
            <td><%=employee.getBirth_date()%>
            </td>
            <td><%=employee.getFirst_name()%>
            </td>
            <td><%=employee.getGender()%>
            </td>
            <td><%=employee.getHire_date()%>
            </td>
            <td><a class="btn btn-success" href="<%=request.getContextPath()%>/JobServlet?action=edit&id=<%= employee.getEmp_no() %>">Editar</a></td>
            <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/JobServlet?action=del&id=<%= employee.getEmp_no() %>">Borrar</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
