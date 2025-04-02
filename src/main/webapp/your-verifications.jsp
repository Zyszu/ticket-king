<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title.your.verifications" /></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">
    <h3 class="mb-4"><spring:message code="info.your.verifications.your.verifications" /></h3>

    <div class="card shadow-sm">
        <div class="card-body p-0">
            <c:choose>
                <c:when test="${not empty listCompanyVerification}">
                    <table class="table table-bordered table-striped mb-0">
                        <thead class="table-light">
                        <tr>
                            <th><spring:message code="text.your.verifications.status" /></th>
                            <th><spring:message code="text.your.verifications.is.accepted" /></th>
                            <th><spring:message code="text.your.verifications.comment" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listCompanyVerification}" var="v">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${v.verificationComplete}">
                                            <spring:message code="text.your.verifications.status.completed" />
                                        </c:when>
                                        <c:otherwise><spring:message code="text.your.verifications.status.pending" /></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${v.verificationComplete}">
                                            <c:choose>
                                                <c:when test="${v.accepted == true}" >
                                                    <spring:message code="text.your.verifications.is.accepted.yes" />
                                                </c:when>
                                                <c:otherwise>
                                                    <spring:message code="text.your.verifications.is.accepted.no" />
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <spring:message code="text.your.verifications.status.not.completed" />
                                        </c:otherwise>
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
                        <div class="alert alert-info mb-0"><spring:message code="info.your.verifications.no.verifications" /></div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>
