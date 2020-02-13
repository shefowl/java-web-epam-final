<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 25.01.2020
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="BUBER">
    <title>userBuber</title>
    <link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/user.js"></script>
</head>
<body>
<div class="background">

    <!-- floating menu -->
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <header class="header" id="header">
                    <li class="logo">
                        <a class="logo__name" href="hello?action=main">BUBER</a>
                    </li>
                </header>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-4 col-sm-4">
                <div class="wrapper">
                    <div class="menu" id="menu">
                        <a href="#" class="buttonMenu" id="buttonMenu"></a>
                        <div class="menu__list">

                            <li class="menu__item">
                                <a href="app?action=user_page">
                                    <fmt:message key="text.userPage.main" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="app?action=new_Order">
                                    <fmt:message key="text.userPage.makeOrder" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="app?action=user_Order">
                                    <fmt:message key="text.userPage.currentOrder" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="app?action=change_Name">
                                    <fmt:message key="text.userPage.username" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="app?action=change_Password">
                                    <fmt:message key="text.userPage.password" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="app?action=drivers">
                                    <fmt:message key="text.userPage.drivers" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="hello?action=logout">
                                    <fmt:message key="text.userPage.logout" bundle="${locale}"/>
                                </a>
                            </li>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-sm-3 ml-left">
                <section class="features" id="features">
                    <div class="Make an order">

                        <div class="form__name">
                            <h2><fmt:message key="text.newOrder.makeOrder" bundle="${locale}"/></h2>
                            <hr width="100%" color="#fff" align="right" size="10">
                        </div>

                        <div class="info__fields d-flex justify-content-center">
                            <!-- form -->
                            <form method="post" action="app?action=new_Order" class="form__order">
                                <p class="field">
                                    <input type="text" name="address" id="address" placeholder=
                                            "<fmt:message key="text.newOrder.address.placeholder" bundle="${locale}"/>">
                                </p>
                                <p class="field">
                                    <select name="class" id="class">
                                        <option value="budget"><fmt:message key="text.newOrder.carClass.budget" bundle="${locale}"/></option>
                                        <option value="comfort"><fmt:message key="text.newOrder.carClass.comfort" bundle="${locale}"/></option>
                                        <option value="business"><fmt:message key="text.newOrder.carClass.business" bundle="${locale}"/></option>
                                    </select>
                                </p>
                                <p class="field">
                                    <textarea rows="3" cols="30" name="comment" id="comment" placeholder="<fmt:message key="text.newOrder.comment.placeholder" bundle="${locale}"/>"></textarea>
                                </p>
                                <p class="submit">
                                    <input type="submit" class="submit" name="button" id="ss" value=
                                            "<fmt:message key="text.newOrder.makeOrder.button" bundle="${locale}"/>">
                                </p>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>

</div>
</body>
</html>