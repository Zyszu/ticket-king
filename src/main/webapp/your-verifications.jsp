<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Your Company Verification Requests</h2>
<table>
    <tr>
        <th>Status</th>
        <th>Accepted</th>
        <th>Comment</th>
    </tr>
    <c:forEach items="${listCompanyVerification}" var="v">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${v.verificationComplete}">Completed</c:when>
                    <c:otherwise>Pending</c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${v.verificationComplete}">
                        <c:out value="${v.accepted ? 'Yes' : 'No'}"/>
                    </c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </td>
            <td>${v.comment}</td>
        </tr>
    </c:forEach>
</table>
