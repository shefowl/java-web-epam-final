<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 23.01.2020
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <style><%@include file="../../../bootstrap-4.4.1-dist/css/bootstrap.min.css"%></style>
    <style><%@include file="../../css/cover.css"%></style>
    <style><%@include file="../../css/main.css"%></style>
</head>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0">BUBER</a>
    <nav class="nav-buber nav-masthead justify-content-center">
        <a class="nav-link active" href="hello?action=main">Main page</a>
        <a class="nav-link" href="hello?action=participant_Page">User page</a>
        <a class="nav-link" href="hello?action=sign_Up">Sign Up</a>
        <a class="nav-link" href="hello?action=sign_In">Sign In</a>
    </nav>
</nav>

<body class="text-center">
</body>
</html>
