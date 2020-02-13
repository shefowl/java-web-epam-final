<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 27.01.2020
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Driver page</title>--%>
    <%--<style><%@include file="../../../bootstrap-4.4.1-dist/css/bootstrap.min.css"%></style>--%>
    <%--<style><%@include file="../../css/userPage.css"%></style>--%>
    <%--<style><%@include file="../../css/ableDrivers.css"%></style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">--%>
    <%--<a class="navbar-brand col-sm-3 col-md-2 mr-0">BUBER</a>--%>
<%--</nav>--%>

<%--<nav class="col-md-2 d-none d-md-block bg-light sidebar">--%>
    <%--<div class="sidebar-sticky">--%>
        <%--<ul class="nav flex-column">--%>
            <%--<li class="nav-item">--%>
                <%--<a class="nav-link" href="driver?action=driver_Page">--%>
                    <%--<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline></svg>--%>
                    <%--Dashboard <span class="sr-only">(current)</span>--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li class="nav-item">--%>
                <%--<a class="nav-link active" href="driver?action=driver_Orders">--%>
                    <%--<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>--%>
                    <%--Show orders--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li class="nav-item">--%>
                <%--<a class="nav-link" href="driver?action=busy">--%>
                    <%--<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>--%>
                    <%--Busy/Free--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li class="nav-item">--%>
                <%--<a class="nav-link" href="driver?action=current_Order">--%>
                    <%--<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-clipboard"><circle cx="9" cy="21" r="1"></circle><circle cx="20" cy="21" r="1"></circle><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path></svg>--%>
                    <%--Show current order--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li class="nav-item">--%>
                <%--<a class="nav-link" href="driver?action=change_Name">--%>
                    <%--<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>--%>
                    <%--Change username--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li class="nav-item">--%>
                <%--<a class="nav-link" href="driver?action=change_Password">--%>
                    <%--<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-settings"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg>--%>
                    <%--Change password--%>
                <%--</a>--%>
            <%--</li>--%>
            <%--<li class="nav-item">--%>
                <%--<a class="nav-link" href="hello?action=logout">--%>
                    <%--<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-log-out"><polygon points="12 2 2 7 12 12 22 7 12 2"></polygon><polyline points="2 17 12 22 22 17"></polyline><polyline points="2 12 12 17 22 12"></polyline></svg>--%>
                    <%--Log out--%>
                <%--</a>--%>
            <%--</li>--%>
        <%--</ul>--%>
    <%--</div>--%>
<%--</nav>--%>

        <%--<jsp:useBean id="orders" scope="request" type="java.util.List"/>--%>
        <%--<jsp:useBean id="orderAccepted" scope="request" type="java.lang.Boolean"/>--%>
        <%--<jsp:useBean id="noOrders" scope="request" type="java.lang.Boolean"/>--%>

<%--<c:if test="${!noOrders}">--%>
        <%--<c:if test="${!orderAccepted}">--%>
            <%--<div class="container">--%>
                <%--<table class="table">--%>
                    <%--<thead>--%>
                    <%--<tr class="ableDrivers">--%>
                        <%--<th scope="col" class="scope">#</th>--%>
                        <%--<th scope="col" class="scope">Coordinates</th>--%>
                        <%--<th scope="col" class="scope">Address</th>--%>
                        <%--<th scope="col" class="scope">Comment</th>--%>
                        <%--<th scope="col" class="scope">Accept</th>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody>--%>
                    <%--<c:forEach items="${orders}" var="currentOrder" varStatus="status">--%>
                        <%--<tr>--%>
                            <%--<th scope="row">${[status.index]}</th>--%>
                            <%--<form method="post" action="driver?action=accept_Order" class="ableDrivers">--%>
                                    <%--&lt;%&ndash;<div class="col">&ndash;%&gt;--%>
                                <%--<th>--%>
                                    <%--<div class="ableDrivers row">--%>
                                        <%--<c:out value="${currentOrder.coordinates}" />--%>
                                    <%--</div>--%>
                                <%--</th>--%>
                                <%--<th>--%>
                                    <%--<div class="row">--%>
                                        <%--<c:out value="${currentOrder.destinationPoint}" />--%>
                                    <%--</div>--%>
                                <%--</th>--%>
                                <%--<th>--%>
                                    <%--<div class="row">--%>
                                        <%--<c:out value="${currentOrder.comment}"/>--%>
                                    <%--</div>--%>
                                <%--</th>--%>
                                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--<td>--%>
                                    <%--<button class="btn btn-lg btn-success" type="submit">Accept</button>--%>
                                <%--</td>--%>
                                <%--<input type="hidden" value="${currentOrder.id}"  name="acceptedOrder"/>--%>
                            <%--</form>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>
                    <%--</tbody>--%>
                <%--</table>--%>
            <%--</div>--%>
        <%--</c:if>--%>
<%--</c:if>--%>
<%--<c:if test="${noOrders}">--%>
    <%--<div class="current-order">--%>
    <%--<h1 class="header-text-center-red">No orders</h1>--%>
    <%--</div>--%>
<%--</c:if>--%>
<%--</body>--%>
<%--</html>--%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="BUBER">
    <title>userBuber</title>
    <link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/driver.css">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user_showDrivers.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/status.js"></script>
</head>
<body>
<div class="background">

    <!-- floating menu -->
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <header class="header" id="header">
                    <li class="logo">
                        <a class="logo__name" href="#">BUBER</a>
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
                                    <a href="driver?action=driver_Page">
                                        <fmt:message key="text.driverPage.main" bundle="${locale}"/>
                                    </a>
                                </li>
                            <li class="menu__item">
                                <div class="act">
                                <a href="driver?action=driver_Orders">
                                    <fmt:message key="text.driverPage.orders" bundle="${locale}"/>
                                </a>
                                </div>
                            </li>
                            <li class="menu__item">
                                <a href="driver?action=busy">
                                    <fmt:message key="text.driverPage.busy" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="driver?action=current_Order">
                                    <fmt:message key="text.driverPage.currentOrder" bundle="${locale}"/>
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
                                <a href="hello?action=logout">
                                    <fmt:message key="text.userPage.logout" bundle="${locale}"/>
                                </a>
                            </li>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-5 col-sm-5 ml-left">
                <jsp:useBean id="noOrders" scope="request" type="java.lang.Boolean"/>
                <jsp:useBean id="orderAccepted" scope="request" type="java.lang.Boolean"/>

                <c:if test="${!noOrders}">
                <c:if test="${!orderAccepted}">

                <jsp:useBean id="orders" scope="request" type="java.util.List"/>

                    <c:forEach items="${orders}" var="currentOrder" varStatus="status">
                        <form method="post" action="driver?action=accept_Order">
                            <section class="features" id="features">
                                <div class="Make an order">

                                    <div class="form__name">
                                        <h2><fmt:message key="text.ableDrivers.head" bundle="${locale}"/></h2>
                                        <hr width="100%" color="#fff" align="right" size="10">
                                    </div>

                                    <div class="drivers_table d-flex justify-content-center">
                                        <!-- Table -->
                                        <table width="70%">
                                            <tr>
                                                <th><fmt:message key="text.driverOrders.coordinates" bundle="${locale}"/></th>
                                                <th><fmt:message key="text.driverOrders.address" bundle="${locale}"/></th>
                                                <th><fmt:message key="text.driverOrders.comment" bundle="${locale}"/></th>
                                                    <%--<th id="btn"> </th>--%>
                                            </tr>

                                            <tr>
                                                <td><c:out value="${currentOrder.coordinates}" /></td>
                                                <td><c:out value="${currentOrder.destinationPoint}" /></td>
                                                <td><c:out value="${currentOrder.comment}"/></td>
                                                <td id="btn"> <input type="submit" class="submit" name="button" id="ss" value=
                                                        "<fmt:message key="text.driverOrders.accept.button" bundle="${locale}"/>"></td>
                                            </tr>
                                            <input type="hidden" value="${currentOrder.id}"  name="acceptedOrder"/>
                                        </table>
                                    </div>
                                </div>
                            </section>
                        </form>
                    </c:forEach>
                </c:if>
                </c:if>
            </div>

        </div>
    </div>

</div>
</body>
</html>