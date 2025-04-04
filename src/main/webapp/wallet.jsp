<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title.wallet" /></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">
    <h3 class="mb-4"><spring:message code="info.wallet.your.tickets" /></h3>

    <c:choose>
        <c:when test="${not empty ticketList}">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                <c:forEach items="${ticketList}" var="ticket">
                    <div class="col">
                        <div class="card h-100 shadow-sm d-flex flex-column justify-content-between">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <spring:message code="info.wallet.flight" /> ${ticket.flight.id}
                                </h5>
                                <p class="card-text">
                                    <strong><spring:message code="info.wallet.ticket.id" /></strong> ${ticket.id} <br/>
                                    <strong><spring:message code="info.wallet.from" /></strong> ${ticket.flight.airfieldFrom} <br/>
                                    <strong><spring:message code="info.wallet.to" /></strong> ${ticket.flight.airfieldTo} <br/>
                                    <strong><spring:message code="info.wallet.carrier" /></strong> ${ticket.company.companyName} <br/>
                                    <strong><spring:message code="info.wallet.departure" /></strong> ${ticket.flight.departure} <br/>
                                    <strong><spring:message code="info.wallet.aircraft" /></strong> ${ticket.flight.aircraft.model}
                                </p>
                            </div>

                            <!-- PDF Button in Footer -->
                            <div class="card-footer bg-white border-0 text-end">
                                <a href="${pageContext.request.contextPath}/tickets/pdf/${ticket.id}" class="btn btn-outline-secondary btn-sm">
                                    <i class="bi bi-file-earmark-pdf"></i> <spring:message code="button.generate.pdf" />
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info"><spring:message code="info.wallet.no.tickets" /></div>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
