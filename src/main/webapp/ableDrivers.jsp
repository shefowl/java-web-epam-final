<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 29.01.2020
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Able Drivers</title>
</head>
<body>
<section>
        <jsp:useBean id="drivers" scope="request" type="java.util.List"/>
        <jsp:useBean id="currentOrder" scope="request" type="by.epam.buber.entity.Order"/>
        <jsp:useBean id="prices" scope="request" type="java.util.List"/>
        <jsp:useBean id="driverRequested" scope="request" type="java.lang.Boolean"/>
    <td><c:out value="${currentOrder.coordinates}" /></td>
    <td><c:out value="${currentOrder.destinationPoint}" /></td>
    <%--<c:if test="${currentOrder.started}"--%>
    <c:if test="${currentOrder.driverId == '0' && !driverRequested}">
        <table>
            <c:forEach items="${drivers}" var="ableDriver" varStatus="status">
                <td>
                <td>
                    <form method="post" action="app?action=drivers">
                        <td><c:out value="${ableDriver.name}" /></td>
                        <td><c:out value="${ableDriver.email}" /></td>
                        <td>Price: </td>
                        <td><c:out value="${prices[status.index]}" /></td>
                <td><input type="hidden" value="${ableDriver.id}"  name="acceptedDriver"/>
                    <input type="hidden" value="${prices[status.index]}"  name="orderPrice"/>
                    <input type="hidden" value="${currentOrder.id}"  name="orderId"/>
                        <button type="submit">Choose</button>
                        </td>
                    </form>
                </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${currentOrder.driverId != '0' || driverRequested}">
        <p>Driver already has been requested</p>
    </c:if>
</section>
</body>
</html>
