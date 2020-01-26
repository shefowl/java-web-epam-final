<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 23.01.2020
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your new order</title>
</head>
<body>
<section>
    <jsp:useBean id="order" scope="request" type="by.epam.buber.entity.Order"/>
    <form method="post" action="app?action=newOrder">
        <dl>
            <dt>ID: </dt>
            <dd><input type="number" name="id" value="${order.id}" placeholder="${order.id}" /></dd>
        </dl>
        <dl>
            <dt>Coordinates: </dt>
            <dd><input type="number" name="coordinates" value="${order.coordinates}" placeholder="${order.coordinates}" /></dd>
        </dl>
        <dl>
            <dt>Destination address: </dt>
            <dd><input type="text" name="address" value="${order.destinationPoint}"
                       placeholder="${order.destinationPoint}" /></dd>
        </dl>
        <button type="submit">Save</button>
    </form>
</section>
</body>
</html>
