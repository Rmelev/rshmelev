<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p style="background-color: red">You can edit only own record. Edit will apply for your login.</p>
<form action='${pageContext.servletContext.contextPath}/edituser' method='post'>
    Edit: name <input type='text' name='name'/>
    email <input type='text' name='email'/>
    password <input type='password' name='password'/>
    <input type='submit'> <br/>
</form>
</body>
</html>
