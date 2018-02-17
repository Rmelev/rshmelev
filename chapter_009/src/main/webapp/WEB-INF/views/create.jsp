<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action='${pageContext.servletContext.contextPath}/create' method='post'>
    Add: login <input type='text' name='login'/>
    name <input type='text' name='name'/>
    email <input type='text' name='email'/>
    <input type='submit'> <br/>
</form>
</body>
</html>
