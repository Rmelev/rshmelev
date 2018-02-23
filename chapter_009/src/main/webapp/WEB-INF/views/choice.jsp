<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.sepoperservlet.MyDataSource" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https:////netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <title>User's operation choice</title>
    <style>
        table, th, td {
            border: 2px solid black;
            border-collapse: collapse;
        }
        th, td {
            width: 110px;
            height: 30px;
        }
    </style>
    <%--<script type="text/javascript">--%>
        <%--$(document).ready(function getUsers() {--%>
            <%--$.ajax('./json', {--%>
                <%--method : 'get',--%>
                <%--dataType : "json",--%>
                <%--success: function(data) {--%>
                    <%--$.each(data, function(i, el) {--%>
                        <%--document.getElementById("yes").innerHTML += "<tbody><tr>"--%>
                            <%--+ "<td>" + el.name + "</td>"--%>
                            <%--+ "<td>" + el.login + "</td>"--%>
                            <%--+ "<td>" + el.email + "</td>"--%>
                            <%--+ "<td>" + el.createDate + "</td>"--%>
                            <%--+ "<td>" + el.role + "</td>"--%>
                            <%--+ "<td>" + el.country + "</td>"--%>
                            <%--+ "<td>" + el.city + "</td>"--%>
                            <%--+ "</tbody></tr>";--%>
                    <%--});--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>
<%--    </script>--%>
</head>
<body align="center">
<form action='${pageContext.servletContext.contextPath}/quit'>
    <input type='submit' value="LogOut">
</form>

<c:if test="${sessionScope.role eq 'admin'}">
    <c:import url="adminEdit.jsp"/>
</c:if>
<c:if test="${sessionScope.role eq 'user'}">
    <c:import url="userEdit.jsp"/>
    <%--<jsp:include page="userEdit.jsp" />--%>
</c:if>
<table id="yes" align="center">
    <tr style="background-color: #4dc8ff">
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>createDate</th>
        <th>role</th>
        <th>country</th>
        <th>city</th>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr height="25" style="background-color: #a3ff7d">
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.createDate}"/></td>
        <td><c:out value="${user.role}"/></td>
        <td><c:out value="${user.country}"/></td>
        <td><c:out value="${user.city}"/></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>