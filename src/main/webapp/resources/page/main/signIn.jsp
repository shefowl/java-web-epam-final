<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 25.01.2020
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%--<link href="bootstrap-4.4.1-dist/css/bootstrap.min.css" rel="stylesheet">--%>
    <%--<link href="resources/css/signIn.css" type="text/css" rel="stylesheet">--%>
        <style><%@include file="../../../bootstrap-4.4.1-dist/css/bootstrap.min.css"%></style>
        <style><%@include file="../../css/signInForm.css"%></style>
        <style><%@include file="../../css/cover.css"%></style>
        <style><%@include file="../../css/main.css"%></style>
</head>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0">BUBER</a>
    <nav class="nav-buber nav-masthead justify-content-center">
        <a class="nav-link" href="hello?action=main">Main page</a>
        <a class="nav-link" href="hello?action=sign_Up">Sign Up</a>
        <a class="nav-link active" href="hello?action=signIn">Sign In</a>
    </nav>
</nav>
<body class="text-center">
<main role="main" class="inner cover">
    <form method="post" action="hello?action=sign_In" class="form-signin">
        <h1 class="text-center">Please sign in</h1>

        <label for="name" class="sr-only">Username</label>
            <input type="text" name="name" id="name" class="form-control" placeholder="Username" required autofocus/>

        <label for="password" class="sr-only">Password</label>
            <input type="password" name="password" id="password" class="form-control" placeholder="Password" required/>

        <button class="btn btn-lg btn-primary" type="submit">Sign in</button>
    </form>
</main>
</body>
</html>
