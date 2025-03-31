<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-custom">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Ticket King</a>
    <div class="ms-auto navbar-user-info">

      <!-- Authenticated User Info -->
      <sec:authorize access="isAuthenticated()">
        <span class="navbar-text">
            <sec:authentication property="name" />
        </span>
        <a id="logoutbtn" class="btn btn-outline-danger btn-sm">Log out</a>
      </sec:authorize>

      <!-- Guest User Options -->
      <sec:authorize access="!isAuthenticated()">
        <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-primary btn-sm me-2">Log in</a>
        <a href="${pageContext.request.contextPath}/register-user" class="btn btn-primary btn-sm">Register</a>
      </sec:authorize>

    </div>
  </div>
</nav>

<!-- Hidden Logout Form -->
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
