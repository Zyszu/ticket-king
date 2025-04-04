<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-expand-lg navbar-custom">
  <div class="container-fluid">
    <!-- Left: App Name -->
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Ticket King</a>

    <!-- Right: Auth Controls + Language -->
    <div class="d-flex align-items-center ms-auto gap-3">

      <!-- ✅ Language Switcher -->
      <div class="language-switcher small">
        <a href="?lang=en" class="me-1">EN</a> |
        <a href="?lang=es" class="mx-1">ES</a>
      </div>

      <!-- ✅ User Controls -->
      <sec:authorize access="isAuthenticated()">
        <span class="navbar-text">
          <sec:authentication property="name" />
        </span>
        <a id="logoutbtn" class="btn btn-outline-danger btn-sm ms-2">
          <spring:message code="text.navbar.logout" />
        </a>
      </sec:authorize>

      <sec:authorize access="!isAuthenticated()">
        <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-primary btn-sm me-2">
          <spring:message code="text.navbar.login" />
        </a>
        <a href="${pageContext.request.contextPath}/register-user" class="btn btn-primary btn-sm">
          <spring:message code="text.navbar.register" />
        </a>
      </sec:authorize>
    </div>
  </div>
</nav>

<!-- 🔒 Hidden Logout Form -->
<form action="${pageContext.request.contextPath}/logout" method="post" id="logoutForm">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<script>
  const logoutBtn = document.getElementById("logoutbtn");
  if (logoutBtn) {
    logoutBtn.addEventListener("click", function () {
      document.getElementById("logoutForm").submit();
    });
  }
</script>
