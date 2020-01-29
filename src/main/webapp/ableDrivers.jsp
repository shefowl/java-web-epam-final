<%--
  Created by IntelliJ IDEA.
  User: Lesha
  Date: 29.01.2020
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Able Drivers</title>
</head>
<body>
<section>
        <jsp:useBean id="drivers" scope="request" type="java.util.List"/>
        <%--<jsp:useBean id ="driver" class="by.epam.buber.entity.participant.Driver" scope="request"/>--%>
        <table>
            <c:forEach items="${drivers}" var="ableDriver">
                <td>
                <td>
                    <form method="post" action="app?action=drivers">
                        <td><c:out value="${ableDriver.name}" /></td>
                        <td><c:out value="${ableDriver.email}" /></td>
                        <td><input type="hidden" value="${ableDriver.id}"  name="acceptedDriver"/>
                        <button type="submit">Choose</button>
                        </td>
                    </form>
                </td>
                </tr>
            </c:forEach>
        </table>
</section>
</body>
</html>
