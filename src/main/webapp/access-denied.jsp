<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Access Denied - Ticket King</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container d-flex justify-content-center align-items-center min-vh-80">
  <div class="text-center">
    <div class="display-1 text-danger mb-3">🚫</div>
    <h2 class="text-danger mb-3">Access Denied</h2>
    <p class="lead mb-4">You don't have permission to view this page.<br>Please contact support if you believe this is an error.</p>
    <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Return to Home</a>
  </div>
</div>

</body>
</html>
