<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 27.01.2020
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<section>
<tr>
    <jsp:useBean id="orders" scope="request" type="java.util.List"/>
    <td>Name: <c:out value="${sessionScope.userName}"/></td>
    <table>
        <c:forEach items="${orders}" var="order">
            <tr>
                <form method="post" action="driver?action=accept">
                <td><c:out value="${order.coordinates}" /></td>
                    <td><c:out value="${order.destinationPoint}" /></td>
                    <input type="hidden" value="${order}" name="acceptedOrder"/>
                    <button type="submit">Sign Up</button>
                </form>
            </tr>
        </c:forEach>
    </table>
</tr>
</section>
</body>
</html>
