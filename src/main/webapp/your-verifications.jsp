<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Verifications - Ticket King</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">
    <h3 class="mb-4">Your Company Verification Requests</h3>

    <div class="card shadow-sm">
        <div class="card-body p-0">
            <c:choose>
                <c:when test="${not empty listCompanyVerification}">
                    <table class="table table-bordered table-striped mb-0">
                        <thead class="table-light">
                        <tr>
                            <th>Status</th>
                            <th>Accepted</th>
                            <th>Comment</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listCompanyVerification}" var="v">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${v.verificationComplete}">Completed</c:when>
                                        <c:otherwise>Pending</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${v.verificationComplete}">
                                            <c:out value="${v.accepted ? 'Yes' : 'No'}"/>
                                        </c:when>
                                        <c:otherwise>-</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${v.comment}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="p-3">
                        <div class="alert alert-info mb-0">You have no verification requests yet.</div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>
