<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="title.flight" /> </title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">
  <h3 class="mb-4"><spring:message code="info.flight.new.flight" /></h3>

  <div class="card shadow-sm">
    <div class="card-body">
      <form:form method="POST" action="/flights" modelAttribute="flight">
        <form:hidden path="id" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <!-- Aircraft Selection -->
        <div class="mb-3">
          <label for="aircraft.id" class="form-label"><spring:message code="text.flight.select.aircraft" /></label>
          <form:select path="aircraft.id" cssClass="form-select" required="required">
            <form:options items="${aircraftList}" itemValue="id" itemLabel="model" />
          </form:select>
        </div>

        <!-- Airfield From -->
        <div class="mb-3">
          <label for="airfieldFrom" class="form-label"><spring:message code="text.flight.airfield.from" /></label>
          <form:input path="airfieldFrom" cssClass="form-control" required="required" />
        </div>

        <!-- Airfield To -->
        <div class="mb-3">
          <label for="airfieldTo" class="form-label"><spring:message code="text.flight.airfield.to" /></label>
          <form:input path="airfieldTo" cssClass="form-control" required="required" />
        </div>

        <!-- Departure -->
        <div class="mb-3">
          <label for="departure" class="form-label"><spring:message code="text.flight.departure" /></label>
          <form:input path="departure" type="datetime-local" cssClass="form-control" required="required" />
        </div>

        <!-- Available Seats -->
        <div class="mb-3">
          <label for="availableSeats" class="form-label"><spring:message code="text.flight.available.seats" /></label>
          <form:input path="availableSeats" type="number" cssClass="form-control" required="required" />
        </div>

        <!-- Price per Ticket -->
        <div class="mb-3">
          <label for="pricePerTicket" class="form-label"><spring:message code="text.flight.ticket.price" /></label>
          <form:input path="pricePerTicket" type="number" step="0.01" cssClass="form-control" required="required" />
        </div>

        <!-- Submit -->
        <div class="text-end">
          <button type="submit" class="btn btn-success"><spring:message code="button.flight.create.flight" /></button>
        </div>
      </form:form>
    </div>
  </div>
</div>

</body>
</html>
