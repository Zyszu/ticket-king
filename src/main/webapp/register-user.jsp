<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 27/03/2025
  Time: 00:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Register user</title>
</head>
<body>
  <form:form method="post" action="register-user" modelAttribute="user">
    <form:hidden path="id" />

    <!-- Email -->
    <label for="email">Email</label>
    <form:input path="email" class="input-field" />
    <form:errors path="email" />

    <!-- Password -->
    <label for="password">Password</label>
    <form:password path="password" class="input-field" />
    <form:errors path="password" />

    <button type="submit">Register</button>
  </form:form>


</body>
</html>
