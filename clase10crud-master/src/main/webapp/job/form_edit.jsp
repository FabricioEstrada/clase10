<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="job" type="com.example.clase10crud.beans.Job" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
        <title>Editar un trabajo</title>
    </head>
    <body>
        <div class='container'>
            <h1 class='mb-3'>Editar un trabajo</h1>
            <form method="post" action="<%=request.getContextPath()%>/JobServlet?action=e">
                <div class="mb-3">
                    <input type="hidden" class="form-control" name="jobId" value="<%=job.getJobId()%>">
                </div>
                <div class="mb-3">
                    <label>Job Title</label>
                    <input type="text" class="form-control" name="jobTitle" value="<%=job.getJobTitle()%>">
                </div>
                <div class="mb-3">
                    <label>Min Salary</label>
                    <input type="text" class="form-control" name="minSalary" value="<%=job.getMinSalary()%>">
                </div>
                <div class="mb-3">
                    <label>Max Salary</label>
                    <input type="text" class="form-control" name="maxSalary" value="<%=job.getMaxSalary()%>">
                </div>
                <a href="<%=request.getContextPath()%>/JobServlet" class="btn btn-danger">Regresar</a>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
    </body>
</html>

