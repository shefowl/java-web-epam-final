<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 12.02.2020
  Time: 1:03
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/admin_SearchResults.css">
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
                                <div class="act">
                                    <a href="admin?action=admin_Page">
                                        <fmt:message key="text.adminPage.main" bundle="${locale}"/>
                                    </a>
                                </div>
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
                                <a href="admin?action=new_Driver">
                                    <fmt:message key="text.adminPage.driver" bundle="${locale}"/>
                                </a>
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

            <jsp:useBean id="participants" scope="request" type="java.util.List"/>
            <jsp:useBean id="notesPerPage" scope="request" type="java.lang.Integer"/>
            <jsp:useBean id="begin" scope="request" type="java.lang.Integer"/>
            <jsp:useBean id="numberOfNotes" scope="request" type="java.lang.Integer"/>
            <div class="col-lg-5 col-sm-5 ml-left">
                <section class="features" id="features">

                    <div class="form__name">
                        <h2> <fmt:message key="text.adminSearchResults" bundle="${locale}"/></h2>
                        <hr width="100%" color="#fff" align="right" size="10">
                    </div>
                    <c:if test="${begin > 0}">
                        <form method="get">
                            <input type="hidden" value="get_All"  name="action"/>
                            <input type="hidden" value="${begin - notesPerPage}"  name="begin"/>
                            <button type="submit" class="btn btn-lg btn-success" name="button" id="ss" value="prev"></button>
                        </form>
                    </c:if>
                    <c:if test="${begin < numberOfNotes - 1}">
                        <form method="get">
                            <input type="hidden" value="get_All"  name="action"/>
                            <input type="hidden" value="${begin + notesPerPage}"  name="begin"/>
                            <button type="submit" class="btn btn-lg btn-success" name="button" id="ss" value="next"></button>
                        </form>
                    </c:if>
                    <div class="Make an order">



                        <div class="drivers_table d-flex justify-content-center">
                            <!-- Table -->

                                <table width="100%">
                                    <tr>
                                        <th><fmt:message key="text.adminSearchResults.name" bundle="${locale}"/></th>
                                        <th><fmt:message key="text.adminSearchResults.email" bundle="${locale}"/></th>
                                        <th><fmt:message key="text.adminSearchResults.role" bundle="${locale}"/></th>

                                        <%--<th id="btn">--%>
                                            <%--<input type="button" class="previous" name="button" id="ss" value="←prev">--%>
                                        <%--</th>--%>
                                        <%--<th id="btn">--%>
                                            <%--<input type="button" class="next" name="button" id="ss" value="next→" img>--%>
                                        <%--</th>--%>
                                    </tr>
                                    <c:forEach items="${participants}" var="participant" varStatus="status" begin="${begin}" end="${begin + notesPerPage - 1}">
                                    <tr>
                                        <form method="post" action="admin?action=ban">

                                        <td width="30%"><c:out value="${participant.name}" /></td>
                                        <td width="30%"><c:out value="${participant.email}" /></td>
                                        <td width="30%"><c:out value="${participant.role}" /></td>
                                        <c:if test="${!participant.banned}">
                                        <td id="btn"> <input type="submit" class="ban" name="button" id="ban" value="ban"></td>
                                            <input type="hidden" value="${participant.banned}"  name="isBanned"/>
                                            <input type="hidden" value="${participant.id}"  name="participant"/>
                                        </c:if>
                                        <c:if test="${participant.banned}">
                                            <td id="btn"> <input type="submit" class="ban" name="button" id="unban" value="unban"></td>
                                            <input type="hidden" value="${participant.banned}"  name="isBanned"/>
                                            <input type="hidden" value="${participant.id}"  name="participant"/>
                                        </c:if>
                                        </form>
                                    </tr>
                                    </c:forEach>
                                </table>

                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>
</body>
</html>