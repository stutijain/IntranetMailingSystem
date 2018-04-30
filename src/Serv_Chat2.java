import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import connect.ConnectionProvider;


public class Serv_Chat2 extends HttpServlet  {
	Connection con;
	Statement st;
	ResultSet rs;
 static	ArrayList<String> al;
	public void init(){
		al=new ArrayList<String>();
		con = ConnectionProvider.getConnection();
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{

    	ServletOutputStream servletoutputstream;
		try {
			servletoutputstream = response.getOutputStream();
			servletoutputstream.println("<html><title>Intranet Mailing System</title>");
			servletoutputstream.println("<body bgcolor=white background='ims%20images/backg.jpg' text=blue><center><img src='ims%20images/banner.jpg' border=0 width=700 height=80></center>");
			
	
			String msg,from,to;
			ServletContext ct = request.getServletContext();
			HttpSession session = request.getSession();
			String current = (String) session.getValue("name");
			
			
			synchronized(ct)
			{
				msg = (String) ct.getAttribute("msg");
				from = (String) ct.getAttribute("from");
				to = (String)ct.getAttribute("to");
				if(msg!=null&&to.equalsIgnoreCase(current))
				{
				al.add(from+" : "+msg);
				ct.removeAttribute("msg");
				}
				Iterator i = al.iterator();
				while(i.hasNext()){
				servletoutputstream.println("<p> <font face='Comic sans MS' size='5'> "+i.next()+"</font></p>");
				}
				
			}
			
			//con = ConnectionProvider.getConnection();
			//System.out.println("jhggjjj"+con);
			//st = con.createStatement();
			
			/*PreparedStatement preparedStatement=con.prepareStatement("select * from message");
			
			rs = preparedStatement.executeQuery();
			System.out.println(rs);
			
			while(rs.next())
			{
				String msg = rs.getString(1);
				//System.out.println(msg);
				if(msg!=null)
				{servletoutputstream.println("<p>"+msg+"</p>");}
				
			}
			
			
*/
			
			//response.setIntHeader("Refresh", 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}*/
		
		response.setIntHeader("Refresh", 1);
	}
	
}
