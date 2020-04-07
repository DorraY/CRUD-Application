<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dorra
  Date: 26/03/2020
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Welcome page</title>
</head>
<body>

&nbsp;&nbsp;&nbsp;

<div align="center">
  <h2>Bienvenue au gestionnaire de l'entreprise!</h2>
  <a href="${pageContext.request.contextPath}/EmployeeServlet?action=list">Afficher tous les employés</a>

</div>
</body>
</html>
