<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 29/03/2025
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Purchase tickets | from ${flight.airfieldFrom} -> ${flight.airfieldTo} </title>
</head>
<body>
  <h2>Flight Information</h2>
  <table border="1">
    <tr>
      <th>ID</th>
      <th>From</th>
      <th>To</th>
      <th>Departure</th>
      <th>Available Seats</th>
      <th>Seats Remaining</th>
      <th>Price per Ticket</th>
    </tr>
    <tr>
      <td>${flight.id}</td>
      <td>${flight.airfieldFrom}</td>
      <td>${flight.airfieldTo}</td>
      <td>${flight.departure}</td>
      <td>${flight.availableSeats}</td>
      <td>${flight.availableSeats - seatsTaken}</td>
      <td>${flight.pricePerTicket}</td>
    </tr>
  </table>

  <h3>Purchase Tickets</h3>
  <form:form method="POST" action="/tickets/purchase">
    <input type="hidden" name="flightId" value="${flight.id}" />

    <label for="ticketCount">Number of Tickets:</label>
    <input type="number" name="ticketCount" min="1" max="${flight.availableSeats - seatsTaken}" required />

    <button type="submit">Buy Tickets</button>
  </form:form>
</body>
</html>
