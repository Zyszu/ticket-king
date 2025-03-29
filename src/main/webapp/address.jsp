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
    <title>Register address</title>
</head>
<body>
<form:form method="post" action="address" modelAttribute="address">
    <form:hidden path="id" />

    <!-- Address Fields -->
    <fieldset>
        <legend>Address</legend>

        <label for="country">Country</label>
        <form:input path="country" class="input-field" />
        <form:errors path="country" />

        <label for="state">State</label>
        <form:input path="state" class="input-field" />
        <form:errors path="state" />

        <label for="city">City</label>
        <form:input path="city" class="input-field" />
        <form:errors path="city" />

        <label for="zipCode">Zip Code</label>
        <form:input path="zipCode" class="input-field" />
        <form:errors path="zipCode" />

        <label for="street">Street</label>
        <form:input path="street" class="input-field" />
        <form:errors path="street" />

        <label for="streetAdditional1">Street Additional 1</label>
        <form:input path="streetAdditional1" class="input-field" />
        <form:errors path="streetAdditional1" />

        <label for="streetAdditional2">Street Additional 2</label>
        <form:input path="streetAdditional2" class="input-field" />
        <form:errors path="streetAdditional2" />
    </fieldset>

    <button type="submit">Register</button>
</form:form>


</body>
</html>
