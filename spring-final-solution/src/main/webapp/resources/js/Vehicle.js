$(document).ready(function () {

});

function doGetVehicles() {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/vehicles",
        headers: {
            "Accept": "application/json"
        },

        success: function (dataObj) {
            var strMessage = "JSON result: " + JSON.stringify(dataObj);
            $("#myMessageArea").html(strMessage);
        },

        error: function (xhr, message, errorThrown) {
            alert("Ajax error occurred: " + message);
        }
    });
}