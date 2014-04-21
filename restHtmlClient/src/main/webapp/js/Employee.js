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
			var id = dataXmlDom.documentElement.children[0].textContent;
			var name = dataXmlDom.documentElement.children[1].textContent;
			var salary = dataXmlDom.documentElement.children[2].textContent;

			var strMessage = "Employee data, after parsing XML: [" + id + "] "
					+ name + ", salary " + salary;
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
			var strMessage = "JSON result: "
					+ JSON.stringify(dataObj);
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