<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User's operation choice</title>
    <jsp:include page="bootstrap.jsp"/>
    <style>
        table, th, td {
            border: 2px solid black;
            border-collapse: collapse;
        }
        th, td {
            width: 110px;
            height: 30px;
        }
    </style>
    <%--<script type="text/javascript">--%>
        <%--$(document).ready(function getUsers() {--%>
            <%--$.ajax('./json', {--%>
                <%--method : 'get',--%>
                <%--dataType : "json",--%>
                <%--success: function(data) {--%>
                    <%--$.each(data, function(i, el) {--%>
                        <%--document.getElementById("yes").innerHTML += "<tbody><tr>"--%>
                            <%--+ "<td>" + el.name + "</td>"--%>
                            <%--+ "<td>" + el.login + "</td>"--%>
                            <%--+ "<td>" + el.email + "</td>"--%>
                            <%--+ "<td>" + el.createDate + "</td>"--%>
                            <%--+ "<td>" + el.role + "</td>"--%>
                            <%--+ "<td>" + el.country + "</td>"--%>
                            <%--+ "<td>" + el.city + "</td>"--%>
                            <%--+ "</tbody></tr>";--%>
                    <%--});--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>
<%--    </script>--%>
</head>
<body>
<br>
<form align="center" action='${pageContext.servletContext.contextPath}/quit'>
    <input type='submit' value="LogOut">
</form>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <c:if test="${sessionScope.role eq 'admin'}">
            <c:import url="adminEdit.jsp"/>
        </c:if>
            <c:if test="${sessionScope.role eq 'user'}">
                <c:import url="userEdit.jsp"/>
                <%--<jsp:include page="userEdit.jsp" />--%>
            </c:if>
        </div>
        <div class="col-md-9">
            <table id="yes" align="center">
                <tr style="background-color: #4dc8ff">
                    <th>name</th>
                    <th>login</th>
                    <th>email</th>
                    <th>createDate</th>
                    <th>role</th>
                    <th>country</th>
                    <th>city</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr height="25" style="background-color: #a3ff7d">
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.login}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.createDate}"/></td>
                        <td><c:out value="${user.role}"/></td>
                        <td><c:out value="${user.country}"/></td>
                        <td><c:out value="${user.city}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>