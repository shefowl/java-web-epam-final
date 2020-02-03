<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 04.02.2020
  Time: 0:38
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
    <style><%@include file="resources/css/newDriver.css"%></style>
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
            <li class="nav-item">
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
                <a class="nav-link active" href="admin?action=driver">
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

<div class="col-md-8 order-md-1">
<form method="post" class="needs-validation" action="admin?action=driver">
<div class="row">
    <div class="col-md-6 mb-3">
        <label for="name" class="text-center">Name</label>
        <input type="text" name="name" class="form-control" id="name" placeholder="Name">
        <%--<div class="invalid-feedback">--%>
            <%--Valid first name is required.--%>
        <%--</div>--%>
    </div>
    <div class="col-md-6 mb-3">
        <label for="password">Last name</label>
        <input type="password" name="password" class="form-control" id="password" placeholder="Password">
        <%--<div class="invalid-feedback">--%>
            <%--Valid last name is required.--%>
        <%--</div>--%>
    </div>
</div>

<div class="row">
    <div class="col-md-6 mb-3">
        <label for="email">Email</label>
        <input type="email" name="email" class="form-control" id="email" placeholder="Email">
        <%--<div class="invalid-feedback">--%>
            <%--Valid first name is required.--%>
        <%--</div>--%>
    </div>
    <div class="col-md-6 mb-3">
        <label for="phone">Phone</label>
        <input type="tel" name="phone" class="form-control" id="phone" placeholder="Phone">
        <%--<div class="invalid-feedback">--%>
            <%--Valid last name is required.--%>
        <%--</div>--%>
    </div>
</div>

<h4 class="mb-3">Car</h4>
<div class="row">
    <div class="col-md-6 mb-3">
        <label for="mark">Mark</label>
        <input type="text" name="mark" class="form-control" id="mark" placeholder="Mark">
        <%--<div class="invalid-feedback">--%>
            <%--Valid first name is required.--%>
        <%--</div>--%>
    </div>
    <div class="col-md-6 mb-3">
        <label for="model">Model</label>
        <input type="text" name="model" class="form-control" id="model" placeholder="Model">
        <%--<div class="invalid-feedback">--%>
            <%--Valid last name is required.--%>
        <%--</div>--%>
    </div>
    <div class="col-md-6 mb-3">
        <label for="price">Price per km</label>
        <input type="number" name="price" class="form-control" id="price" placeholder="Price">
        <%--<div class="invalid-feedback">--%>
        <%--Valid last name is required.--%>
        <%--</div>--%>
    </div>
    <div class="d-block my-3">
        <div class="custom-control custom-radio">
            <input id="budget" name=carClass type="radio" value="budget" class="custom-control-input">
            <label class="custom-control-label" for="budget">Budget</label>
        </div>
        <div class="custom-control custom-radio">
            <input id="comfort" name="carClass" type="radio" value="comfort" class="custom-control-input">
            <label class="custom-control-label" for="comfort">Comfort</label>
        </div>
        <div class="custom-control custom-radio">
            <input id="business" name=carClass type="radio" value="business" class="custom-control-input">
            <label class="custom-control-label" for="business">Business</label>
        </div>
    </div>
    <button class="btn btn-primary btn-lg btn-block" type="submit">Create</button>
</div>
</form>
</div>
</body>
</html>
