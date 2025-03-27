<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 27/03/2025
  Time: 00:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Title</title>
</head>
<body>
<form:form method="post" action="register-company" modelAttribute="company">
  <form:hidden path="id" />

  <!-- Email -->
  <label for="companyName">Company Name</label>
  <form:input path="companyName" class="input-field" />
  <form:errors path="companyName" />

  <!-- Password -->
  <label for="nip">NIP</label>
  <form:input path="nip" class="input-field" />
  <form:errors path="nip" />

  <!-- Address Fields -->
  <fieldset>
    <legend>Address</legend>

    <label for="address.country">Country</label>
    <form:input path="address.country" class="input-field" />
    <form:errors path="address.country" />

    <label for="address.state">State</label>
    <form:input path="address.state" class="input-field" />
    <form:errors path="address.state" />

    <label for="address.city">City</label>
    <form:input path="address.city" class="input-field" />
    <form:errors path="address.city" />

    <label for="address.zipCode">Zip Code</label>
    <form:input path="address.zipCode" class="input-field" />
    <form:errors path="address.zipCode" />

    <label for="address.street">Street</label>
    <form:input path="address.street" class="input-field" />
    <form:errors path="address.street" />

    <label for="address.streetAdditional1">Street Additional 1</label>
    <form:input path="address.streetAdditional1" class="input-field" />
    <form:errors path="address.streetAdditional1" />

    <label for="address.streetAdditional2">Street Additional 2</label>
    <form:input path="address.streetAdditional2" class="input-field" />
    <form:errors path="address.streetAdditional2" />
  </fieldset>

  <!-- Address Fields -->
  <fieldset>
    <legend>User</legend>

    <label for="owner.id">Owner id</label>
    <form:input path="owner.id" class="input-field" />
    <form:errors path="owner.id" />
  </fieldset>

  <button type="submit">Register Company</button>
</form:form>


</body>
</html>
