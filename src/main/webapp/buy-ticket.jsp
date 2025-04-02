<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="title.buy.ticket" /> | ${flight.airfieldFrom} ➝ ${flight.airfieldTo}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
  <!-- Navbar -->
  <jsp:include page="/resources/partials/navbar.jsp" />

  <div class="container mt-4">
    <h3 class="mb-4"><spring:message code="info.flight" /></h3>

    <!-- Flight Info Table -->
    <div class="card shadow-sm mb-4">
      <div class="card-body">
        <table class="table table-bordered table-striped mb-0">
          <thead class="table-light">
          <tr>
            <th><spring:message code="text.buy.ticket.id" /></th>
            <th><spring:message code="text.buy.ticket.from" /></th>
            <th><spring:message code="text.buy.ticket.to" /></th>
            <th><spring:message code="text.buy.ticket.departure" /></th>
            <th><spring:message code="text.buy.ticket.availableseats" /></th>
            <th><spring:message code="text.buy.ticket.seatsremaning" /></th>
            <th><spring:message code="text.buy.ticket.priceperticket" /></th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>${flight.id}</td>
            <td>${flight.airfieldFrom}</td>
            <td>${flight.airfieldTo}</td>
            <td>${flight.departure}</td>
            <td>${flight.availableSeats}</td>
            <td>${flight.availableSeats - seatsTaken}</td>
            <td>$${flight.pricePerTicket}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Ticket Purchase Form -->
    <h4 class="mb-3"><spring:message code="info.purchase.tickets" /></h4>
    <div class="card shadow-sm">
      <div class="card-body">
        <form:form method="POST" action="/tickets/purchase">
          <input type="hidden" name="flightId" value="${flight.id}" />

          <div class="mb-3">
            <label for="ticketCount" class="form-label"><spring:message code="text.numberoftickets" /></label>
            <input type="number"
                   class="form-control"
                   name="ticketCount"
                   min="1"
                   max="${flight.availableSeats - seatsTaken}"
                   required />
          </div>

          <button type="submit" class="btn btn-success"><spring:message code="button.text.buy.tickets" /></button>
        </form:form>
      </div>
    </div>
  </div>
</body>
</html>