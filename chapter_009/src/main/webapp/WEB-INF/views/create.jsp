<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create User</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https:////netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            $("select[name='country']").bind("change", function () {
                $.ajax('./json', {
                    method: 'get',
                    dataType: "json",
                    success: function (data) {
                        $("select[name='city']").empty();
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
        function addUser() {
            $.ajax('./create', {
                method: 'post',
                dataType : "json",
                data: {name: $('#name').val(), login: $('#login').val(), email: $('#email').val(),
                    createDate: $('#createDate').val(), role: $('#role').val(), password: $('#password').val(),
                    country: $('#country').val(), city: $('#city').val()}
            });
            location.href = "./";
        }

    </script>
</head>
<body align="center">
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
<br/>
<form action='${pageContext.servletContext.contextPath}/quit'>
    <input type='submit' value="LogOut">
</form>
<form action='${pageContext.servletContext.contextPath}/create' method='post'>
    <h4>Add record:</h4>
    login <input id="login" type='text' name='login'/><br/><br/>
    name <input id="name" type='text' name='name'/><br/><br/>
    email <input id="email" type='text' name='email'/><br/><br/>
    password <input id="password" type='password' name='password'/><br/><br/>
    role <select id="role" name="role">
        <option value="user">user</option>
        <option value="admin">admin</option>
    </select><br/><br/>
    country <select id="country" name='country'/>
    <option id="0" selected>unindefinite</option>
    <option id="USA">USA</option>
    <option id="Russia">Russia</option>
    </select><br/><br/>
    city <select id="city" name='city'/>
    <option value="0" selected>choose country</option>
    </select><br/><br/>
    <input type='submit' onclick="return addUser();">
</form>
</body>
</html>
