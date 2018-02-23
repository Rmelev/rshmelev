<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin edit page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https:////netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#country').bind("change", function () {
                $.ajax('./json', {
                    method: 'get',
                    dataType: "json",
                    success: function (data) {
                        $('#city').empty();
                        $.each(data, function (i, el) {
                            if ($('#country').val() == el.country) {
                                document.getElementById("city").innerHTML +=
                                     "<option>" + el.city + "</option>"
                            }
                        });
                    }
                });
            });
        });
        function updateUser() {
            $.ajax('./editadmin', {
                method: 'post',
                dataType : "json",
                data: {name: $('#name').val(), login: $('#login').val(), email: $('#email').val(),
                    createDate: $('#createDate').val(), role: $('#role').val(), password: $('#password').val(),
                    country: $('#country').val(), city: $('#city').val(),}
            });
            location.href = "./";
        }

    </script>
</head>
<body align="center">
<a href='${pageContext.servletContext.contextPath}/create'>Add element</a><br/>
<%--до введения скрипта данный формы отправлялись строчкой ниже--%>
<form action='${pageContext.servletContext.contextPath}/editadmin' method='post'>
Edit for login <input id="login" type='text' name='login'/>
name <input id="name" type='text' name='name'/>
email <input id="email" type='text' name='email'/><br/>
password <input id="password" type='password' name='password'/>
role <select id="role" name="role">
    <option value="user">user</option>
    <option value="admin">admin</option>
</select>
country <select id="country" name='country'/>
<option id="0" selected>unindefinite</option>
<option id="USA">USA</option>
<option id="Russia">Russia</option>
</select>
city <select id="city" name='city'/>
<option value="0" selected>choose country</option>
</select>
<input type='submit' onclick="return updateUser();"> <br/><br/>
</form>
<form action='${pageContext.servletContext.contextPath}/delete' method='post'>
    Delete: login <input type='text' name='login'/>
    <input type='submit'> <br/>
</form>
<c:if test="${requestScope.get(isEmpty) != ''}">
    <div style="background-color: red">
        <c:out value="${isEmpty}"/>
    </div>
</c:if>
<c:if test="${requestScope.get(notEmail) != ''}">
    <div style="background-color: red">
        <c:out value="${notEmail}"/>
    </div>
</c:if>
</body>
</html>
