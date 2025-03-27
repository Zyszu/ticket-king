<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Create Flight</title>
</head>
<body>

<h2>Create a New Flight</h2>

<form:form method="post" modelAttribute="flight" action="/flights">
  <label for="aircraft">Aircraft:</label>
  <form:select path="aircraft.id">
    <form:options items="${aircraftList}" itemValue="id" itemLabel="model" />
  </form:select>
  <br/>

  <label for="airfieldFrom">Airfield From:</label>
  <form:input path="airfieldFrom" />
  <br/>

  <label for="airfieldTo">Airfield To:</label>
  <form:input path="airfieldTo" />
  <br/>

  <label for="departure">Departure (YYYY-MM-DDTHH:MM):</label>
  <form:input path="departure" type="datetime-local" />
  <br/>

  <label for="availableSeats">Available Seats:</label>
  <form:input path="availableSeats" type="number" />
  <br/>

  <label for="pricePerTicket">Ticket Price:</label>
  <form:input path="pricePerTicket" type="number" step="0.01" />
  <br/>

  <button type="submit">Create Flight</button>
</form:form>

</body>
</html>
