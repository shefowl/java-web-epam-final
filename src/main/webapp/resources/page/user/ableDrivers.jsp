<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 29.01.2020
  Time: 0:21
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/user_showDrivers.css">
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
            <div class="col-lg-5 col-sm-5 ml-left">
                <jsp:useBean id="ordered" scope="request" type="java.lang.Boolean"/>
                <c:if test="${ordered}">
                    <jsp:useBean id="currentOrder" scope="request" type="by.epam.buber.entity.Order"/>
                    <jsp:useBean id="drivers" scope="request" type="java.util.List"/>
                    <jsp:useBean id="prices" scope="request" type="java.util.List"/>

                    <c:forEach items="${drivers}" var="ableDriver" varStatus="status">
                <form method="post" action="app?action=drivers">
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
                                    <th><fmt:message key="text.ableDrivers.name" bundle="${locale}"/></th>
                                    <th><fmt:message key="text.ableDrivers.email" bundle="${locale}"/></th>
                                    <th><fmt:message key="text.ableDrivers.price" bundle="${locale}"/></th>
                                    <%--<th id="btn"> </th>--%>
                                </tr>

                                <tr>
                                    <td><c:out value="${ableDriver.name}" /></td>
                                    <td><c:out value="${ableDriver.email}" /></td>
                                    <td><c:out value="${prices[status.index]}"/></td>
                                    <td id="btn"> <input type="submit" class="submit" name="button" id="ss" value=
                                            "<fmt:message key="text.ableDrivers.accept.button" bundle="${locale}"/>"></td>
                                </tr>
                                <input type="hidden" value="${ableDriver.id}"  name="acceptedDriver"/>
                                <input type="hidden" value="${prices[status.index]}"  name="orderPrice"/>
                                <input type="hidden" value="${currentOrder.id}"  name="orderId"/>
                            <%--<tr>--%>
                                    <%--<td width="30%">Jim</td>--%>
                                    <%--<td width="30%">2</td>--%>
                                    <%--<td width="30%">3</td>--%>
                                    <%--<td id="btn"> <input type="button" class="submit" name="button" id="ss" value="submit"></td>--%>
                                <%--</tr>--%>
                            </table>
                        </div>
                    </div>
                </section>
                </form>
                    </c:forEach>
                </c:if>
            </div>

        </div>
    </div>

</div>
</body>
</html>