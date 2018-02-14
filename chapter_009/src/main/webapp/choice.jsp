<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.sepoperservlet.MyDataSource" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<html>
<head>
    <title>User's operation choice</title>
</head>
<body>
</table>
<a href='./create'>Add element</a>
<form action='./edit' method='post'>
    Edit: login <input type='text' name='login'/>
    New name <input type='text' name='name'/>
    New email <input type='text' name='email'/>
    <input type='submit'> <br/>
</form>
<form action='./delete' method='post'>
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
        <% for (User nextUser : MyDataSource.getInstance().getUsers()) {%>
    <tr>
        <td><%=nextUser.getName()%></td>
        <td><%=nextUser.getLogin()%></td>
        <td><%=nextUser.getEmail()%></td>
        <td><%=nextUser.getCreateDate()%></td>
    </tr>
        <% } %>
</body>
</html>