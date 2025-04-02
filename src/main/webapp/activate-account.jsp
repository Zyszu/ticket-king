<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title.page.activate.account" /></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<!-- Full-Height Centered Wrapper -->
<div class="d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="container" style="max-width: 500px;">
        <div class="card shadow-sm p-4 text-center">
            <h4 class="mb-3"><spring:message code="info.account.verification" /></h4>

            <!-- Spring message code display -->
            <c:choose>
                <c:when test="${fn:contains(message, 'success')}">
                    <div class="alert alert-success">
                        <spring:message code="${message}" />
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-danger">
                        <spring:message code="${message}" />
                    </div>
                </c:otherwise>
            </c:choose>

            <!-- Back to Home Button -->
            <a href="${pageContext.request.contextPath}/home" class="btn btn-primary mt-3">
                <spring:message code="button.return.home" />
            </a>
        </div>
    </div>
</div>

</body>
</html>
