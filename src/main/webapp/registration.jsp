<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 25.01.2020
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<section>
    <%--<jsp:useBean id="taxiParticipant" scope="request" type="by.epam.buber.entity.TaxiParticipantParticipant"/>--%>
    <form method="post" action="hello?action=signUp">
        <dl>
            <dt>Login: </dt>
            <dd><input type="text" name="name"/></dd>
        </dl>
        <dl>
            <dt>Password: </dt>
            <dd><input type="password" name="password"/></dd>
        </dl>
        <dl>
            <dt>Email: </dt>
            <dd><input type="email" name="email"/></dd>
        </dl>
        <dl>
            <dt>Phone number: </dt>
            <dd><input type="text" name="phone"/></dd>
        </dl>
        <button type="submit">Sign Up</button>
    </form>
</section>
</body>
</html>
