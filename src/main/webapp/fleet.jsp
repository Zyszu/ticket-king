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
  <title>Title</title>
</head>
<body>
  <form:form method="post" action="/fleet" modelAttribute="aircraft">
    <form:hidden path="id" />

    <label for="model">Aircraft Name</label>
    <form:input path="model" class="input-field" />
    <form:errors path="model" />

    <button type="submit">Register Aircraft</button>
  </form:form>

  <thead>
    <tr>
      <th>Model</th>
      <th>ID</th>
      <!-- add more -->
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${aircraftList}" var="aircraft">
      <tr>
        <td>${aircraft.model}</td>
        <td>${aircraft.id}</td>
      </tr>
    </c:forEach>
  </tbody>


</body>
</html>
