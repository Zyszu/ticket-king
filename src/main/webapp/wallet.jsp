<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 29/03/2025
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Wallet</title>
</head>
<body>
    <c:forEach items="${ticketList}" var="ticket">
        <tr>
            <td>${ticket.id}</td>
        </tr>
    </c:forEach>

</body>
</html>
