<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 03.02.2020
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/driver.css">
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
                                <a href="driver?action=driver_Orders">
                                    <fmt:message key="text.driverPage.orders" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <div class="act">
                                <a href="driver?action=busy">
                                    <fmt:message key="text.driverPage.busy" bundle="${locale}"/>
                                </a>
                                </div>
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

            <jsp:useBean id="busy" scope="request" type="java.lang.Boolean"/>
            <div class="col-lg-3 col-sm-3 ml-left">
                <c:if test="${busy}">
                <form method="post" action="driver?action=free">
                <section class="features">
                    <input type="submit" class="free_status" name="button" value="I'm free">
                </section>
                </form>

                </c:if>
                <c:if test="${!busy}">
                <form method="post" action="driver?action=busy">
                    <section class="features">
                        <input type="submit" class="busy_status" name="button" value="I'm busy">
                    </section>
                </form>
                </c:if>
            </div>
        </div>
    </div>

</div>
</body>
</html>
