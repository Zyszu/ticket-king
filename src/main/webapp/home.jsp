<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 26/03/2025
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello!</h1>
    <a href="/login">
        login
    </a>
    <br />
    <a href="/fleet">Register fleet</a>
    <br />
    <a href="/flights">Register flight!!!</a>

    <c:if test="${not empty flightList}">
        <h2>Existing Flights</h2>
        <table border="1">
            <thead>
            <tr>
                <th>ID</th>
                <th>Aircraft</th>
                <th>From</th>
                <th>To</th>
                <th>Seats</th>
                <th>Departure At</th>
                <th>Price per Seat</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${flightList}" var="flight">
                <tr>
                    <td>${flight.id}</td>
                    <td>${flight.aircraft.model}</td> <!-- assuming aircraft has a model -->
                    <td>${flight.airfieldFrom}</td>
                    <td>${flight.airfieldTo}</td>
                    <td>${flight.availableSeats}</td>
                    <td>${flight.departure}</td>
                    <td>${flight.pricePerTicket}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>


</body>
</html>
