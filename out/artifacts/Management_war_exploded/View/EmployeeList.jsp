<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestionnaire d'entreprise</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<body>
<div style="text-align: center;">
    <h1>Gestionnaire d'employé</h1>
    <h2>
        <a href="View/EmployeeForm.jsp">Ajouter employé</a>&nbsp;&nbsp;&nbsp;

    </h2>
</div>
<div align="center">
    <table class="table-striped table table-bordered table-hover" border="1" cellpadding="5">
        <caption><h2>List des employés</h2></caption>

        <tr>
            <th>Matricule</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Département</th>
        </tr>

        <c:forEach var="employee" items="${listEmployee}">
            <tr>
                <td>${employee.getMatricule()}</td>
                <td>${employee.getNom()}</td>
                <td>${employee.getPrénom()}</td>
                <td>${employee.getDépartement()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/EmployeeServlet?action=update&id=${employee.getMatricule()}">Modifier</a>
                    <a href="${pageContext.request.contextPath}/EmployeeServlet?action=delete&id=${employee.getMatricule()}">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
