<!DOCTYPE html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style5.css">
</head>
<body>
		<c:if test="${not empty param.error}"><c:out value="Error Incorrect username or password"/></c:if>
		
		<form action='FileFolderLogin06' method='post' >
		
		<center>
		<h1>User Login</h1>
		<br><br>
		<c:out value="Username: "/> <input class="form-control wd" type='text' name='user'/>
		<br>
		<br>
		<c:out value="Password: "/> <input class="form-control wd"  type='password' name='pass'  />
		<br>
		<br>
		<input class="button3" type='submit' name='login' value='Login' /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="<c:url value='/FileFolderReg06'/>">New User</a>
		</center>
		</form>

</body>
</html>