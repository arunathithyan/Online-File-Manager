<!DOCTYPE html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload File</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="style5.css">
</head>
<body>
	
<c:if test="${empty param.name}">
<c:set var="root">ROOT</c:set>		
</c:if>
<c:if test="${not empty param.name}">
<c:set var="root">${param.name}</c:set>
</c:if>


		<form action="UploadFile06" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${param.id}"/>
		<table class="newfol">
		<c:out value="${root}">${root}</c:out> 
		<tr><td> <c:out value="Upload File"></c:out><input type="file" name="file"/></td></tr>
		
		<tr><td><input class="button3" type="submit" name="upload" value="Upload"/></td></tr>
		</table>
		</form>
</body>
</html>