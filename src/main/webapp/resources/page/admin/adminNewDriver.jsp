<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 04.02.2020
  Time: 0:38
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
    <title>driverBuber</title>
    <link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu:500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin.css">
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
                                <a href="admin?action=admin_Page">
                                    <fmt:message key="text.adminPage.main" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="admin?action=get_All">
                                    <fmt:message key="text.adminPage.showAll" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="admin?action=get_All_By_Name">
                                    <fmt:message key="text.adminPage.findByName" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="admin?action=participant_Id">
                                    <fmt:message key="text.adminPage.findById" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <a href="admin?action=discount">
                                    <fmt:message key="text.adminPage.discounts" bundle="${locale}"/>
                                </a>
                            </li>
                            <%--<li class="menu__item">--%>
                                <%--<a href="app?action=change_Name">--%>
                                    <%--<fmt:message key="text.userPage.username" bundle="${locale}"/>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <li class="menu__item">
                                <a href="app?action=change_Password">
                                    <fmt:message key="text.userPage.password" bundle="${locale}"/>
                                </a>
                            </li>
                            <li class="menu__item">
                                <div class="act">
                                    <a href="admin?action=new_Driver">
                                        <fmt:message key="text.adminPage.driver" bundle="${locale}"/>
                                    </a>
                                </div>
                            </li>
                            <li class="menu__item">
                                <a href="hello?action=logout">
                                    <fmt:message key="text.adminPage.logout" bundle="${locale}"/>
                                </a>
                            </li>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-xl-5 col-lg-6 col-sm-4" ml-auto>
                <div class="authorization">

                    <div class="form__name">
                        <h2><fmt:message key="text.adminNewDriver.head" bundle="${locale}"/></h2>
                        <hr width="100%" color="#000" align="right" size="10">
                    </div>


                    <div class="info__fields">

                        <div class="valid__message" id="valid_message">  </div>

                        <!-- form -->

                        <form method="post" action="admin?action=new_Driver" class="form__login">
                            <nav class="book">
                                <div class="page1">
                                    <p class="part__name"><fmt:message key="text.adminNewDriver.driverInfo" bundle="${locale}"/></p>
                                    <p class="field">
                                        <input type="text" name="name" id ="name" placeholder=
                                                "<fmt:message key="text.adminNewDriver.name.placeholder" bundle="${locale}"/>">
                                        <!-- <i class="icon-user icon-large"></i>-->
                                    </p>
                                    <p class="field">
                                        <input type="email" name="email" id="mail" placeholder=
                                                "<fmt:message key="text.adminNewDriver.email" bundle="${locale}"/>">
                                    </p>

                                    <p class="field" data-title="The password must contain at least four symbols.">
                                        <input type="password" name="password" id="password" placeholder=
                                                "<fmt:message key="text.adminNewDriver.password.placeholder" bundle="${locale}"/>">
                                        <!--<i class="icon-lock icon-large"></i>-->
                                    </p>
                                    <p class="field">
                                        <input type="tel" name="phone" id="phone" placeholder=
                                                "<fmt:message key="text.adminNewDriver.phone.placeholder" bundle="${locale}"/>">
                                    </p>


                                </div>


                                <!-- new page -->
                                <div class="page2">

                                    <!-- form -->
                                    <p class="part__name"><fmt:message key="text.adminNewDriver.carInfo" bundle="${locale}"/></p>
                                    <p class="field">
                                        <input type="text" name="mark" id="mark" placeholder=
                                                "<fmt:message key="text.adminNewDriver.mark" bundle="${locale}"/>">
                                    </p>
                                    <p class="field">
                                        <input type="text" name="model" id="model" placeholder=
                                                "<fmt:message key="text.adminNewDriver.model" bundle="${locale}"/>">
                                    </p>
                                    <p class="field">
                                        <input type="number" name="pricePerKm" id="pricePerKm" placeholder=
                                                "<fmt:message key="text.adminNewDriver.pricePerKm" bundle="${locale}"/>">
                                    </p>
                                    <select name="carClass" class="custom-select d-block w-100" id="carClass">
                                        <option value="budget"><fmt:message key="text.newOrder.carClass.budget" bundle="${locale}"/></option>
                                        <option value="comfort"><fmt:message key="text.newOrder.carClass.comfort" bundle="${locale}"/></option>
                                        <option value="business"><fmt:message key="text.newOrder.carClass.business" bundle="${locale}"/></option>
                                    </select>
                                    <%--<p class="field_checkbox"><input type="radio" name="buberX" value="buberX">BuberX</p>--%>
                                    <%--<p class="field_checkbox"><input type="radio" name="buberSelect" value="buberSelect">Buber Select</p>--%>
                                    <%--<p class="field_checkbox"><input type="radio" name="black" value="black">Black</p>--%>

                                </div>
                            </nav>

                            <p class="submition">
                                <button type="submit" class="submit" name="submit">
                                    <fmt:message key="text.adminNewDriver.create.button" bundle="${locale}"/></button>
                            </p>
                        </form>

                    </div>
                </div>
            </div>
        </div>
        </div>
        </div>
</body>
</html>