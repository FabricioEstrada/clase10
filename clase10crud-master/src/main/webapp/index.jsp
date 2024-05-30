<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    int maxSalary = (int) request.getAttribute("maxSalary");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <h1><%= "Hello World!" %>
        </h1>
        <br/>
        <a href="hello-servlet">Hello Servlet</a>
        <h1>El salario máximo de este día desde base de datos es: <%=maxSalary%></h1>
    </body>
</html>