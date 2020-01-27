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
    <%--<jsp:useBean id="order" scope="request" type="by.epam.buber.entity.Order"/>--%>
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
</section>
</body>
</html>
