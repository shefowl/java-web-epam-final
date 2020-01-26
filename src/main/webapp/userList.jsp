<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 25.01.2020
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<section>
    <h3>Order info</h3>
    <jsp:useBean id="user" scope="request" type="by.epam.buber.entity.User"/>
    <tr>
        <td>ID: ${user.id} | Name: ${user.name} | Password: ${user.password}</td>
        <td><a href="hello?action=update">Make order</a></td>
    </tr>
    <br>
    <td><a href="hello?action=orders">Make order</a></td>
</section>
</body>
</html>
