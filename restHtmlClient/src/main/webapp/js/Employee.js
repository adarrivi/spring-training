var EMPLOYEE_WEBSERVICE_URL = "http://localhost:8080/restWebService/services/employee";

$(document).ready(function() {
	// load users?
});

function doGetEmployeesAsXml() {
	$.ajax({
		type : "GET",
		url : EMPLOYEE_WEBSERVICE_URL,
		headers : {
			"Accept" : "application/xml"
		},

		success : function(dataXmlDom) {
			var strMessage;
			if (dataXmlDom.length != 0) {
				strMessage = "Employee xml data: " + JSON.stringify(dataXmlDom);

			} else {
				strMessage = "Empty list received";
			}
			$("#myMessageArea").html(strMessage);
		},

		error : function(xhr, message, errorThrown) {
			alert("Ajax error occurred: " + message);
		}
	});
}

function doGetEmployeesAsJson() {

	$.ajax({
		type : "GET",
		url : EMPLOYEE_WEBSERVICE_URL,
		headers : {
			"Accept" : "application/json"
		},

		success : function(dataObj) {
			var strMessage = "JSON result: " + JSON.stringify(dataObj);
			$("#myMessageArea").html(strMessage);
		},

		error : function(xhr, message, errorThrown) {
			alert("Ajax error occurred: " + message);
		}
	});
}

function saveUpdateEmployee() {

	$.ajax({
		url : EMPLOYEE_WEBSERVICE_URL,
		type : "POST",
		contentType : 'application/json',
		data : JSON.stringify({
			"id" : $("#employeeId").val(),
			"name" : $("#employeeName").val(),
			"salary" : $("#salary").val()
		}),

		success : function(dataObj) {
			$("#myMessageArea").html("Employee saved");
		},

		error : function(xhr, message, errorThrown) {
			alert("Ajax error occurred: " + message);
		}
	});
}