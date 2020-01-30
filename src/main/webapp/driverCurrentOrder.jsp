<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 31.01.2020
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Current order</title>
</head>
<body>
<section>
    <section>
        <jsp:useBean id="currentOrder" scope="request" type="by.epam.buber.entity.Order"/>
        <table>
            <td><c:out value="${currentOrder.coordinates}" /></td>
            <td><c:out value="${currentOrder.destinationPoint}" /></td>
            <td><c:out value="${currentOrder.comment}" /></td>
            <c:if test="${!currentOrder.started}">
            <form method="post" action="driver?action=startTrip">
            <td><input type="hidden" value="${currentOrder.id}"  name="startedOrder"/>
                <button type="submit">Trip started</button>
            </td>
            </c:if>
                <c:if test="${currentOrder.started}">
                <form method="post" action="driver?action=complete">
                    <td><input type="hidden" value="${currentOrder.id}"  name="startedOrder"/>
                        <button type="submit">Complete the order</button>
                    </td>
                    </c:if>
            </form>
        </table>
    </section></section>
</body>
</html>
