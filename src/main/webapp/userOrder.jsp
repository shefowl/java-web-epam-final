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
        <%--<jsp:useBean id ="driver" class="by.epam.buber.entity.participant.Driver" scope="request"/>--%>
        <table>
            <td>
            <td>
            <form method="post" action="driver?action=cancelOrder">
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
            <%--<c:if test="${currentOrder.driverId == '0'}">--%>
            <p>Driver hasn't been selected or denied your order<p>
            <%--</c:if>--%>
            </c:otherwise>
            </c:choose>
                </c:if>

            <%--<c:if test = "${currentOrder.driverId == '0'}">--%>
            <%--<p>Order isn't accepted by driver<p>--%>
            <%--</c:if>--%>
            <%--<c:if test = "${driverRequested == true}">--%>
            <%--<p>Driver has been selected, please, wait for acceptance <p>--%>
            <%--</c:if>--%>
            <%--<c:if test = "${driverRequested == false}">--%>
            <%--<p>Driver hasn't been selected or denied your order<p>--%>
            <%--</c:if>--%>
            <%--<c:if test = "${currentOrder.driverId != '0'}">--%>
            <%--<p>Order have been accepted by driver<p>--%>
            <%--</c:if>--%>
            <td><input type="hidden" value="${currentOrder.id}"  name="canceledOrder"/>
                    <button type="submit">Cancel the order</button>
                </td>
            </form>
            </td>
            </tr>
        </table>
    </section></section>
</body>
</html>
