<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<html>
<head>
    <title>Car Store</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https:////netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        table, th, td {
            border: 2px solid black;
            border-collapse: collapse;
        }
    </style>
    <script>
        $(document).ready(function() {
            getItems(${curId});
        });

        /**
         * get all items.
         * @param userId - identificator for add order and update status.
         */
        function getItems(userId) {
            $.ajax('./', {
                method: 'get',
                dataType: "json",
                data: {
                    brand : $('#brand').val()
                },
                complete: function(data) {
                    $('#items').empty();
                    document.getElementById("items").innerHTML = "";
                    <c:forEach items="${orders}" var="el">

                    var line = "<tr>"
                        + "<td><c:out value="${el.id}"/></td>"
                        + "<td><c:out value="${el.date}"/></td>"
                        + "<td><c:out value="${el.description}"/></td>"
                        + "<td><c:out value="${el.car.color}"/></td>"
                        + "<td><c:out value="${el.car.model.brand.name}"/></td>"
                        + "<td><c:out value="${el.car.model.name}"/></td>"
                        + "<td><c:out value="${el.car.body.name}"/></td>"
                        + "<td><c:out value="${el.car.transmission.name}"/></td>"
                        + "<td><c:out value="${el.car.engine.name}"/></td>"
                        + "<td><c:out value="${el.price}"/></td>"
                        + "<td><button type='button' class='btn btn-link pictures' onclick= 'return callGallery(" + ${el.id} + ")' >Gallery</button></td>"
                        + "<td><c:out value="${el.sold}"/></td>"
                        + "<td><c:out value="${el.user.email}"/></td>";
                    if (${curId} !== null && ${curId} === ${el.user.id}) {
                        var sold = ${el.sold};
                        if (sold === true) {
                            line += "<td style='text-align: center'>" +
                                "<input type='checkbox' checked onchange='return updateStatus(" + ${el.id} +","+ sold +")'/></td></tr>";
                        } else {
                            line += "<td style='text-align: center'>" +
                                "<input type='checkbox' onchange='return updateStatus(" + ${el.id} +","+ sold +")'/></td></tr>";
                        }
                    } else {
                        if (sold === true) {
                            line += "<td>sold</td></tr>";
                        } else {
                            line += "<td>for sale</td></tr>";
                        }
                    }
                    document.getElementById("items").innerHTML += line;
                    </c:forEach>

                    document.getElementById("f").innerHTML = "";
                    var f;
                    <c:choose>
                    <c:when test="${sessionScope.foto eq 'true'}">
                        f = "&nbsp;&nbsp;&nbsp;Image:&nbsp;<input type='checkbox' id ='foto' onchange='return fotoReverse();' checked/>";
                    </c:when>
                    <c:otherwise>
                        f = "&nbsp;&nbsp;&nbsp;Image:&nbsp;<input type='checkbox' id ='foto' onchange='return fotoReverse();'/>";
                    </c:otherwise>
                    </c:choose>
                    document.getElementById("f").innerHTML += f;

                    document.getElementById("d").innerHTML = "";
                    var d;
                    <c:choose>
                    <c:when test="${sessionScope.data eq 'true'}">
                        d = "&nbsp;&nbsp;&nbsp;Last Day:&nbsp;<input type='checkbox' id ='data' onchange='return dataReverse();' checked/>";
                    </c:when>
                    <c:otherwise>
                        d = "&nbsp;&nbsp;&nbsp;Last Day:&nbsp;<input type='checkbox' id ='data' onchange='return dataReverse();'/>";
                    </c:otherwise>
                    </c:choose>
                    document.getElementById("d").innerHTML += d;

                    document.getElementById("username").innerHTML = "<h3> You log in as: " + "${curName}" + "</h3>";
                }
            })
        }

        /**
         * log out from user rights.
         */
        function logOut() {
            $.ajax('./logout', {
                method: 'get',
                dataType: "json",
                complete: function() {
                    location.href = "./"
                }
            });
        }

        function callGallery(orderId) {
            $.ajax({
                url: "image",
                method: "get",
                data: {'order': orderId},
                dataType: "json",
                complete: function (data) {
                    var returnData = JSON.parse(data.responseText);
                    if (returnData.length === 0) {
                        alert('Owner did not add images');
                    }
                    for (var i = 0; i !== returnData.length; ++i) {
                        $("#picture").append("<a href='${pageContext.servletContext.contextPath}/'><img src= '" + returnData[i] + "'   width='400' height='300'></a>");
                    }
                }
            })
        }

        function fotoReverse() {
            $.ajax({
                url: "fotoReverse",
                method: "get",
                dataType: 'json'
            });
        }

        function dataReverse() {
            $.ajax({
                url: "dataReverse",
                method: "get",
                dataType: 'json'
            });
        }

        function resetFilter() {
            $.ajax({
                url: "resetFilter",
                method: "get",
                dataType: 'json'
            });
        }

    </script>

</head>
<body>
<div class="container" class="container" style="margin-left: 10px; padding-left: 10px; margin-right: 10px; padding-right: 10px">
    <br>
    <label><input type="submit" class="btn btn-success" value="LogOut" onclick="logOut();"></label>
    <p id="username"></p>
    <a id="add" href="./add"><h3><u>Add Order</u></h3></a>
    <div align="center">
        <h3 align="center" >Filter</h3>
        <form class="form-inline">
            <div class="form-group">
                <label>
                    &nbsp;&nbsp;&nbsp;Car brand:
                    <select class="selector" id="brand" name="brand">
                        <option><c:out value=""/></option>
                        <c:forEach items="${brands}" var="el">
                            <option><c:out value="${el.name}"/></option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div class="form-group">
                <label id="f">
                </label>
            </div>
            <div class="form-group">
                <label id="d">
                </label>

            </div>
            <label>&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-success" id="flt-ord-btn" value="Filter" onclick="return getItems(${curId});"/></label>
            <label>&nbsp;&nbsp;&nbsp;<input type="submit" class="btn btn-success" id="res-ord-btn" value="Reset" onclick="return resetFilter();"/></label>
        </form>

    </div>
    <div class="row">
        <div id="picture"></div>
        <div class="col-md-12">
            <table class="table table-bordered">
                <thead style="background: #4dc8ff">
                <tr style="color: white">
                    <td>ID</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Description</td>
                    <td>Color</td>
                    <td>Brand</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Model&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>Body</td>
                    <td>Transmission</td>
                    <td>Engine</td>
                    <td>Price</td>
                    <td>Images</td>
                    <td>Sold</td>
                    <td>Contact</td>
                    <td>Status</td>
                </tr>
                </thead>
                <tbody id="items"></tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
