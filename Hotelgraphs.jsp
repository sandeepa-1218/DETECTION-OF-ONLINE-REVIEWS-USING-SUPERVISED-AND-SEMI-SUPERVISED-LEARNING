<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE	html	PUBLIC	"-//W3C//DTD	HTML	4.01	Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="w3.css">
</head>
<body>
<%
String hname=request.getParameter("hname"); String tot=request.getParameter("tot");
String hid=request.getParameter("hid");
%>
<div>
<h3 Style="text-transform: uppercase;" class="w3-text-orange">HOTEL <%=hname %> <a href="viewReview.jsp?hid=<%=hid%>" class="w3-text-green " style="right: 10px;position:static ;" target="_self " >View Reviews</a></h3>
<div style="width: 400px;height: 20px;" class="w3-red">
<div	style="width:<%=tot+'%'	%>;height:	20px;"	class="w3-green	w3-animate- left"><%=tot+'%' %></div>
</div>
 
</div>
</body>
</html>

