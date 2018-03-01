<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <title>User's database</title>
    <style>
        table.border, th.border, td.border {
            border: 2px solid black;
            border-collapse: collapse;
        }
        th, td {
            width: 110px;
            height: 30px;
        }
        table.nonborder {
            border: hidden;
        }
    </style>
</head>
<body>
<table align="center" class="nonborder">
    <tr>
        <td><form  align="center" action='${pageContext.servletContext.contextPath}/quit'>
            <input type='submit' value="LogOut">
        </form></td>
        <td><form  align="center" action='${pageContext.servletContext.contextPath}/rolefilter'>
            <input type='submit' value="Filters">
        </form></td>
    </tr>
</table>

<form  align="center" action='${pageContext.servletContext.contextPath}/create' method='post'>
    <h4>Add record:</h4>
    Login <input id="login" type='text' name='login'/><br/><br/>
    Password <input id="password" type='password' name='password'/><br/><br/>
    Role <select id="role" name="role">
        <option>USER</option>
        <option>MANDATOR</option>
        <option>ADMIN</option>
    </select><br/><br/>
    Address <input id="address" type='text' name='address' value="Voronezh, Lizyukova, 17"/><br/><br/>
    MusicType_1 <select id="mtype1" name='mtype1'/>
        <%--<option selected>unindefinite</option>--%>
        <option>RAP</option>
        <option>ROCK</option>
        <option>JAZZ</option>
        <option>SWING</option>
    </select><br/><br/>
    MusicType_2 <select id="mtype2" name='mtype2'/>
    <%--<option selected>unindefinite</option>--%>
    <option>ROCK</option>
    <option>RAP</option>
    <option>JAZZ</option>
    <option>SWING</option>
    </select><br/><br/>
    MusicType_3 <select id="mtype3" name='mtype3'/>
    <%--<option selected>unindefinite</option>--%>
    <option>JAZZ</option>
    <option>RAP</option>
    <option>ROCK</option>
    <option>SWING</option>
    </select><br/><br/>
    <input type='submit' value="Add">
</form>

<table class="border" id="yes" align="center">
    <tr class="border" style="background-color: #4dc8ff">
        <th class="border">id</th>
        <th class="border">login</th>
        <th class="border">role</th>
        <th class="border" >address</th>
        <th class="border" >music types</th>
        <th class="border" >delete record</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr class="border" height="25" style="background-color: #a3ff7d">
            <td class="border" ><c:out value="${user.id}"/></td>
            <td class="border" ><c:out value="${user.login}"/></td>
            <td class="border" ><c:out value="${user.role}"/></td>
            <td class="border" ><c:out value="${user.address}"/></td>
            <td class="border" ><c:out value="${user.musicTypes}"/></td>
            <td class="border" >
                <form action='${pageContext.servletContext.contextPath}/delete'  method='post'>
                    <input type='text' value="${user.id}" name="el_id" hidden>
                <input type='submit' value="Delete">
            </form></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
