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
<form action="SubmitReview" method="get">
<div class="w3-display-middle w3-center ">
<%
String hid=request.getParameter("hid");
if(hid==null)
{
 
%>
<select name=shid><%
ResultSet r=DbConnection.getHotels();
while(r.next())
{
%>
<option value=<%=r.getString(1) %>><%=r.getString(2) %></option>
<%
}
%>
</select>
<%

}else{
 
%>
<select name=shid><%
ResultSet r=DbConnection.getHotels();
while(r.next())
{
%>
<option value=<%=r.getString(1) %>><%=r.getString(2) %></option>
<%
}
%>
</select>
<%

ResultSet r=DbConnection.getHotels();
while(r.next())
{
 
if(r.getString(1).equals(hid))
{
%>


<h1	class="w3-text-white	w3-win8-steel	w3-padding">Write	review	on	hotel
<%=r.getString(2) %></h1>
<input type="hidden" name=shid value=<%=hid %>>
<%
 
}
}


}
%>
<textarea rows="3" cols="20" name=text class="w3-padding"></textarea><br><br>
<input type="submit" value="Submit Review" class="w3-button w3-red ">
</div>
</form>
</body>
</html>
