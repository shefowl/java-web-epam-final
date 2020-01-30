<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 27.01.2020
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Driver page</title>
</head>
<body>
<section>
    <section>
        <jsp:useBean id="orders" scope="request" type="java.util.List"/>
        <%--<jsp:useBean id ="driver" class="by.epam.buber.entity.participant.Driver" scope="request"/>--%>
        <table>
            <c:forEach items="${orders}" var="currentOrder">
                <td>
                <td>
                    <form method="post" action="driver?action=acceptOrder">
                <td><c:out value="${currentOrder.coordinates}" /></td>
                <td><c:out value="${currentOrder.destinationPoint}" /></td>
                <td><input type="hidden" value="${currentOrder.id}"  name="acceptedOrder"/>
                    <button type="submit">Choose</button>
                </td>
                </form>
                </td>
                </tr>
            </c:forEach>
        </table>
    </section></section>
</body>
</html>
