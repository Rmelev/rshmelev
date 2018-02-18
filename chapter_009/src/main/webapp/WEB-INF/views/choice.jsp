<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.sepoperservlet.MyDataSource" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User's operation choice</title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/quit'>
    <input type='submit' value="LogOut">
</form>

<c:if test="${sessionScope.role eq 'admin'}">
    <jsp:include page="adminEdit.jsp" />
</c:if>
<c:if test="${sessionScope.role eq 'user'}">
    <jsp:include page="userEdit.jsp" />
</c:if>

<table style="border-style: solid double" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>createDate</th>
        <th>role</th>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.createDate}"/></td>
        <td><c:out value="${user.role}"/></td>
    </tr>
    </c:forEach>
</table>
</body>
</html>