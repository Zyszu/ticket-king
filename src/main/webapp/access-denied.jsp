<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <title><spring:message code="title.page.access.denied" /></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/resources/partials/navbar.jsp" />

<div class="container d-flex justify-content-center align-items-center min-vh-80">
  <div class="text-center">
    <div class="display-1 text-danger mb-3">🚫</div>
    <h2 class="text-danger mb-3"><spring:message code="info.access.denied" /></h2>
    <p class="lead mb-4"><spring:message code="info.access.denied.msg1" /><br><spring:message code="info.access.denied.msg2" /></p>
    <a href="${pageContext.request.contextPath}/home" class="btn btn-primary"><spring:message code="button.return.home" /></a>
  </div>
</div>

</body>
</html>
