<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 26.01.2020
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<section>
    <form method="post" action="app?action=changeName">
        <dl>
            <dt>Login: </dt>
            <dd><input type="text" name="name"/></dd>
        </dl>
        <button type="submit">Change your name</button>
    </form>
</section>
</body>
</html>
