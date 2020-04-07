<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Welcome page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<body>

&nbsp;&nbsp;&nbsp;

<div align="center">

  <h2>Bienvenue au gestionnaire de l'entreprise!</h2>

  <h3>  <a href="${pageContext.request.contextPath}/EmployeeServlet?action=list">Afficher tous les employés</a>
  </h3>


</div>
</body>
</html>
