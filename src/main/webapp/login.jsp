<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 25.01.2020
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<section>
    <%--<jsp:useBean id="taxiParticipant" scope="request" type="by.epam.buber.entity.TaxiParticipantParticipant"/>--%>
    <form method="post" action="hello?action=login">
        <dl>
            <dt>Login: </dt>
            <dd><input type="text" name="name"/></dd>
        </dl>
        <dl>
            <dt>Password: </dt>
            <dd><input type="password" name="password"/></dd>
        </dl>
        <button type="submit">Log In</button>
    </form>
</section>
</body>
</html>
