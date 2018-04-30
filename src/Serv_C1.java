

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
 * Servlet implementation class Serv_C1
 */
@WebServlet("/Serv_C1")
public class Serv_C1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Serv_C1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
			ServletContext sc = request.getServletContext();
		
			try {
        	ServletOutputStream servletoutputstream = response.getOutputStream();
        	
        	
        	
			servletoutputstream.println("<html><title>Intranet Mailing System</title>");
			servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=blue><center><img src='ims%20images/banner.jpg' border=0 width=250 height=80></center>");
	        servletoutputstream.println("<pre>");
	        servletoutputstream.println("<form name=form4 method=get action='Serv_C1'enctype='multipart/form-data'>");
	        servletoutputstream.println("To                  :<input type=text name=to value= ''><br>");
	        servletoutputstream.println("Message             :<input type=text name=message value=''><br>");
	        servletoutputstream.println("<input type=submit name=submit1 value='SEND'><br>");
	        servletoutputstream.println("<p></p><p></p><p></p><p></p><p></p>");
	        servletoutputstream.println("<a href=Serv_List target=in1><img src='ims%20images/back.jpg' border=0 width=54 height=54></a>");
	        HttpSession session = request.getSession(); 
	//	       synchronized(sc)
	//	       {

	        Connection con=ConnectionProvider.getConnection();

	        String to = (String) request.getParameter("to");
	        String msg = (String) request.getParameter("message");
	        String from = (String) session.getAttribute("uname");
	        System.out.println("bfore writing"+con);
	        try
	        {
	        	if((msg!=null))
	        	{
	        PreparedStatement ps=con.prepareStatement("insert into chats values(?,?,?)");
	        ps.setString(1, from);
	        ps.setString(2, to);
	        ps.setString(3, msg);
	        ps.executeUpdate();
	        System.out.println("Messages stored");
	        	}
	        	}
	        catch(Exception e){System.out.println("Unable to store messagesssss");}
	        System.out.println("Outside storing messages");
	        ServletContext ct = request.getServletContext(); 
	 //      }
	      
	     //  session.setAttribute("msg",msg);
	     //  session.setAttribute("from",from);
	        
	        
	        
	        
	        
	        
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to store message");
			e.printStackTrace();
		} 

	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
