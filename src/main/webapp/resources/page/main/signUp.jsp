
<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 25.01.2020
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>--%>
        <%--<fmt:message key="title.signUp" bundle="${locale}"/>--%>
    <%--</title>--%>
    <%--<style><%@include file="../../../bootstrap-4.4.1-dist/css/bootstrap.min.css"%></style>--%>
    <%--<style><%@include file="../../css/signUpForm.css"%></style>--%>
    <%--<style><%@include file="../../css/cover.css"%></style>--%>
        <%--<style><%@include file="../../css/main.css"%></style>--%>
<%--</head>--%>
<%--<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">--%>
    <%--<a class="navbar-brand col-sm-3 col-md-2 mr-0">BUBER</a>--%>
    <%--<a class="nav-link col-sm-3 col-md-3 mr-0" href="hello?action=change_Locale">EN|RU</a>--%>
    <%--<nav class="nav-buber nav-masthead justify-content-center">--%>
        <%--<a class="nav-link active" href="hello?action=main">--%>
            <%--<fmt:message key="text.main.mainPage" bundle="${locale}"/>--%>
        <%--</a>--%>
        <%--<a class="nav-link" href="hello?action=participant_Page">--%>
            <%--<fmt:message key="text.main.userPage" bundle="${locale}"/>--%>
        <%--</a>--%>
        <%--<a class="nav-link" href="hello?action=sign_Up">--%>
            <%--<fmt:message key="text.main.signUp" bundle="${locale}"/>--%>
        <%--</a>--%>
        <%--<a class="nav-link" href="hello?action=sign_In">--%>
            <%--<fmt:message key="text.main.signIn" bundle="${locale}"/>--%>
        <%--</a>--%>
    <%--</nav>--%>
<%--</nav>--%>

<%--<body class="text-center">--%>

<%--<main role="main" class="inner cover">--%>
    <%--<jsp:useBean id="exist" scope="request" type="java.lang.Boolean"/>--%>

    <%--<form method="post" action="hello?action=sign_Up" class="form-signin">--%>
    <%--<h1 class="text-center">--%>
        <%--<fmt:message key="text.signUp.enter" bundle="${locale}"/>--%>
    <%--</h1>--%>
    <%--<label for="name" class="sr-only">--%>
        <%--<fmt:message key="text.signUp.userName" bundle="${locale}"/>--%>
    <%--</label>--%>
    <%--<input type="text" name="name" id="name" class="form-control" placeholder="--%>
<%--<fmt:message key="text.signUp.userName.placeholder" bundle="${locale}"/>" required autofocus/>--%>

    <%--<label for="password" class="sr-only">--%>
        <%--<fmt:message key="text.signUp.password" bundle="${locale}"/>--%>
    <%--</label>--%>
    <%--<input type="password" name="password" id="password" class="form-control" placeholder="--%>
<%--<fmt:message key="text.signUp.password.placeholder" bundle="${locale}"/>" required/>--%>

    <%--<label for="email" class="sr-only">--%>
        <%--<fmt:message key="text.signUp.email" bundle="${locale}"/>--%>
    <%--</label>--%>
    <%--<input type="email" name="email" id="email" class="form-control" placeholder="--%>
<%--<fmt:message key="text.signUp.email.placeholder" bundle="${locale}"/>" required/>--%>

    <%--<label for="phone" class="sr-only">--%>
        <%--<fmt:message key="text.signUp.phone" bundle="${locale}"/>--%>
    <%--</label>--%>
    <%--<input type="tel" name="phone" id="phone" class="form-control" placeholder="--%>
<%--<fmt:message key="text.signUp.phone.placeholder" bundle="${locale}"/>" required/>--%>

    <%--<button class="btn btn-lg btn-primary" type="submit">--%>
        <%--<fmt:message key="text.signUp.button" bundle="${locale}"/>--%>
    <%--</button>--%>
    <%--<c:if test="${exist}">--%>
        <%--<h3 class="text-center-invalid">--%>
            <%--<fmt:message key="text.signUp.exist" bundle="${locale}"/>--%>
        <%--</h3>--%>
    <%--</c:if>--%>
<%--</form>--%>
<%--</main>--%>
<%--</body>--%>
<%--</html>--%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="BUBER">
    <title>BUBER</title>
    <link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/SignUp.css">
    <script src="${pageContext.request.contextPath}/resources/js/signUp.js"></script>
</head>
<body>
<div class="background">
    <header class="header" id="header">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <li class="logo">
                        <a class="logo__name" href="hello?action=main">BUBER</a>
                    </li>
                </div>
                <div class="col-lg-6 ml-auto">
                    <nav>
                        <ul class="menu d-flex justify-content-center ">
                            <li class="menu__item">
                                <a href="hello?action=participant_Page">
                                    <fmt:message key="text.main.mainPage" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="hello?action=sign_In">
                                    <fmt:message key="text.main.signIn" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <p>
                                <a href="hello?action=sign_Up">
                                    <fmt:message key="text.main.signUp" bundle="${locale}"/>
                                </a>
                                </p>
                            </li>
                            <li class="menu__item">
                                <a href="hello?action=change_Locale">
                                    EN|RU
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </header>

    <section class="features" id="features">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-sm-6 offset-lg-4 offset-sm-3">
                    <div class="authorization">

                        <div class="form__name">
                            <h2><fmt:message key="text.signUp.enter" bundle="${locale}"/></h2>
                            <hr width="100%" color="#fff" align="right" size="10">
                        </div>

                        <div class="info__fields">
                            <div class="valid__message" id="valid_message"> </div>
                            <!-- form -->
                            <form method="post" action="hello?action=sign_Up" class="form__login">
                                <p class="field__uname" data-title=
                                        "<fmt:message key="text.signUp.userName.title" bundle="${locale}"/>">
                                    <%--<input type="text" name="login" placeholder="Username">--%>
                                    <input type="text" name="name" id="name" class="form-control" placeholder=
                                            "<fmt:message key="text.signUp.userName.placeholder" bundle="${locale}"/>" required autofocus/>
                                    <!-- <i class="icon-user icon-large"></i>-->
                                </p>
                                <p class="field__passw" data-title="<fmt:message key="text.signUp.password.title" bundle="${locale}"/>">
                                    <%--<input type="text" name="password" placeholder="Password">--%>
                                    <input type="password" name="password" id="password" class="form-control" placeholder=
                                            "<fmt:message key="text.signUp.password.placeholder" bundle="${locale}"/>" required/>
                                    <!--<i class="icon-lock icon-large"></i>-->
                                </p>
                                <p class="field__mail">
                                    <%--<input type="text" name="e-mail" placeholder="E-mail">--%>
                                    <input type="email" name="email" id="email" class="form-control" placeholder=
                                            "<fmt:message key="text.signUp.email.placeholder" bundle="${locale}"/>" required/>

                                </p>
                                <p class="field__phone" data-title="<fmt:message key="text.signUp.phone.title" bundle="${locale}"/>">
                                    <input type="tel" name="phone" id="phone" class="form-control" placeholder=
                                            "<fmt:message key="text.signUp.phone.placeholder" bundle="${locale}"/>" required/>
                                </p>
                                <input type="submit" class="submit" name="button" id="ss" value=
                                        "<fmt:message key="text.signUp.button" bundle="${locale}"/>">
                                <%--<div class="submit">--%>
                                    <%--<button class="submit" type="submit">--%>
                                    <%--<fmt:message key="text.signUp.button" bundle="${locale}"/>--%>
                                    <%--</button>--%>
                                <%--</div>--%>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section class="touch" id="touch">
        <div class="container">

        </div>
    </section>

    <footer class="footer" id="footer">
        <div class="container">

        </div>
    </footer>
</div>
</body>
</html>