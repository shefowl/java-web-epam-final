<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 26.01.2020
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Change password</title>--%>
    <%--<style><%@include file="../../../bootstrap-4.4.1-dist/css/bootstrap.min.css"%></style>--%>
    <%--<style><%@include file="../../css/cover.css"%></style>--%>
    <%--<style><%@include file="../../css/main.css"%></style>--%>
    <%--&lt;%&ndash;<style><%@include file="../../css/userPage.css"%></style>&ndash;%&gt;--%>
    <%--<style><%@include file="../../css/password.css"%></style>--%>
<%--</head>--%>
<%--<body>--%>
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

<%--<jsp:useBean id="error" scope="request" type="java.lang.Boolean"/>--%>
<%--<main role="main" class="inner cover">--%>
    <%--<form method="post" action="hello?action=change_Password" class="form-password">--%>
        <%--<h1 class="text-center"><fmt:message key="text.password.change" bundle="${locale}"/></h1>--%>

        <%--<label for="current" class="sr-only"><fmt:message key="text.password.current" bundle="${locale}"/></label>--%>
        <%--<input type="password" name="current" id="current" class="form-control" placeholder="--%>
<%--<fmt:message key="text.password.current.placeholder" bundle="${locale}"/>" required autofocus/>--%>

        <%--<label for="new" class="sr-only"><fmt:message key="text.password.new" bundle="${locale}"/></label>--%>
        <%--<input type="password" name="new" id="new" class="form-control" placeholder="--%>
<%--<fmt:message key="text.password.new.placeholder" bundle="${locale}"/>" required/>--%>

        <%--<label for="repeatNew" class="sr-only"><fmt:message key="text.password.repeat" bundle="${locale}"/></label>--%>
        <%--<input type="password" name="repeatNew" id="repeatNew" class="form-control" placeholder="--%>
<%--<fmt:message key="text.password.repeat.placeholder" bundle="${locale}"/>" required/>--%>

        <%--<button class="btn btn-lg btn-primary" type="submit">--%>
            <%--<fmt:message key="text.password.change.button" bundle="${locale}"/>--%>
        <%--</button>--%>
        <%--<c:if test="${error}">--%>
        <%--<h3 class="text-center-invalid">--%>
            <%--<fmt:message key="text.password.error" bundle="${locale}"/>--%>
        <%--</h3>--%>
        <%--</c:if>--%>
    <%--</form>--%>
<%--</main>--%>
<%--</body>--%>
<%--</html>--%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="BUBER">
    <title>BUBER</title>
    <link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/SignUp.css">
    <script src="${pageContext.request.contextPath}/resources/js/changePassword.js"></script>
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
                                <p> <a href="hello?action=sign_In">
                                    <fmt:message key="text.main.signIn" bundle="${locale}"/>
                                </a>
                                </p>
                            </li>
                            <li class="menu__item">
                                <a href="hello?action=sign_Up">
                                    <fmt:message key="text.main.signUp" bundle="${locale}"/>
                                </a>
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
    <jsp:useBean id="error" scope="request" type="java.lang.Boolean"/>
    <section class="features" id="features">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-sm-6 offset-lg-4 offset-sm-3">
                    <div class="authorization">

                        <div class="form__name">
                            <h2> <fmt:message key="text.signIn.please" bundle="${locale}"/></h2>
                            <hr width="100%" color="#fff" align="right" size="10">
                        </div>

                        <div class="info__fields">
                            <div class="valid__message" id="valid_message"> </div>
                            <!-- form -->
                            <form method="post" action="hello?action=change_Password" class="form__login">
                                <p class="field__passw">
                                    <label for="name" class="sr-only">
                                        <fmt:message key="text.password.current" bundle="${locale}"/>
                                    </label>
                                    <input type="password" name="passwordCurrent" id="name" class="form-control" placeholder=
                                            "<fmt:message key="text.password.current.placeholder" bundle="${locale}"/>" required autofocus/>
                                </p>

                                <p class="field__passw">
                                    <label for="password" class="sr-only">
                                        <fmt:message key="text.password.new" bundle="${locale}"/>
                                    </label>
                                    <input type="password" name="password" id="password" class="form-control" placeholder=
                                            "<fmt:message key="text.password.new.placeholder" bundle="${locale}"/>" required/>
                                </p>

                                <p class="field__passw">
                                    <label for="password" class="sr-only">
                                        <fmt:message key="text.password.new" bundle="${locale}"/>
                                    </label>
                                    <input type="password" name="password" id="passwordRepeat" class="form-control" placeholder=
                                            "<fmt:message key="text.password.new.placeholder" bundle="${locale}"/>" required/>
                                </p>

                                <input type="button" class="submit" name="button" id="ss" value=
                                        "<fmt:message key="text.password.change.button" bundle="${locale}"/>">

                            </form>

                        </div>
                        <div class="form__name">
                            <c:if test="${error}">
                                <h3 class="text-center-invalid">
                                    <fmt:message key="text.password.error" bundle="${locale}"/>
                                </h3>
                            </c:if>
                        </div>
                        <div class="new__user">
                            <ul>
                                <li><fmt:message key="text.signIn.new" bundle="${locale}"/></li>
                                <li>
                                    <a href="hello?action=sign_Up">
                                        <fmt:message key="text.signIn.create" bundle="${locale}"/>
                                    </a>
                                </li>
                            </ul>
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