<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 23.01.2020
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>--%>
<%--<img alt="logo" src="<html:rewrite page="" width="100%" height="20%"/>--%>


<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--<html>--%>
<%--<head>--%>
    <%--<title>--%>
        <%--<fmt:message key="title.main" bundle="${locale}"/>--%>
    <%--</title>--%>
    <%--<style><%@include file="../../../bootstrap-4.4.1-dist/css/bootstrap.min.css"%></style>--%>
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
<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="BUBER">
    <title>BUBER</title>
    <link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
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

    <section class="features" id="features">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="offer">
                        <h1 class="offer__title">
                            <fmt:message key="text.main.header" bundle="${locale}"/>
                        </h1>
                        <div class="offer__intro">
                            <fmt:message key="text.main.middle" bundle="${locale}"/>
                        </div>
                        <p class="offer__text">
                            <fmt:message key="text.main.description" bundle="${locale}"/>
                        </p>
                        <ul class="icons">
                            <li class="icons__item">
                                <form method="get" action="hello?action=sign_Up">
                                <button class="button__item">
                                    <fmt:message key="text.main.join" bundle="${locale}"/>
                                </button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="col-lg-5 ml-auto">
                    <img src="${pageContext.request.contextPath}/resources/page/img/iphone.png" class="car" alt="car picture">
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