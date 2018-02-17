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
</table>
<a href=<%=request.getContextPath() + "/create"%>>Add element</a>
<form action='${pageContext.servletContext.contextPath}/edit' method='post'>
    Edit: login <input type='text' name='login'/>
    New name <input type='text' name='name'/>
    New email <input type='text' name='email'/>
    <input type='submit'> <br/>
</form>
<form action='${pageContext.servletContext.contextPath}/delete' method='post'>
    Delete: login <input type='text' name='login'/>
    <input type='submit'> <br/>
</form>
<table style="border-style: solid double" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>createDate</th>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.name}"></c:out></td>
        <td><c:out value="${user.login}"></c:out></td>
        <td><c:out value="${user.email}"></c:out></td>
        <td><c:out value="${user.createDate}"></c:out></td>
    </tr>
    </c:forEach>
</body>
</html>