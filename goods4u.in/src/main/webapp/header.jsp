<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="scripts/jquery-2.1.1.min.js"></script>
<script src="scripts/jqxcore.js"></script>
<script src="scripts/jqxmenu.js"></script>
<script src="scripts/toggle.js"></script>
<script>
function loadNavigation() {
	var xmlhttp;
	xmlhttp = new XMLHttpRequest();
    var url = "http://localhost:8080/goods4u.in/rest/defaultNavigation";
    xmlhttp.open('GET',url,true);
    xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    
    xmlhttp.send(null);
    
    xmlhttp.onreadystatechange = function() {
           
           if (xmlhttp.readyState == 4) {
              if ( xmlhttp.status == 200) {
                   var det = eval( "(" +  xmlhttp.responseText + ")");
                   
                   //Loading menu data
                   var menuData=det.hierarchies;
                   
                   //Recursive function to load the unordered list
                   var builder = function (parent, items) {
                	   $.each(items, function(){
                		  var li = $("<li><a href=\"." + this.link + "\">" + this.value + "</a></li>");
                		  li.appendTo(parent);
                		  if(this.nextGen && this.nextGen.length>0){
                			  var ul = $("<ul></ul>");
                			  ul.appendTo(li);
                			  builder(ul,this.nextGen);
                		  }
                	   });
                   };
                   var ul = $("<ul></ul>");
                   ul.appendTo("#navDiv");
                   builder(ul,menuData);
                   $("#navDiv").jqxMenu({ width: '600', height: '30px'});
             }
             else{
           		//alert("Error ->" + xmlhttp.responseText);
           		alert("Something went wrong! Please try after some time, we are working on it.");
             }
              
          }
    };
}
</script>
<link rel="stylesheet" type="text/css" href="styles/template.css">
<link rel="stylesheet" type="text/css" href="styles/jqx.base.css">
<link rel="stylesheet" type="text/css" href="styles/site.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="loadNavigation()">
	<table width="100%">
		<tr>
			<td style="width:5%"/>
			<td class="container" style="width:90%">
				<table width="100%">
					<tr>
						<td class="bannerContainer" colspan="2"><h2>Welcome to Goods4u.in</h2>
						<td class="settingContainer">
							<jsp:include page="plugins/settings.jsp"></jsp:include>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="menubarContainer">
							<div id="navDiv" style="width:1000px"></div>
						</td>
					</tr>
				</table>
			</td>
			<td style="width:5%"/>
		</tr>
		<tr>
			<td style="width:5%"/>
			<td class="cntBody" style="width:90%">