<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <title>Filters</title>
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
</head>
<body>

<form  align="center" action='${pageContext.servletContext.contextPath}/rolefilter'>
    <a align="center" href='${pageContext.servletContext.contextPath}/'>Back to Main Page</a><br/><br/>
    Role <select id="role" name='role'/>
    <option selected>unindefinite</option>
    <option>USER</option>
    <option>MANDATOR</option>
    <option>ADMIN</option>
    </select>
    <input type='submit' value="Filter">
    <form  action='${pageContext.servletContext.contextPath}/musictypefilter'>
        <input type='submit' value="Clean results"></form>
</form>

<form  align="center" action='${pageContext.servletContext.contextPath}/musictypefilter'>
    Music Type <select id="mtype" name='mtype'/><c:out value="${requestScope.mtype}"/>
    <option selected>unindefinite</option>
    <option>RAP</option>
    <option>ROCK</option>
    <option>JAZZ</option>
    <option>SWING</option>
    </select>
    <input type='submit' value="Filter">
    <form  action='${pageContext.servletContext.contextPath}/musictypefilter'>
        <input type='submit' value="Clean results"></form>
</form>
<c:if test="${not empty requestScope.mtype}">
    <p align="center">Results for:&nbsp &nbsp<c:out value="${requestScope.mtype}"/></p>
</c:if>
<c:if test="${not empty requestScope.role}">
    <p align="center">Results for:&nbsp &nbsp<c:out value="${requestScope.role}"/></p>
</c:if>

<table id="yes" align="center">
    <tr style="background-color: #4dc8ff">
        <th>id</th>
        <th>login</th>
        <th>role</th>
        <th>address</th>
        <th>music types</th>
    </tr>
    <c:forEach items="${userRoleFilter}" var="user">
        <tr height="25" style="background-color: #a3ff7d">
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.address}"/></td>
            <td><c:out value="${user.musicTypes}"/></td>
        </tr>
    </c:forEach>
    <c:forEach items="${musicTypeRoleFilter}" var="user">
        <tr height="25" style="background-color: #a3ff7d">
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.address}"/></td>
            <td><c:out value="${user.musicTypes}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
