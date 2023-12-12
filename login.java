package com.Servlets;


import java.io.IOException;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher; import javax.servlet.ServletException; import javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpServletResponse; import javax.servlet.http.HttpSession;
import com.Beans.HotelBean;
import com.DBConnect.DbConnection;


/**
* Servlet implementation class Login
*/ @WebServlet("/Login")
public class Login extends HttpServlet {
private static final long serialVersionUID = 1L;


/**
* @see HttpServlet#HttpServlet()
*/
public Login() {
super();
// TODO Auto-generated constructor stub
}

/**
*	@see	HttpServlet#doGet(HttpServletRequest	request,	HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
// TODO Auto-generated method stub
 
String utype=request.getParameter("utype");
if(utype==null)
{
RequestDispatcher rd=request.getRequestDispatcher("success.jsp?msg=select user type&&to=login.jsp");
rd.forward(request, response);

}
String uname=request.getParameter("uname"); String pass=request.getParameter("pass"); if(utype.equals("admin"))
{
if(uname.equals("admin")&&pass.equals("admin"))
{
 

}else{
 
response.sendRedirect("adminhome.jsp");

RequestDispatcher
 
rd=request.getRequestDispatcher("success.jsp?msg=admin login failed &&to=login.jsp"); rd.forward(request, response);

}
}else if(utype.equals("user")){ HotelBean u=new HotelBean(); u.setEmail(uname); u.setPass(pass);
try {
if(DbConnection.checkLog(u))
{
 




}else{
 
HttpSession h=request.getSession(); h.setAttribute("email", uname); response.sendRedirect("userhome.jsp");
 
RequestDispatcher rd=request.getRequestDispatcher("success.jsp?msg=user login failed &&to=login.jsp");
rd.forward(request, response);
}
} catch (SQLException e) {
// TODO Auto-generated catch block e.printStackTrace();
}

}
}


/**
*	@see	HttpServlet#doPost(HttpServletRequest	request,	HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
// TODO Auto-generated method stub
}


}
