<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="title.login" /></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
  <div class="card shadow-sm p-4" style="width: 100%; max-width: 400px;">
    <h3 class="text-center mb-3"><spring:message code="info.login.login" /></h3>

    <!-- Display global messages -->
    <c:if test="${not empty messageError}">
      <div class="alert alert-danger"><spring:message code="${messageError}" /></div>
    </c:if>
    <c:if test="${not empty messageInfo}">
      <div class="alert alert-info"><spring:message code="${messageInfo}" /></div>
    </c:if>

    <c:if test="${not empty error}">
      <div class="alert alert-danger"><spring:message code="${error}" /></div>
    </c:if>
    <c:if test="${not empty logout}">
      <div class="alert alert-info"><spring:message code="info.login.user.logged.out" /></div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
      <div class="mb-3">
        <label for="login" class="form-label"><spring:message code="text.login.email" /></label>
        <input type="text" class="form-control" id="login" name="login" required />
      </div>

      <div class="mb-3">
        <label for="password" class="form-label"><spring:message code="text.login.password" /></label>
        <input type="password" class="form-control" id="password" name="password" required />
      </div>

      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

      <button type="submit" class="btn btn-primary w-100"><spring:message code="button.login.login" /></button>
    </form>

    <div class="text-center mt-3">
      <small><spring:message code="text.login.info.don.have.account" /> <a href="${pageContext.request.contextPath}/register-user"><spring:message code="text.login.info.don.have.account.link.register" /></a></small>
    </div>
  </div>
</div>
</body>
</html>
