<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 29.01.2020
  Time: 20:56
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
        <jsp:useBean id="driverRequested" scope="request" type="java.lang.Boolean"/>
        <table>
            <td>
            <td>
            <form method="post" action="app?action=cancelOrder">
            <td><c:out value="${currentOrder.coordinates}" /></td>
            <td><c:out value="${currentOrder.destinationPoint}" /></td>
            <td><c:out value="${currentOrder.comment}" /></td>

            <c:if test = "${currentOrder.driverId != '0'}">
            <p>Order have been accepted by driver<p>
            </c:if>

            <c:if test="${currentOrder.driverId == '0'}">
                <c:choose>
                <c:when test="${driverRequested == true}">
            <p>Driver has been selected, please, wait for acceptance <p>
            </c:when>
                <c:otherwise>
            <p>Driver hasn't been selected or denied your order<p>
            </c:otherwise>
            </c:choose>
                </c:if>
                <c:choose>
                <c:when test="${currentOrder.started == '0'}">
            <td><input type="hidden" value="${currentOrder.id}"  name="canceledOrder"/>
                <button type="submit">Cancel the order</button>
            </td>
            </c:when>
            <c:otherwise>
                <p>Your trip has been started</p>
            </c:otherwise>
            </c:choose>

            </form>
            </td>
            </tr>
        </table>
    </section></section>
</body>
</html>
