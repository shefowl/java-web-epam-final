<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 26.01.2020
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change password</title>
</head>
<body>
<section>
    <form method="post" action="app?action=changePassword">
        <dl>
            <dt>Your current password: </dt>
            <dd><input type="password" name="current"/></dd>
        </dl>
        <dl>
            <dt>Your new password: </dt>
            <dd><input type="password" name="new"/></dd>
        </dl>
        <dl>
            <dt>Repeat your new password: </dt>
            <dd><input type="password" name="repeatNew"/></dd>
        </dl>
        <button type="submit">Change your password</button>
    </form>
</section>
</body>
</html>
