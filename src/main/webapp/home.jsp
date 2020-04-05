<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	$("#submit").click(function(){
		val = $("#expression").val();
		flag = val.match(/[A-z]/g)?false:true;
		if (flag){
			signUpForm.submit();
		} else{
			alert('alphabet not allowed');
			$("#submit").prop('disabled', true);
		}
	});

	$("#expression").on("input", function(){
		$("#submit").prop('disabled', false);
    });
});
</script>
<title>OA Demo</title>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
</head>
<body>
<div class="container">
	<form action="addExpression" id="expressionForm">
		<label for="fname">Math Expression</label>
		<input type="text" id="expression" name="expression" placeholder="Enter Here"><br>
		<input type="submit" id= "submit" value="Submit"><br>
	</form>
</div>
</body>
</html>