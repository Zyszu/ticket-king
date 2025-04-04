<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="title.register.user" /></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
  <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
  <div class="card shadow-sm p-4" style="width: 100%; max-width: 500px;">
    <h3 class="text-center mb-3"><spring:message code="info.register.user.create.account" /></h3>

    <c:if test="${not empty messageError}">
      <div class="alert alert-danger"><spring:message code="${messageError}" /></div>
    </c:if>
    <c:if test="${not empty messageInfo}">
      <div class="alert alert-success"><spring:message code="${messageInfo}" /></div>
    </c:if>

    <form:form method="post" modelAttribute="user" cssClass="needs-validation" novalidate="novalidate">

      <div class="mb-3">
        <label for="email" class="form-label"><spring:message code="text.register.user.email" /></label>
        <form:input path="email" cssClass="form-control" id="email" />
        <form:errors path="email" cssClass="text-danger small" />
      </div>

      <div class="mb-3">
        <label for="password" class="form-label"><spring:message code="text.register.user.password" /></label>
        <form:password path="password" cssClass="form-control" id="password" />
        <form:errors path="password" cssClass="text-danger small" />
      </div>

<%--      <div class="mb-3">--%>
<%--        <label for="confirmPassword" class="form-label">Confirm Password</label>--%>
<%--        <form:password path="confirmPassword" cssClass="form-control" id="confirmPassword" />--%>
<%--        <form:errors path="confirmPassword" cssClass="text-danger small" />--%>
<%--      </div>--%>

      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      <input type="hidden" name="active" value="false" />

      <button type="submit" class="btn btn-success w-100 mb-3"><spring:message code="button.register.user.register" /></button>
      <div class="d-flex justify-content-center mb-3">
        <div class="g-recaptcha" data-sitekey="${reCAPTCHASiteKey}"></div>
      </div>
    </form:form>

    <div class="text-center mt-3">
      <small><spring:message code="info.register.user.no.account" /> <a href="${pageContext.request.contextPath}/login"><spring:message code="info.register.user.login.url" /></a></small>
    </div>
  </div>
</div>
</body>
</html>
