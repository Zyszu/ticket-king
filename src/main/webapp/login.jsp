<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 27/03/2025
  Time: 00:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
  <form name='loginForm' action="login" method='POST'>
    <input type="text" name="login" class="input-field" placeholder="email" required />
    <input type="password" name="password" class="input-field" placeholder="password" required />
    <button type="submit" class="btn-primary">Login</button>

    <!-- CSRF Token -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  </form>

</body>
</html>
