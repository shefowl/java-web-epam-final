<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 23.01.2020
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Your new order</title>
</head>
<body>
<section>
    <jsp:useBean id="ordered" scope="request" type="java.lang.Boolean"/>
    <c:if test="${!ordered}">
    <form method="post" action="app?action=newOrder">
        <dl>
            <dt>Destination address: </dt>
            <dd><input type="text" name="address"/></dd>
        </dl>
        <select name="class">
            <option value="budget">Budget</option>
            <option value="comfort">Comfort</option>
            <option value="business">Business</option>
        </select>
        <dl>
            <dt>Your comment: </dt>
            <dd><input type="text" name="comment"/></dd>
        </dl>
        <button type="submit">Order</button>
    </form>
    </c:if>
    <c:if test="${ordered}">
        <p>You already made the order</p>
    </c:if>
</section>
</body>
</html>
