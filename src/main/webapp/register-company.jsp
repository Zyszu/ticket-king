<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="title.register.company" /></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">
  <h3 class="mb-4"><spring:message code="info.register.company.register.company" /></h3>

  <!-- Messages -->
  <c:if test="${not empty messageError}">
    <div class="alert alert-danger"><spring:message code="${messageError}" /></div>
  </c:if>
  <c:if test="${not empty messageInfo}">
    <div class="alert alert-success"><spring:message code="${messageInfo}" /></div>
  </c:if>

  <!-- Form Starts -->
  <div class="card shadow-sm mb-3 ">
    <div class="card-body">
      <form:form method="post" action="register-company" modelAttribute="company">

        <form:hidden path="id" />

        <!-- Company Name -->
        <div class="mb-3">
          <label for="companyName" class="form-label"><spring:message code="text.register.company.company.name" /></label>
          <form:input path="companyName" cssClass="form-control" />
          <form:errors path="companyName" cssClass="text-danger small" />
        </div>

        <!-- NIP -->
        <div class="mb-3">
          <label for="nip" class="form-label"><spring:message code="text.register.company.nip" /></label>
          <form:input path="nip" cssClass="form-control" />
          <form:errors path="nip" cssClass="text-danger small" />
        </div>

        <!-- Address Fields -->
        <fieldset class="border rounded-3 p-3 mb-3">
          <legend class="float-none w-auto px-2"><spring:message code="text.register.company.address" /></legend>

          <div class="mb-3">
            <label for="address.country" class="form-label"><spring:message code="text.register.company.country" /></label>
            <form:input path="address.country" cssClass="form-control" />
            <form:errors path="address.country" cssClass="text-danger small" />
          </div>

          <div class="mb-3">
            <label for="address.state" class="form-label"><spring:message code="text.register.company.state" /></label>
            <form:input path="address.state" cssClass="form-control" />
            <form:errors path="address.state" cssClass="text-danger small" />
          </div>

          <div class="mb-3">
            <label for="address.city" class="form-label"><spring:message code="text.register.company.city" /></label>
            <form:input path="address.city" cssClass="form-control" />
            <form:errors path="address.city" cssClass="text-danger small" />
          </div>

          <div class="mb-3">
            <label for="address.zipCode" class="form-label"><spring:message code="text.register.company.zipcode" /></label>
            <form:input path="address.zipCode" cssClass="form-control" />
            <form:errors path="address.zipCode" cssClass="text-danger small" />
          </div>

          <div class="mb-3">
            <label for="address.street" class="form-label"><spring:message code="text.register.company.street" /></label>
            <form:input path="address.street" cssClass="form-control" />
            <form:errors path="address.street" cssClass="text-danger small" />
          </div>

          <div class="mb-3">
            <label for="address.streetAdditional1" class="form-label"><spring:message code="text.register.company.street.additional1" /></label>
            <form:input path="address.streetAdditional1" cssClass="form-control" />
            <form:errors path="address.streetAdditional1" cssClass="text-danger small" />
          </div>

          <div class="mb-3">
            <label for="address.streetAdditional2" class="form-label"><spring:message code="text.register.company.street.additional1" /></label>
            <form:input path="address.streetAdditional2" cssClass="form-control" />
            <form:errors path="address.streetAdditional2" cssClass="text-danger small" />
          </div>
        </fieldset>

        <!-- Submit -->
        <div class="text-end">
          <button type="submit" class="btn btn-success"><spring:message code="button.register.company.register" /></button>
        </div>

      </form:form>
    </div>
  </div>
</div>

</body>
</html>
