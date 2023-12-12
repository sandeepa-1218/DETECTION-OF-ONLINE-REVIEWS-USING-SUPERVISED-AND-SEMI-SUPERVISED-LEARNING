<%@page import="java.util.StringTokenizer"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.DBConnect.DbConnection"%>
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
<a href=userhome.jsp class="w3-button w3-bar-item w3-blue">Back</a>
<a href=userhome.jsp class="w3-button w3-bar-item w3-blue">Home</a>
<a	href=hotelRatings.jsp	class="w3-button	w3-bar-item	w3-blue">Hotels	Quality Percentage</a>
<a href=login.jsp class="w3-button w3-bar-item w3-red">Logout</a>
</div><br>
<table class="w3-table w3-text-white w3-blue-grey">
<tr class="w3-border-bottom w3-text-orange">
<th class=""><h2>Hotel Name</h2></th>
<th><h2>City Name</h2></th>
<th><h2>Food Items</h2></th>
<th><h2></h2></th>
<th><h2></h2></th>
</tr>
<%
ResultSet r=DbConnection.getHotels();
while(r.next())
{
%>
<tr class="w3-border-bottom">
<td><strong><%=r.getString(2) %></strong></td>
<td><strong><%=r.getString(3) %></strong></td>
<td><%
 
StringTokenizer st=new StringTokenizer(r.getString(4),",");
while(st.hasMoreElements())
{
%>
<strong><%=st.nextElement() %></strong><br>
<%
}

%></td>
<td><a href="viewReview.jsp?hid=<%=r.getString(1) %>" class="w3-button w3- white">View Reviews</a></td>
<td><a href="giveReview.jsp?hid=<%=r.getString(1) %>" class="w3-button w3- white">Give Review</a></td>

</tr>


<%
}
%>
</table>
</body>
</html>

