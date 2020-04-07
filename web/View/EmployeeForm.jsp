<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gestionnaire d'entreprise</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Gestionnaire d'employé</h1>
    <h2>

        <a href="${pageContext.request.contextPath}/EmployeeServlet?action=list">Afficher tous les employés</a>

    </h2>
</div>
<div align="center">

        <form action="${pageContext.request.contextPath}/EmployeeServlet?action=insert"method="POST">


            <table border="1" cellpadding="5">
                <caption>
                    <h2>

                    </h2>
                </caption>
                    <input type="hidden" name="matricule"  value="${employee.matricule}" />
                <tr>
                    <th>Nom: </th>
                    <td>
                        <input type="text" name="Nom" size="45" value="${employee.nom}" />
                    </td>
                </tr>
                <tr>
                    <th>Prénom: </th>
                    <td>
                        <input type="text" name="Prénom" size="45"  value="${employee.prénom}" />
                    </td>
                </tr>
                <tr>
                    <th>Département: </th>
                    <td>
                        <input type="text" name="Département" size="5"   value="${employee.département}" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>