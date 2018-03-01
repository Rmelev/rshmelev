<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<html>
<head>
    <title>Sign In</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script type="text/javascript">
        function isValidate() {
            var valid = true;
            var login, password;
            login = jQuery('#val1').val();
            password = jQuery('#val2').val();
            if (login == "" || password == "") {
                alert('Заполните оба поля');
                valid = false;
            }
            return valid;
        }
    </script>
</head>
<body align="center">
<%--<c:if test="${error != ''}">--%>
    <%--<div style="background-color: red">--%>
        <%--<c:out value="${error}"/>--%>
    <%--</div>--%>
<%--</c:if>--%>
<br/>
<form action='${pageContext.servletContext.contextPath}/logIn' method='post' accept-charset="UTF-8"
      onsubmit="return isValidate();">
    Login &nbsp &nbsp &nbsp &nbsp<input id='val1' type='text' name='login'/><br/><br/>
    Password: <input id='val2' type='password' name='password'/><br/><br/>
    <input type='submit'> <br/>
</form>
</body>
</html>
