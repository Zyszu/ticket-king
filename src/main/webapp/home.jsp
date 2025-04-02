<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title.home" /></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">

    <!-- Messages -->
    <c:if test="${not empty messageError}">
        <div class="alert alert-danger"><spring:message code="${messageError}" /></div>
    </c:if>
    <c:if test="${not empty messageInfo}">
        <div class="alert alert-success"><spring:message code="${messageInfo}" /></div>
    </c:if>

    <sec:authorize access="hasRole('ROLE_USER')">
        <!-- Collapsible Panel for App Links -->
        <div class="mb-4">
            <button class="btn btn-outline-secondary mb-2" type="button" data-bs-toggle="collapse" data-bs-target="#appLinksPanel">
                <spring:message code="button.home.toggle.nav" />
            </button>

            <div class="collapse" id="appLinksPanel">
                <div class="card card-body">
                    <div class="d-flex flex-wrap gap-3">
                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/tickets/wallet"><spring:message code="button.home.link.wallet" /></a>
                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/personal-data"><spring:message code="button.home.link.personal.data" /></a>
                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/address"><spring:message code="button.home.link.address" /></a>
                        <sec:authorize access="not hasRole('ROLE_NOT_VERIFIED')">
                            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/register-company"><spring:message code="button.home.link.register.company" /></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_PROPRIETOR') and hasRole('ROLE_VERIFIED')">
                            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/fleet"><spring:message code="button.home.link.fleet" /></a>
                            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/flights"><spring:message code="button.home.link.add.new.flight" /></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_PROPRIETOR')">
                            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/verifications/your-verifications"><spring:message code="button.home.link.your.verifications" /></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_SUPPORT')">
                            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/verifications/verification-panel"><spring:message code="button.home.link.verify.companies" /></a>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </div>
    </sec:authorize>

    <!-- Flights Section -->
    <h4 class="mb-3"><spring:message code="info.home.available.flights" /></h4>

    <c:choose>
        <c:when test="${not empty listFlightDTO}">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4 mb-5">
                <c:forEach var="flight" items="${listFlightDTO}">
                    <div class="col">
                        <div class="card h-100 shadow-sm">
                            <div class="card-body">
                                <!-- Main Route -->
                                <h5 class="card-title mb-3">${flight.airfieldFrom} → ${flight.airfieldTo}</h5>

                                <!-- Flight Details -->
                                <div class="mb-2 small text-muted">
                                    <div><strong><spring:message code="text.home.carrier" /></strong> ${flight.carrier}</div>
                                    <div><strong><spring:message code="text.home.aircraft.model" /></strong> ${flight.aircraftModel}</div>
                                </div>

                                <div class="mb-2">
                                    <strong><spring:message code="text.home.departure" /></strong>
                                    <div class="text-dark">
                                        <fmt:formatDate value="${flight.departure}" pattern="MMMM dd, yyyy 'at' HH:mm" />
                                    </div>
                                </div>

                                <!-- Price and Seats -->
                                <div class="d-flex justify-content-between align-items-center">
                                    <div>
                                        <strong><spring:message code="text.home.price" /></strong> $${flight.pricePerTicket}
                                    </div>
                                    <div class="text-muted small">
                                        <strong><spring:message code="text.home.seats" /></strong> ${flight.countTicketsLeft} / ${flight.availableSeats}
                                    </div>
                                </div>
                            </div>

                            <!-- Action Button -->
                            <sec:authorize access="hasRole('ROLE_USER')">
                                <div class="card-footer text-end bg-white border-0">
                                    <c:choose>
                                        <c:when test="${flight.countTicketsLeft > 0}">
                                            <a href="${pageContext.request.contextPath}/tickets/purchase/${flight.id}" class="btn btn-primary btn-sm">
                                                <spring:message code="button.home.buy.ticket" />
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-secondary btn-sm" disabled><spring:message code="button.home.sold.out" /></button>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </sec:authorize>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info"><spring:message code="info.home.no.available.flights" /></div>
        </c:otherwise>
    </c:choose>


</div>
</body>
</html>
