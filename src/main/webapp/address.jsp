<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title.page.address" /></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">
    <h3 class="mb-4"><spring:message code="info.address"/></h3>

    <!-- Messages -->
    <c:if test="${not empty messageError}">
        <div class="alert alert-danger"><spring:message code="${messageError}" /></div>
    </c:if>
    <c:if test="${not empty messageInfo}">
        <div class="alert alert-success"><spring:message code="${messageInfo}" /></div>
    </c:if>

    <!-- Form Card -->
    <div class="card shadow-sm">
        <div class="card-body">
        <form:form method="post" action="address" modelAttribute="address">
            <form:hidden path="id" />

                <div class="mb-3">
                    <label for="country" class="form-label"><spring:message code="text.address.country" /></label>
                    <form:input path="country" cssClass="form-control" />
                    <form:errors path="country" cssClass="text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="state" class="form-label"><spring:message code="text.address.state" /></label>
                    <form:input path="state" cssClass="form-control" />
                    <form:errors path="state" cssClass="text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="city" class="form-label"><spring:message code="text.address.city" /></label>
                    <form:input path="city" cssClass="form-control" />
                    <form:errors path="city" cssClass="text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="zipCode" class="form-label"><spring:message code="text.address.zipcode" /></label>
                    <form:input path="zipCode" cssClass="form-control" />
                    <form:errors path="zipCode" cssClass="text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="street" class="form-label"><spring:message code="text.address.street" /></label>
                    <form:input path="street" cssClass="form-control" />
                    <form:errors path="street" cssClass="text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="streetAdditional1" class="form-label"><spring:message code="text.address.add1" /></label>
                    <form:input path="streetAdditional1" cssClass="form-control" />
                    <form:errors path="streetAdditional1" cssClass="text-danger small" />
                </div>

                <div class="mb-3">
                    <label for="streetAdditional2" class="form-label"><spring:message code="text.address.add2" /></label>
                    <form:input path="streetAdditional2" cssClass="form-control" />
                    <form:errors path="streetAdditional2" cssClass="text-danger small" />
                </div>

                <div class="text-end mt-3">
                    <button type="submit" class="btn btn-primary"><spring:message code="button.text.register" /></button>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
