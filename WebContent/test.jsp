<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
            $(document).ready(function() {                        // When the HTML DOM is ready loading, then execute the following function...
                $('#somebutton').click(function() { 
                	var resumeID = document.getElementById('resumeID').innerHTML;
                	var resumePassword = document.getElementById('password').innerHTML;
                	alert(resumeID);
        			alert(resumePassword);
                	// Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
                    $.get('TestServlet/', function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                        $('#somediv').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
                    });
                });
            });
        </script>

</head>
 <body>
 		<input type="text" id="resumeID"/>
 		<input type="password" id="resume_password"/>
        <button id="somebutton">press here</button>
        <div id="somediv"></div>
    </body>
</html>