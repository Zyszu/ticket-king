<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <title>Personal Data</title>
</head>
<body>

<h2>Add Personal Data</h2>

<form:form method="post" modelAttribute="personalData" action="/personal-data">
  <form:hidden path="id" />

  <label for="userId">User ID:</label>
  <input type="number" name="userId" />
  <br/>

  <label for="firstName">First Name:</label>
  <form:input path="firstName" />
  <br/>

  <label for="secondName">Second Name:</label>
  <form:input path="secondName" />
  <br/>

  <label for="lastName">Last Name:</label>
  <form:input path="lastName" />
  <br/>

  <button type="submit">Save</button>
</form:form>

</body>
</html>
