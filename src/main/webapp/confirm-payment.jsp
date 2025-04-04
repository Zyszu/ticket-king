<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title.confirm.payment" /></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
    <style>
        .fade-out {
            animation: fadeOut 0.5s ease-out forwards;
        }

        .fade-in {
            animation: fadeIn 0.5s ease-in forwards;
        }

        @keyframes fadeOut {
            from { opacity: 1; }
            to { opacity: 0; }
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .cancel-icon {
            font-size: 3rem;
            color: #dc3545;
            animation: popIn 0.4s ease;
        }

        @keyframes popIn {
            from { transform: scale(0.5); opacity: 0; }
            to { transform: scale(1); opacity: 1; }
        }
    </style>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<!-- Centered Layout -->
<div class="d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="container" style="max-width: 500px;">
        <div class="card shadow-sm p-4 text-center">
            <div id="paymentContent" class="fade-in">
                <h4 class="mb-3"><spring:message code="info.confirm.payment.confirm.payment" /></h4>
                <p class="text-muted mb-4">
                    <spring:message code="text.confirm.payment.msg1" /><br>
                    <spring:message code="text.confirm.payment.msg2" />
                </p>

                <!-- Payment Form -->
                <form:form method="post" modelAttribute="ticketWrapperDTO" action="/tickets/purchase/confirm">
                    <c:forEach var="id" items="${ticketWrapperDTO.ticketIds}" varStatus="status">
                        <form:input type="hidden" path="ticketIds[${status.index}]" value="${id}" />
                    </c:forEach>
                    <button id="confirmBtn" type="submit" class="btn btn-success"><spring:message code="button.confirm.payment.confirm" /></button>
                </form:form>
            </div>
        </div>
    </div>
</div>

<!-- Auto-cancel & redirect script -->
<script>
    setTimeout(() => {
        const content = document.getElementById("paymentContent");
        if (content) {
            content.classList.add("fade-out");

            setTimeout(() => {
                content.innerHTML = `
                    <div class="fade-in">
                        <div class="cancel-icon mb-3">✖</div>
                        <h4 class="text-danger mb-2"><spring:message code="info.confirm.payment.canceled" /></h4>
                        <p class="text-muted"><spring:message code="text.confirm.payment.canceled.msg1" /></p>
                        <p class="small text-secondary"><spring:message code="text.confirm.payment.canceled.msg2" /></p>
                    </div>
                `;
                content.classList.remove("fade-out");
                content.classList.add("fade-in");
            }, 500);
        }

        // Disable button just in case
        const btn = document.getElementById("confirmBtn");
        if (btn) btn.disabled = true;

        // Redirect after another 5 seconds
        setTimeout(() => {
            window.location.href = '${pageContext.request.contextPath}/home';
        }, 5000);

    }, 10000); // 10 seconds
</script>

</body>
</html>
