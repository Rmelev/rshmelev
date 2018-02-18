<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href='${pageContext.servletContext.contextPath}/create'>Add element</a>
<form action='${pageContext.servletContext.contextPath}/editadmin' method='post'>
    Edit: login <input type='text' name='login'/>
    name <input type='text' name='name'/>
    email <input type='text' name='email'/>
    password <input type='password' name='password'/>
    role <select name="role">
    <option value="user">user</option>
    <option value="admin">admin</option>
</select>
    <input type='submit'> <br/>
</form>
<form action='${pageContext.servletContext.contextPath}/delete' method='post'>
    Delete: login <input type='text' name='login'/>
    <input type='submit'> <br/>
</form>
</body>
</html>
