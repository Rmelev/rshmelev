$(document).ready(function() {
    getItems();
});

function addItem() {
    $.ajax('./add', {
        method: 'post',
        dataType: 'json',
        data: {
            desc: $("#add").val()
        },
        success: location.reload()
    });
}

function getItems() {
    $.ajax('./show', {
        method: 'get',
        dataType: "json",
        data: {
            'done': $("#status").prop('checked')
        },
        success: function(data) {
            document.getElementById("items").innerHTML = "";
            $.each(data, function (i, el) {
                var line = "<tr>"
                    + "<td>" + el.id + "</td>"
                    + "<td>" + el.desc + "</td>"
                    + "<td>" + new Date(el.created) + "</td>";
                if (el.done === true) {
                    line += "<td><input type='checkbox' checked onchange='return updateStatus(" + el.id + "," + el.done + ")'/></td>";
                } else {
                    line += "<td><input type='checkbox' onchange='return updateStatus(" + el.id + "," + el.done + ")'/></td></tr>";
                }
                document.getElementById("items").innerHTML += line;
            })
        }
    })
}

function updateStatus(id, status) {
    $.ajax('./update', {
        method: 'post',
        data: {
            'id': id,
            'done': status
        }
    })
}