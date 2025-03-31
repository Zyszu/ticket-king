<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Pending Company Verifications</h2>
<table>
    <tr>
        <th>Company Name</th>
        <th>Owner Email</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listCompanyVerification}" var="v">
        <tr>
            <td>${v.company.companyName}</td>
            <td>${v.company.owner.email}</td>
            <td><a href="/verifications/verification-panel/case/${v.id}">Review</a></td>
        </tr>
    </c:forEach>
</table>
