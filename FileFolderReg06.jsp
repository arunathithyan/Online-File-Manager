<!DOCTYPE html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UserRegistration</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style5.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>

$(function(){

	
	$("input:password").click(function()
	{
		$("#perror").text(" ");
	});

	$("input:text").click(function(){
		$("#uerror").text(" ");
	});

	$("form").submit(function(e){
		
		if(!$("input:text").val())
		{
		$("#uerror").text("user name cant be empty");
	    e.preventDefault();
		}
		
		
		else
		{
			$.ajax({
	              url: "FileFolderReg06",
	              type: 'POST',
	              async: false,
	              data: 
	              {
	                  "user": $("input:text").val(),
	                  "pass": $("input:password").val()
	              },
	              success: function(data1)
	              {
	            	
	            	if(data1==1)
	            	{
	            	  $("#uerror").text("user name already exist");
	          	    	e.preventDefault();
	          	    }
	            	  else
	            	{	 document.location.href='FileFolderLogin06';
	            		  e.preventDefault();
	             	}
	            	  
	              }
	          });
		}
		
		if(!$("input:password").val())
		{
			$("#perror").text("Password can not be empty");
			e.preventDefault();
		}	
		
		
			});	
	
});
</script>
</head>
<body>
	
		
		<form action='FileFolderReg06' method='post' >
		
		<h1>REGISTRATION</h1>
		<c:out value=" Enter Username"/> <input class="form-control wd" type='text' name='user'/> 
		<p id="uerror"> </p>
		<br><br>
		<c:out value="Enter Password: "/> <input class="form-control wd" type='password' name='pass'/>
		<p id="perror"> </p>
		<br><br>
		<input class="button3" type='submit' name='reg' value='Submit' /> 
		
		</form>
</body>
</html>