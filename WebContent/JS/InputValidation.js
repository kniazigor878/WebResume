	function validateInsertInput(){
		var emptyCheck = [];
		emptyCheck["'Upload resume'"] = document.getElementById("file_form1").value;
		emptyCheck["'Set password'"] = document.getElementById("password_form1").value;
		emptyCheck["'Confirm password'"] = document.getElementById("conf_password_form1").value;
		
		if (!checkEmptyFields(emptyCheck)){
			return false;
			}else{
				return checkPassword();
			}
	}	

	function validateUpdateInput(){
		var emptyCheck = [];
		emptyCheck["'Upload resume'"] = document.getElementById("file_form2").value;
		emptyCheck["'WebResume ID'"] = document.getElementById("web_id_form2").value;
		emptyCheck["'WebResume password'"] = document.getElementById("password_form2").value;
		
		if (!checkEmptyFields(emptyCheck)){
			return false;
			}else{
				if (!checkPassword()){
					return false;
				}else{
					var isIDAndPasswordCorrect = checkIDAndPassword();
					//alert("before if" + isIDAndPasswordCorrect);
					if(!isIDAndPasswordCorrect){
						//alert("final false");
						return false;
					}else{
						//alert("final true");
						return true;
					}
				}
			}
	}	
	
	function checkEmptyFields(emptyCheck){
		var counter = 0;
		var alertMessage = "";
		
		for (key in emptyCheck){
			if(emptyCheck[key] == ''){alertMessage += key + " is empty! Please fill mandatory field \n"; counter++;}
		}
		
		if(counter>0){
			alert(alertMessage);
			return false;
		} else {
			return true;
		}
	}
	
/*	function checkEmail (){
		var email = document.getElementById("email2").value;
		var pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/; 
		var res= email.match(pattern);
		(res == null) ? (res = false) : (res = true);
		if (!res) alert("E-mail is not valid. Please modify it");
		return res;
	}*/
	
	function checkPassword(){
		var pass = document.getElementById("password_form1").value;
		var cpass = document.getElementById("conf_password_form1").value;
		if (pass == cpass){
			return true;
		}else{
			alert("Password did not match. Please confirm it again");
			document.getElementById("conf_password_form1").value = "";
			return false;
		}
		
		
	}
	
	function checkIDAndPassword(){
		var resumeID = document.getElementById('web_id_form2').value;
    	var resumePassword = document.getElementById('password_form2').value;
    	//alert(resumeID);
		//alert(resumePassword);
		var result = false;
    	// Locate HTML DOM element with ID "somebutton" and assign the following function to its "click" event...
		$.ajaxSetup({async:false});
		$.get('TestServlet/' + resumeID + '/' + resumePassword, function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
            //$('#somediv').text(responseText);         // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
        	//alert(responseText);
        	//alert(responseText.substr(0,1));
        	//alert(responseText.substr(1,1));
        	if(responseText.substr(0,1) == 0){
        		alert("Your WebResume ID doesn't exist! Please white another one");
        		result =  false;
        	}else{
        		if(responseText.substr(1,1) == 0){
        			alert("Password doesn't match to existing WebResume");
        			result =  false;
        		}else{
        			//alert("return true");
        			result =  true;
        		}
        	}
        });
		return result;
	}

