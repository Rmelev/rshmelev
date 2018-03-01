<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="bootstrap.jsp"/>
    <title>Admin edit page</title>
    <script>
        $(document).ready(function () {
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
                dataType: "json",
                data: {
                    name: $('#name').val(), email: $('#email').val(),
                    password: $('#password').val(),
                    country: $('#country').val(), city: $('#city').val(),
                }
            });
            location.href = "./";
        }

    </script>
</head>
<body>
<br>
<div align="left" class="form-group">
    <h4 style="background-color: red">You can edit only own record. Edit will apply for your login.</h4>
    <form action='${pageContext.servletContext.contextPath}/edituser' method='post'>
        <label>Edit for login:&nbsp &nbsp<c:out value="${sessionScope.login}"/></label><br/>
        <label>New name <input id="name" type='text' name='name'/></label><br/>
        <label>New email <input id="email" type='text' name='email'/></label><br/>
        <label>New password <input id="password" type='password' name='password'/></label><br/>
        <label>New country <select id="country" height="30" name='country'>
            <option id="0" selected>unindefinite</option>
            <option id="USA">USA</option>
            <option id="Russia" height="30">Russia</option>
        </select></label><br/>
        <label>New city <select id="city" name='city'>
        <option value="0" selected>choose country</option>
        </select></label><br>
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
</div>
</body>
</html>
