<!DOCTYPE html>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="style5.css">

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
function del()
{ event.preventDefault();
   var row = $(this).closest("tr");
   var td=$(this);

   $.ajax({
       url: "DeleteFileFolder06",
       data: {
           "id" : row.attr("data-entry-id")
       },
       success: function(){
           row.remove();
       }
   });
}

function ahr()
		{
		var input=$(this).prev();
	
			$.ajax({
		        url: "NewFileFolder06",
		        type: 'POST',
		        data: 
		        {
		            "filename": input.val(),
		            "id": input.attr("data-entry-id")
		        },
		        success: function(data1)
		        {	
		        document.location.href='FileFolderDisplay06?id='+input.attr("data-entry-id");
		   		} 
		         });
		}


function sharb()
{ 
   var sel = $(this).closest("tr").find("select").val();
   var row = $(this).closest("tr");
   var bt=$(this);

   $.ajax({
       url: "ShareFile06",
       data: {
           "userid" : sel,
           "fileid": row.attr("data-entry-id")
       },
      
   });
}




$(function(){

	$(".del").click(del);
	$(".sharb").click(sharb);
	$(".ahr").click(ahr);

});
</script>


</head>
<body>


<c:choose>
<c:when test="${(not empty param.id) and (param.id != 'null') and (not empty fn:trim(param.id)) }">
<h1 class="cent uppercase"><c:out value="${user1}"/></h1>
<a href="FileFolderDisplay06?id=${requestScope.parentid}"><i class="fa fa-level-up" aria-hidden="true">BACK</i></a> 
<div class="dropdown">
<button class="dropbtn">${requestScope.currentname}<i class="fa fa-sort-desc" aria-hidden="true"></i></button>
<div class="dropdown-content">
<input class="iahr" data-entry-id="${param.id}" type="text" ></input>
<button class="ahr"> Create</button>
<a href="UploadFile06?id=${param.id}&name=${requestScope.currentname}">Upload File</a> 
</div>
</div>


<a style="float:right; margin-right:100px" href="<c:url value='/FileFolderLogout06'/>"><i class="fa fa-sign-out fa-lg" aria-hidden="true">Logout</i></a>
<c:set var="parentidset" value="${param.id}"/>
</c:when>


<c:when test="${(empty param.id) or (empty fn:trim(param.id)) or ((param.id eq 'null')) }">
<h1 class="cent uppercase"><c:out value="${user1}"/></h1>
<div class="dropdown">
 <button class="dropbtn">${requestScope.currentname}<i class="fa fa-sort-desc" aria-hidden="true"></i></button>
 <div class="dropdown-content">
<input class="iahr" data-entry-id="" type="text" ></input>
<button class="ahr"> Create</button>

<a href="UploadFile06">Upload File</a> 
</div>
</div>

<a style="float:right; margin-right:100px" href="<c:url value='/FileFolderLogout06'/>"><i class="fa fa-sign-out fa-lg" aria-hidden="true">Logout</i></a>
<c:set var="parentidset" value="${fn:trim(param.id)}"/>
</c:when>
</c:choose>

<table>

<tr><th> NAME</th> <th> DATE</th> <th>SIZE</th> <th>OPERATIONS</th> </tr>


<c:forEach items="${fr06}" var="subfolder">

<c:choose>

<c:when test="${(not empty param.id) and (not empty fn:trim(param.id)) and (param.id eq subfolder.parentId) or (param.id eq 1)}">

<tr data-entry-id="${subfolder.id}">

<td>
<c:if test="${subfolder.folder==true}">
<a href="FileFolderDisplay06?id=${subfolder.id}"><i class="fa fa-folder" aria-hidden="true">${subfolder.name}</i></a>
</c:if>
<c:if test="${subfolder.folder==false}">
<a href="DownloadFile06?id=${subfolder.parentId}&name=${subfolder.id}"><i class="fa fa-file">${subfolder.name}</i>  <i class="fa fa-download" aria-hidden="true"></i></a>
</c:if>
</td>

<td><fmt:formatDate pattern="MM/dd/yyyy hh:mm a" value="${subfolder.date}" /></td>

<td>   
<c:if test="${subfolder.folder==false}">
<c:set var="filesize">${(subfolder.size<1024)?(subfolder.size):(subfolder.size/1024)}</c:set>
<fmt:formatNumber var="formatedfilesize" value="${filesize}" type="NUMBER"/> 
<c:out value="${formatedfilesize} ${(subfolder.size<1024)?'B':'KB'} "/>
</c:if>
</td>

<td>

<c:if test="${subfolder.folder==false}">
<select name="userselect">
        <c:forEach items="${ur06}" var="ur">
        <option value="${ur.id}">${ur.uname}</option>
        </c:forEach>
 </select>

<button class="sharb">share</button>

</c:if>

<c:if test="${param.id!=1}">
<a href="EditFileFolder06?id=${subfolder.id}&name=${subfolder.name}"> <i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a> <c:out value="|"/>
</c:if>

<c:if test="${param.id==1}">
<a href="EditFileFolder06?id=${subfolder.id}&name=${subfolder.name}&shared=1"> <i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a> <c:out value="|"/>
</c:if>

<a class="del" href="#"><i class="fa fa-trash fa-lg" aria-hidden="true"></i></a>
</td>
</tr>
</c:when>



<c:when test="${(empty parentidset) and (empty subfolder.parentId) or (parentidset eq 'null') }">
<tr data-entry-id="${subfolder.id}">

<td>
<c:if test="${subfolder.folder==true}">
<a href="FileFolderDisplay06?id=${subfolder.id}"><i class="fa fa-folder" aria-hidden="true">${subfolder.name}</i></a>
</c:if>
<c:if test="${subfolder.folder==false}">
<a href="DownloadFile06?id=${subfolder.parentId}&name=${subfolder.id}"><i class="fa fa-file">${subfolder.name}</i>   <i class="fa fa-download" aria-hidden="true"></i></a>
</c:if>
</td>

<td><fmt:formatDate pattern="MM/dd/yyyy hh:mm a" value="${subfolder.date}" /></td>

<td>   
<c:if test="${subfolder.folder==false}">
<c:set var="filesize">${(subfolder.size<1024)?(subfolder.size):(subfolder.size/1024)}</c:set>
<fmt:formatNumber var="formatedfilesize" value="${filesize}" type="NUMBER"/> 
<c:out value="${formatedfilesize} ${(subfolder.size<1024)?'B':'KB'} "/>
</c:if>
</td>

<td>

<c:if test="${subfolder.folder==false}">
<select name="userselect">
        <c:forEach items="${ur06}" var="ur">
        <option value="${ur.id}">${ur.uname}</option>
        </c:forEach>
 </select>
<button class="sharb">share</button>

</c:if>

<c:if test="${subfolder.id != 1}">
<a href="EditFileFolder06?id=${subfolder.id}&name=${subfolder.name}"> <i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i></a> <c:out value="|"/> 
<a class="del" href="#"><i class="fa fa-trash fa-lg" aria-hidden="true"></i></a>
 </c:if>
</td>
</tr>
</c:when>

</c:choose>
</c:forEach>
</table>


</body>
</html>