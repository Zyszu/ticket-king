<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Register - Ticket King</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div class="container d-flex justify-content-center align-items-center min-vh-100">
  <div class="card shadow-sm p-4" style="width: 100%; max-width: 500px;">
    <h3 class="text-center mb-3">Create Account</h3>

    <c:if test="${not empty messageError}">
      <div class="alert alert-danger">${messageError}</div>
    </c:if>
    <c:if test="${not empty messageInfo}">
      <div class="alert alert-success">${messageInfo}</div>
    </c:if>

    <form:form method="post" modelAttribute="user" cssClass="needs-validation" novalidate="novalidate">

      <div class="mb-3">
        <label for="email" class="form-label">Email address</label>
        <form:input path="email" cssClass="form-control" id="email" />
        <form:errors path="email" cssClass="text-danger small" />
      </div>

      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <form:password path="password" cssClass="form-control" id="password" />
        <form:errors path="password" cssClass="text-danger small" />
      </div>

<%--      <div class="mb-3">--%>
<%--        <label for="confirmPassword" class="form-label">Confirm Password</label>--%>
<%--        <form:password path="confirmPassword" cssClass="form-control" id="confirmPassword" />--%>
<%--        <form:errors path="confirmPassword" cssClass="text-danger small" />--%>
<%--      </div>--%>

      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

      <button type="submit" class="btn btn-success w-100">Register</button>
    </form:form>

    <div class="text-center mt-3">
      <small>Already have an account? <a href="${pageContext.request.contextPath}/login">Log in</a></small>
    </div>
  </div>
</div>
</body>
</html>
