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
        .selector {
            width: 90px;
            margin-right: 5px;
        }
    </style>
    <script>
        $(document).ready(function () {
            loadSelectors();
        });

        /**
         * Add order.
         */
        function addOrder() {
            var order = {
                'description' : $('#description').val(),
                'price' : $('#price').val(),
                'car' : {
                    'color' : $('#color').val(),
                    'model' :{
                        'name' : $('#model').val(),
                        'brand' : {'name': $('#brand').val()}
                    },
                    'body' : {'name' : $('#body').val()},
                    'transmission' : {'name' : $('#transmission').val()},
                    'engine' : {'name' : $('#engine').val()}
                }
            };

            $.ajax('./addOrder', {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: "POST",
                async: false,
                processData:false,
                data : JSON.stringify(order),
                complete : function(data){
                    alert("Order added successful!");
                }
            })
        }

        /**
         * Form selector for brands.
         */
        function brandSelector() {
            $.ajax('./getBrands', {
                method: 'get',
                dataType: "json",
                success: function (data) {
                    document.getElementById("brand").innerHTML = "<option>--choose brand--</option>"
                    $.each(data, function (i, el) {
                        document.getElementById("brand").innerHTML += "<option>" + el.name + "</option>";
                    });
                }
            })
        }

        /**
         * Form selector for models.
         */
        function modelSelector() {
            var sel = document.getElementById("brand");
            $.ajax('./getModels', {
                method: 'get',
                data : {
                    brand: $('#brand').val()
                },
                dataType: "json",
                success: function (data) {
                    document.getElementById("model").innerHTML = "<option>--choose model--</option>";
                    $.each(data, function (i, el) {
                        document.getElementById("model").innerHTML += "<option>" + el.name + "</option>";
                    });
                }
            })
        }

        /**
         * Form selector for engines.
         */
        function engineSelector() {
            $.ajax('./getEngines', {
                method: 'get',
                dataType: "json",
                success: function (data) {
                    $.each(data, function (i, el) {
                        document.getElementById("engine").innerHTML += "<option>" + el.name + "</option>";
                    });
                }
            })
        }

        /**
         * Form selector for transmissions.
         */
        function transmissionSelector() {
            $.ajax('./getTransmissions', {
                method: 'get',
                dataType: "json",
                success: function (data) {
                    $.each(data, function (i, el) {
                        document.getElementById("transmission").innerHTML += "<option>" + el.name + "</option>";
                    });
                }
            })
        }

        /**
         * Form selector for car bodies.
         */
        function carBodiesSelector() {
            $.ajax('./getBodies', {
                method: 'get',
                dataType: "json",
                success: function (data) {
                    $.each(data, function (i, el) {
                        document.getElementById("body").innerHTML += "<option>" + el.name + "</option>";
                    });
                }
            })
        }

        /**
         * Load selectors on form.
         */
        function loadSelectors() {
            brandSelector();
            engineSelector();
            transmissionSelector();
            carBodiesSelector();
        }

        function back() {
            location.href = "./";
        }

        function addImage() {
            var data = new FormData();
            var x = document.getElementById("file-image");
            var file = x.files[0];
            data.append("file", file);
            $.ajax({
                url: "addImage",
                method: "post",
                processData : false,
                contentType : false,
                data: data,
                complete: function (data) {
                    var result  = JSON.parse(data.responseText);
                    if (result.success) {
                        alert("Image was added successful! You can add another image.");
                    }
                }
            });
        }

    </script>
</head>
<body>

<div class="container">
    <br>
    <button type="submit" class="btn btn-success" onclick="back()">Back</button>
    <h3 style="padding-bottom: 30px">Create new order</h3>
    <div class="row">
        <div class="col-md-8">
            <form id="form" accept-charset="UTF-8">
                <label>
                    Color: <input type="text" id="color" value="red" name="color">
                </label>
                <br><br>
                <label>
                    Car brand: <br/>
                    <select class="selector" id="brand" name="brand" onchange="return modelSelector()"></select>
                </label>

                <label>
                    Car model: <br/>
                    <select class="selector" id="model" name="model"></select>
                </label>

                <label>
                    Engine type: <br/>
                    <select class="selector" id="engine" name="engine"></select>
                </label>

                <label>
                    Transmission: <br/>
                    <select class="selector" id="transmission" name="transmission"></select>
                </label>

                <label>
                    Body type: <br/>
                    <select class="selector" id="body" name="body"></select>
                </label>

                <br/><br/>

                <label>
                    Price: <input type="number" id="price" name="price" value="50000">
                </label>
                <br/><br/>

                <label>
                    Description: <br/>
                    <textarea style="width: 300%; padding-bottom: 20px" id="description" name="description">Third try</textarea>
                </label>
            </form>
        </div>
    </div>
    <br/>
    <button type="submit" class="btn btn-success" onclick="return addOrder();">Add new order</button><br><br>
</div>

<div class="container">
    <form enctype="multipart/form-data">
        <div id ="multi-choice" class="form-group">
            <input type="file" accept="image/jpeg" multiple="multiple" id="file-image">
            <input id="file-btn-load" type="button"  class="btn btn-success" value="Add image" onclick="return addImage();">
        </div>
    </form>
</div>

</body>
</html>
