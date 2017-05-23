<!DOCTYPE html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Folder</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="style5.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function(){
$("input:button").click(function(e)
{
	$.ajax({
        url: "NewFileFolder06",
        type: 'POST',
        data: 
        {
            "filename": $("input:text").val(),
            "id": $("input:hidden").val()
        },
        success: function(data1)
        {	
        document.location.href='FileFolderDisplay06?id='+data1;
   		} 
         });
});
});
</script>
</head>
<body>

<c:if test="${empty param.name}">
<c:set var="root">ROOT</c:set>
</c:if>
<c:if test="${not empty param.name}">
<c:set var="root">${param.name}</c:set>
</c:if>



		<input type="hidden" name="id" value="${param.id}"/>
		<c:out value="${root} /">${root}</c:out> 
		<br>
		<table class="newfol">
		<tr><td>New Folder <input type="text" name="filename" /></td><tr>
		
		<tr><td><input class="button3" type="button" name="create" value="Create" /></td></tr>
		</table>
	
</body>
</html>