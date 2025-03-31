<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Company Verification Case</h2>

<h3>Company Info</h3>
<p><strong>Name:</strong> ${company.companyName}</p>
<p><strong>NIP:</strong> ${company.nip}</p>
<p><strong>Address:</strong> ${company.address.street}, ${company.address.city}, ${company.address.country}</p>

<h3>Owner Info</h3>
<p><strong>Email:</strong> ${owner.email}</p>
<p><strong>Address:</strong> ${owner.address.street}, ${owner.address.city}, ${owner.address.country}</p>
<form method="post" action="">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

  <label>Comment:</label><br>
  <textarea name="comment" required></textarea><br><br>

  <button type="submit" name="accepted" value="true">Accept</button>
  <button type="submit" name="accepted" value="false">Reject</button>
</form>

