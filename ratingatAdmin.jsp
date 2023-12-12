<%@page import="com.DBConnect.DbConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE	html	PUBLIC	"-//W3C//DTD	HTML	4.01	Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="w3.css">
<link rel="stylesheet" href="win8.css">
</head>
<body Style="background-image:url(pics/back1.jpeg);">
<h1 class="w3-white w3-text-pink w3-card-4 w3-center" style="font-size: 50px;">Supervised and Unsupervised Aspect Category
Detection for Sentiment Analysis With Co-Occurrence Data</h1>
<div class="w3-bar w3-win8-steel">
<a href=adminhome.jsp class="w3-button w3-bar-item w3-blue">Back</a>
<a href=adminhome.jsp class="w3-button w3-bar-item w3-blue">Home</a>
<a href=HotelsAtAdmin.jsp class="w3-button w3-bar-item w3-blue">Hotel list</a>
<a href=login.jsp class="w3-button w3-bar-item w3-red">Logout</a>
</div><br>
<center><div class="w3-win8-steel" style="width: 500px;">
<h1 style="width: 100%;" class="w3-red w3-center">HOTELS PERCENTAGE</h1>
<%
 
int row=0;
ResultSet r=DbConnection.getCount();
if(r.next())
{
row=r.getInt(1);
}
for(int i=1;i<=3;i++)
{
%>
<div	style=""	class="w3-padding"><iframe	src="Rating?hid=<%=i	%>" height="100px" width=100% frameBorder="0" style="border-collapse: collapse;" >
</iframe></div><%
}
%>
</div></center>
</body>
</html>
