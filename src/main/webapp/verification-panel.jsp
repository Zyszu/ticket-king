<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verification Panel - Ticket King</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">
    <h3 class="mb-4">Verification Panel</h3>

    <!-- Messages -->
    <c:if test="${not empty messageError}">
        <div class="alert alert-danger">${messageError}</div>
    </c:if>
    <c:if test="${not empty messageInfo}">
        <div class="alert alert-success">${messageInfo}</div>
    </c:if>

    <!-- Existing Page Content Goes Here -->
    <div class="card shadow-sm">
        <div class="card-body">
            <!-- Keep your existing logic, tables, listings, etc. inside here -->

            <!-- Example (replace this block with your actual content): -->
            <c:choose>
                <c:when test="${not empty listCompanyVerification}">
                    <c:forEach var="verification" items="${listCompanyVerification}">
                        <div class="mb-3 border-bottom pb-3">
                            <p><strong>ID:</strong> ${verification.id}</p>
                            <p><strong>Status:</strong> Waiting for verification</p>
                            <a href="${pageContext.request.contextPath}/verifications/verification-panel/case/${verification.id}" class="btn btn-outline-primary btn-sm">View Case</a>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info">There are no more verification request. Good job!</div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>
