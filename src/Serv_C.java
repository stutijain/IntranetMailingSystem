

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connect.ConnectionProvider;

/**
 * Servlet implementation class Serv_C
 */
@WebServlet("/Serv_C")
public class Serv_C extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Serv_C() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=null;
    	ServletOutputStream servletoutputstream;
		try {
			
			servletoutputstream = response.getOutputStream();
			servletoutputstream.println("<html><title>Intranet Mailing System</title>");
			servletoutputstream.println("<body bgcolor=white background='ims%20images/backg.jpg' text=blue><center><img src='ims%20images/banner.jpg' border=0 width=700 height=80></center>");
			HttpSession session=request.getSession();
			ServletContext ct = request.getServletContext();
//			synchronized (ct) {
					

				con=ConnectionProvider.getConnection();

			String msg,from,to;
			PreparedStatement ps=con.prepareStatement("select * from chats where to1=? or from1=?");
			ResultSet rs;	
			System.out.println(""+session.getAttribute("uname"));
				ps.setString(1,(String) session.getAttribute("uname"));
				ps.setString(2,(String) session.getAttribute("uname"));
				
				rs=ps.executeQuery();
				
				while(rs.next())
				{
					if(rs.getString(1).equalsIgnoreCase((String) session.getAttribute("uname")))
					servletoutputstream.println("<font face='Comic sans MS' size='3'>To: "+rs.getString(2)+" : "+rs.getString(3)+"</font><br>");
					else
						servletoutputstream.println("<font face='Comic sans MS' size='3' color='red'>From: "+rs.getString(1)+" : "+rs.getString(3)+"</font><br>");
					
				}
				con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
System.out.println("Connection = "+con);			
		}

		response.setIntHeader("Refresh", 1);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		
	}

}
