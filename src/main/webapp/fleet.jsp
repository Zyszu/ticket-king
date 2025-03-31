<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Fleet - Ticket King</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">
  <h3 class="mb-4">Fleet Management</h3>

  <!-- Aircraft Registration Form -->
  <div class="card shadow-sm mb-4">
    <div class="card-body">
      <h5 class="card-title mb-3">Register New Aircraft</h5>
      <form:form method="post" action="/fleet" modelAttribute="aircraft">
        <form:hidden path="id" />

        <div class="mb-3">
          <label for="model" class="form-label">Aircraft Name</label>
          <form:input path="model" cssClass="form-control" />
          <form:errors path="model" cssClass="text-danger small" />
        </div>

        <button type="submit" class="btn btn-primary">Register Aircraft</button>
      </form:form>
    </div>
  </div>

  <!-- Aircraft List Table -->
  <div class="card shadow-sm">
    <div class="card-body">
      <h5 class="card-title mb-3">Registered Aircraft</h5>

      <c:choose>
        <c:when test="${not empty aircraftList}">
          <table class="table table-bordered table-hover">
            <thead class="table-light">
            <tr>
              <th>Model</th>
              <th>ID</th>
              <!-- add more fields as needed -->
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
          </table>
        </c:when>
        <c:otherwise>
          <div class="alert alert-info mb-0">No aircraft registered yet.</div>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
</div>

</body>
</html>
