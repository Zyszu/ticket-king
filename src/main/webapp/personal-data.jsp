<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="title.personal.data" /></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">
  <h3 class="mb-4"><spring:message code="info.personal.data.personal.data" /></h3>

  <!-- Messages -->
  <c:if test="${not empty messageError}">
    <div class="alert alert-danger"><spring:message code="${messageError}" /></div>
  </c:if>
  <c:if test="${not empty messageInfo}">
    <div class="alert alert-success"><spring:message code="${messageInfo}" /></div>
  </c:if>

  <!-- Personal Data Form -->
  <div class="card shadow-sm">
    <div class="card-body">
      <form:form method="post" modelAttribute="personalData">

        <form:hidden path="id" />

        <div class="mb-3">
          <label for="firstName" class="form-label"><spring:message code="text.personal.data.first.name" /></label>
          <form:input path="firstName" cssClass="form-control" />
          <form:errors path="firstName" cssClass="text-danger small" />
        </div>

        <div class="mb-3">
          <label for="secondName" class="form-label"><spring:message code="text.personal.data.second.name" /></label>
          <form:input path="secondName" cssClass="form-control" />
          <form:errors path="secondName" cssClass="text-danger small" />
        </div>

        <div class="mb-3">
          <label for="lastName" class="form-label"><spring:message code="text.personal.data.last.name" /></label>
          <form:input path="lastName" cssClass="form-control" />
          <form:errors path="lastName" cssClass="text-danger small" />
        </div>

  <%--        <div class="mb-3">--%>
  <%--          <label for="phoneNumber" class="form-label">Phone Number</label>--%>
  <%--          <form:input path="phoneNumber" cssClass="form-control" />--%>
  <%--          <form:errors path="phoneNumber" cssClass="text-danger small" />--%>
  <%--        </div>--%>

<%--        <div class="mb-3">--%>
<%--          <label for="birthDate" class="form-label">Birth Date</label>--%>
<%--          <form:input path="birthDate" cssClass="form-control" type="date" />--%>
<%--          <form:errors path="birthDate" cssClass="text-danger small" />--%>
<%--        </div>--%>

        <div class="text-end">
          <button type="submit" class="btn btn-primary"><spring:message code="button.personal.data.save" /></button>
        </div>

      </form:form>
    </div>
  </div>
</div>

</body>
</html>
