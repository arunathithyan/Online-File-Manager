<!DOCTYPE html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit FileFolder Name</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="style5.css">
</head>
<body>

		<form action='EditFileFolder06' method='post' >
		<input type="hidden" name="id" value="${param.id}"/>
		<input type="hidden" name="shared" value="${param.shared}"/>
		<table class="newfol">
		<tr><td><c:out value="EditFolder">Edit Folder</c:out> <input type="text" name="filename" value="${param.name}"/></td></tr>
		<tr><td><input class="button3" type="submit" name="save" value="Save" /></td></tr>
		</table>
		</form>

</body>
</html>