<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 03.02.2020
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin page</title>
    <style><%@include file="bootstrap-4.4.1-dist/css/bootstrap.min.css"%></style>
    <style><%@include file="resources/css/userPage.css"%></style>
    <style><%@include file="resources/css/ableDrivers.css"%></style>
</head>
<body>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0">BUBER</a>
</nav>

<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" href="admin?action=adminPage">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline></svg>
                    Dashboard <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="admin?action=getAll">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>
                    Show all participants
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="admin?action=getAllByName">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-clipboard"><circle cx="9" cy="21" r="1"></circle><circle cx="20" cy="21" r="1"></circle><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path></svg>
                    Find participants by name
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="admin?action=discount">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
                    Set discounts
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="admin?action=changePassword">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-settings"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg>
                    Change password
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="admin?action=driver">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-truck"><polygon points="12 2 2 7 12 12 22 7 12 2"></polygon><polyline points="2 17 12 22 22 17"></polyline><polyline points="2 12 12 17 22 12"></polyline></svg>
                    Register driver
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="admin?action=participantId">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-truck"><polygon points="12 2 2 7 12 12 22 7 12 2"></polygon><polyline points="2 17 12 22 22 17"></polyline><polyline points="2 12 12 17 22 12"></polyline></svg>
                    Find participant by ID
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="hello?action=logout">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-log-out"><polygon points="12 2 2 7 12 12 22 7 12 2"></polygon><polyline points="2 17 12 22 22 17"></polyline><polyline points="2 12 12 17 22 12"></polyline></svg>
                    Log out
                </a>
            </li>
        </ul>
    </div>
</nav>

<jsp:useBean id="participants" scope="request" type="java.util.List"/>
<%--<jsp:useBean id="orderAccepted" scope="request" type="java.lang.Boolean"/>--%>
<%--<jsp:useBean id ="driver" class="by.epam.buber.entity.participant.Driver" scope="request"/>--%>
    <div class="container">
        <table class="table">
            <thead>
            <tr class="ableDrivers">
                <th scope="col" class="scope">#</th>
                <th scope="col" class="scope">Name</th>
                <th scope="col" class="scope">Email</th>
                <th scope="col" class="scope">Role</th>
                <th scope="col" class="scope">Ban/Unban</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${participants}" var="participant" varStatus="status">
                <tr>
                    <th scope="row">${[status.index]}</th>
                    <form method="post" action="admin?action=ban" class="ableDrivers">
                        <th>
                            <div class="ableDrivers row">
                                <c:out value="${participant.name}" />
                            </div>
                        </th>
                        <th>
                            <div class="row">
                                <c:out value="${participant.email}" />
                            </div>
                        </th>
                        <th>
                            <div class="row">
                                <c:out value="${participant.role}"/>
                            </div>
                        </th>
                                <c:if test="${!participant.banned}">
                                <td>
                                <button class="btn btn-lg btn-success" type="submit">Ban</button>
                                </td>
                                    <input type="hidden" value="${participant.banned}"  name="isBanned"/>
                                <input type="hidden" value="${participant.id}"  name="participant"/>
                                </c:if>
                        <c:if test="${participant.banned}">
                            <td>
                                <button class="btn btn-lg btn-success" type="submit">Unban</button>
                            </td>
                            <input type="hidden" value="${participant.banned}"  name="isBanned"/>
                            <input type="hidden" value="${participant.id}"  name="participant"/>
                        </c:if>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>