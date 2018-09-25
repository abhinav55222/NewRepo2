package servelet.customerinput;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/CustomerInput")
public class customerinput extends GenericServlet {
	private static final long serialVersionUID = 1L;

	private PreparedStatement pst;
	
	public void init(ServletConfig config) throws ServletException {
	
		try
		{
		Class.forName("com.mysql.jdbc.Driver");			
	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","root", "");
	    pst=con.prepareStatement("insert into customer values(?,?,?,?,?) ");
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			
		}
	}
	
	public void destroy() {

	}	
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {		
	response.setContentType("text/html");
	PrintWriter pw=response.getWriter();
	
	String cid=request.getParameter("cid");
	String cname=request.getParameter("cname");
	String cpass=request.getParameter("cpass");
	String caddress=request.getParameter("caddress");
	String ccontact=request.getParameter("ccontact");
			
	try
	{
		pst.setInt(1, Integer.parseInt(cid) );
		pst.setString(2, cname);
		pst.setString(3, cpass);
		pst.setString(4,caddress);
		pst.setLong(5,Long.parseLong(ccontact));
		
	   int result= pst.executeUpdate();
	   if(result>0)
	   {
		   pw.print("<body bgcolor=cyan><h3 align=center>Record inserted successfully</h3></body>");
	   }
	}
	catch(SQLException e)
	{
		e.printStackTrace();		
	}
	
	
	
	
	
	
	
	
	
	/*pw.print("Customer Id="+cid);
	pw.print("<br>Customer Name="+cname);
	pw.print("<br>Customer password="+cpass);
	pw.print("<br>Customer address="+caddress);
	pw.print("<br>Customer contact="+ccontact);
	pw.print("<br>Customer contact="+ccontact);*/
		
	}

}
