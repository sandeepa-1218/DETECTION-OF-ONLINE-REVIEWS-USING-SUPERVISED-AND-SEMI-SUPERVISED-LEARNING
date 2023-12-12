package com.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher; import javax.servlet.ServletException; import javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.Beans.HotelBean;
import com.DBConnect.DbConnection;


/**
* Servlet implementation class AddHotels
*/ @WebServlet("/AddHotels")
public class AddHotels extends HttpServlet {
private static final long serialVersionUID = 1L;

/**
* @see HttpServlet#HttpServlet()
*/
public AddHotels() {
super();
// TODO Auto-generated constructor stub
}
/**
*	@see	HttpServlet#doGet(HttpServletRequest	request,	HttpServletResponse
response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
// TODO Auto-generated method stub
String uname=request.getParameter("hname"); String email=request.getParameter("city"); String pass=request.getParameter("items"); HotelBean u=new HotelBean(); u.setHname(uname);
u.setCity(email); u.setItems(pass); try {
int i=DbConnection.addHotels(u);
if(i>0)
 
{
RequestDispatcher
rd=request.getRequestDispatcher("success.jsp?msg=Hotel	Added	successfully &&to=addHotel.jsp");
rd.forward(request, response);
}else
{
RequestDispatcher rd=request.getRequestDispatcher("success.jsp?msg=Adding	Hotel Failed&&to=addHotel.jsp");
rd.forward(request, response);
}
}catch(SQLException e)
{
e.printStackTrace();
}




 

/**
 
}


*	@see	HttpServlet#doPost(HttpServletRequest	request,	HttpServletResponse
 
response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
// TODO Auto-generated method stub
}

}
