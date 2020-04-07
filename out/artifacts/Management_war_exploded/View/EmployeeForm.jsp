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

        <a href="${pageContext.request.contextPath}/EmployeeServlet?action=list">Afficher tous les employés</a>

    </h2>
</div>
<div align="center">

        <form action="${pageContext.request.contextPath}/EmployeeServlet?action=list"method="POST">

            <div class="form-group">

            <table class="table table-striped table-bordered" >

                    <input type="hidden" name="matricule" value=<%= request.getParameter("id") %> />
                <tr>
                    <th>Nom: </th>
                    <td>
                        <input class="form-control" type="text" name="Nom" size="45" value="${employee.nom}" />
                    </td>
                </tr>
                <tr>
                    <th>Prénom: </th>
                    <td>
                        <input class="form-control" type="text" name="Prénom" size="45"  value="${employee.prénom}" />
                    </td>
                </tr>
                <tr>
                    <th>Département: </th>
                    <td>
                        <input class="form-control" type="number" name="Département" size="5"   value="${employee.département}" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <button class="btn btn-lg btn-primary" type="submit">Valider</button>

                    </td>
                </tr>
            </table>
            </div>

        </form>
</div>
</body>
</html>