<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="title.verification.case" /></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4 mb-5">
  <h3 class="mb-4"><spring:message code="info.verification.case.verification.case" /></h3>

  <!-- Messages -->
  <c:if test="${not empty messageError}">
    <div class="alert alert-danger"><spring:message code="${messageError}" /></div>
  </c:if>
  <c:if test="${not empty messageInfo}">
    <div class="alert alert-success"><spring:message code="${messageInfo}" /></div>
  </c:if>

  <!-- Case Display -->
  <div class="card shadow-sm mb-4">
    <div class="card-body">
      <div class="row mb-3">
        <label class="col-sm-3 col-form-label"><spring:message code="text.verification.case.case.id" /></label>
        <div class="col-sm-9">
          <p class="form-control-plaintext">#${verification.id}</p>
        </div>
      </div>

      <div class="row mb-3">
        <label class="col-sm-3 col-form-label"><spring:message code="text.verification.case.owner.mail" /></label>
        <div class="col-sm-9">
          <p class="form-control-plaintext">${owner.email}</p>
        </div>
      </div>

      <div class="row mb-3">
        <label class="col-sm-3 col-form-label"><spring:message code="text.verification.case.company.owner" /></label>
        <div class="card-body">
          <table class="table table-bordered table-striped mb-0">
            <thead class="table-light">
              <tr>
                <th><spring:message code="text.verification.case.first.name" /></th>
                <th><spring:message code="text.verification.case.second.name" /></th>
                <th><spring:message code="text.verification.case.last.name" /></th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>${owner.personalData.firstName}</td>
                <td>${owner.personalData.secondName}</td>
                <td>${owner.personalData.lastName}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <div class="row mb-3">
        <label class="col-sm-3 col-form-label"><spring:message code="text.verification.case.company.data" /></label>
        <div class="card-body">
          <table class="table table-bordered table-striped mb-0">
            <thead class="table-light">
            <tr>
              <th><spring:message code="text.verification.case.company.name" /></th>
              <th><spring:message code="text.verification.case.nip" /></th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>${company.companyName}</td>
              <td>${company.nip}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>

      <div class="row mb-3">
        <label class="col-sm-3 col-form-label"><spring:message code="text.verification.case.company.address" /></label>
        <div class="card-body">
          <table class="table table-bordered table-striped mb-0">
            <thead class="table-light">
            <tr>
              <th><spring:message code="text.verification.case.country" /></th>
              <th><spring:message code="text.verification.case.state" /></th>
              <th><spring:message code="text.verification.case.zipcode" /></th>
              <th><spring:message code="text.verification.case.city" /></th>
              <th><spring:message code="text.verification.case.street" /></th>
              <th><spring:message code="text.verification.case.street.additional1" /></th>
              <th><spring:message code="text.verification.case.street.additional2" /></th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>${company.address.country}</td>
              <td>${company.address.state}</td>
              <td>${company.address.zipCode}</td>
              <td>${company.address.city}</td>
              <td>${company.address.street}</td>
              <td>${company.address.streetAdditional1}</td>
              <td>${company.address.streetAdditional2}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>

    </div>
  </div>

  <!-- Comment & Action Buttons -->
  <form action="${pageContext.request.contextPath}/verifications/verification-panel/case/${verification.id}" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <div class="mb-3">
      <label for="comment" class="form-label"><spring:message code="text.verification.case.support.comment" /></label>
      <textarea name="comment" id="comment" class="form-control" rows="4"></textarea>
    </div>

    <div class="d-flex gap-3">
      <button type="submit" name="accepted" value="true" class="btn btn-success"><spring:message code="button.verification.case.accept" /></button>
      <button type="submit" name="accepted" value="false" class="btn btn-danger"><spring:message code="button.verification.case.reject" /></button>
    </div>
  </form>
</div>

</body>
</html>
