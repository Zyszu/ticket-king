<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Ticket King</title>
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
        <div class="alert alert-danger">${messageError}</div>
    </c:if>
    <c:if test="${not empty messageInfo}">
        <div class="alert alert-success">${messageInfo}</div>
    </c:if>

    <sec:authorize access="hasRole('ROLE_USER')">
        <!-- Collapsible Panel for App Links -->
        <div class="mb-4">
            <button class="btn btn-outline-secondary mb-2" type="button" data-bs-toggle="collapse" data-bs-target="#appLinksPanel">
                Toggle App Navigation
            </button>

            <div class="collapse" id="appLinksPanel">
                <div class="card card-body">
                    <div class="d-flex flex-wrap gap-3">
                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/tickets/wallet">Wallet</a>
                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/personal-data">Personal Data</a>
                        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/address">Address</a>
                        <sec:authorize access="not hasRole('ROLE_NOT_VERIFIED')">
                            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/register-company">Register Company</a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_PROPRIETOR') and hasRole('ROLE_VERIFIED')">
                            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/fleet">Fleet</a>
                            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/flights">Add New Flight</a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_PROPRIETOR')">
                            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/verifications/your-verifications">Your Verifications</a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_SUPPORT')">
                            <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/verifications/verification-panel">Verify Companies</a>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </div>
    </sec:authorize>

    <!-- Flights Section -->
    <h4 class="mb-3">Available Flights</h4>

    <c:choose>
        <c:when test="${not empty flightList}">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                <c:forEach var="flight" items="${flightList}">
                    <div class="col">
                        <div class="card h-100 shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">${flight.airfieldFrom} -> ${flight.airfieldTo}</h5>
                                <p class="card-text">
                                    <strong>From:</strong> ${flight.airfieldTo} <br/>
                                    <strong>To:</strong> ${flight.airfieldTo} <br/>
                                    <strong>Departure:</strong> ${flight.departure} <br/>
                                    <strong>Price:</strong> ${flight.pricePerTicket}$ <br />
                                    <strong>Available seats:</strong> ${flight.availableSeats}
                                </p>
                            </div>
                            <sec:authorize access="hasRole('ROLE_USER')">
                                <div class="card-footer text-end bg-white border-0">
                                    <a href="${pageContext.request.contextPath}/tickets/purchase/${flight.id}" class="btn btn-primary btn-sm">Buy Ticket</a>
                                </div>
                            </sec:authorize>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">No flights available at the moment.</div>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>
