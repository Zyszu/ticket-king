<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Wallet - Ticket King</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container mt-4">
    <h3 class="mb-4">Your Tickets</h3>

    <c:choose>
        <c:when test="${not empty ticketList}">
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                <c:forEach items="${ticketList}" var="ticket">
                    <div class="col">
                        <div class="card h-100 shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">Flight #${ticket.flight.id}</h5>
                                <p class="card-text">
                                    <strong>Ticket ID:</strong> ${ticket.id} <br/>
                                    <strong>From:</strong> ${ticket.flight.airfieldFrom} <br/>
                                    <strong>To:</strong> ${ticket.flight.airfieldTo} <br/>
                                    <strong>Carrier:</strong> ${ticket.company.companyName} <br/>
                                    <strong>Departure:</strong> ${ticket.flight.departure} <br/>
                                    <strong>Aircraft:</strong> ${ticket.flight.aircraft.model}
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info">You don't have any tickets yet.</div>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
