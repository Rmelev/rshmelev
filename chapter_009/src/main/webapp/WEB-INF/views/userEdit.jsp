<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https:////netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <title>Admin edit page</title>
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
        function updateUser() {
            $.ajax('./edituser', {
                method: 'post',
                dataType : "json",
                data: {name: $('#name').val(), email: $('#email').val(),
                    password: $('#password').val(),
                    country: $('#country').val(), city: $('#city').val(),}
            });
            location.href = "./";
        }

    </script>
</head>
<body align="center">
<h4 style="background-color: red">You can edit only own record. Edit will apply for your login.</h4>
<form action='${pageContext.servletContext.contextPath}/edituser' method='post'>
    Edit for login: <h3><c:out value="${sessionScope.login}"/></h3><br/>
    name <input id="name" type='text' name='name'/>
    email <input id="email" type='text' name='email'/>
    password <input id="password" type='password' name='password'/><br/>
    country <select id="country" height="30" name='country'/>
    <option id="0" selected>unindefinite</option>
    <option id="USA" >USA</option>
    <option id="Russia" height="30" >Russia</option>
    </select>
    city <select id="city" name='city'/>
    <option value="0" selected>choose country</option>
    </select>
    <input type='submit' onclick="return updateUser();"> <br/>
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
