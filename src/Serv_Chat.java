import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import connect.ConnectionProvider;


public class Serv_Chat extends HttpServlet implements ServletContextAttributeListener{
	Connection con;
	String s1;
	Statement st;
	public void init()
	{
		
	     try { 
	    	 con = ConnectionProvider.getConnection();
			st = con.createStatement();
			//st.execute("create table message(to varchar(20),from varchar(20),msg varchar(50));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	}
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		
		//con=ConnectionProvider.getConnection();
		ServletContext sc = request.getServletContext();
		
        try {
        	ServletOutputStream servletoutputstream = response.getOutputStream();
			servletoutputstream.println("<html><title>Intranet Mailing System</title>");
			servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=blue><center><img src='ims%20images/banner.jpg' border=0 width=250 height=80></center>");
	        servletoutputstream.println("<pre>");
	        servletoutputstream.println("<form name=form4 method=get action='Serv_Chat'enctype='multipart/form-data'>");
	        servletoutputstream.println("To                  :<input type=text name=to value= ''><br>");
	        servletoutputstream.println("Message             :<input type=text name=message value=''><br>");
	        servletoutputstream.println("<input type=submit name=submit1 value='SEND'><br>");
	        servletoutputstream.println("<p></p><p></p><p></p><p></p><p></p>");
	        servletoutputstream.println("<a href=Serv_List target=in1><img src='ims%20images/back.jpg' border=0 width=54 height=54></a>");
	        
	        String to = (String) request.getParameter("to");
	        String msg = (String) request.getParameter("message");
	        HttpSession session = request.getSession();
	        String a = session.getId();
	        String from=(String) session.getValue("name");
	       /* ServletContext s = request.getServletContext();
	      
	        if(msg==null)
	        {
	       
	        s.setAttribute("msg",msg);
	        servletoutputstream.println("<p>"+msg+"</p><br>");
	        s1=msg;
	        }
	        else
	        {
	        	       
	      
	        
	        s.setAttribute("msg",msg);
	        s1=s1+"\n"+msg;
	        servletoutputstream.println("<p>"+s1+"</p>");
	        
	        }*/
	        
	        ServletContext ct = request.getServletContext(); 
	       synchronized(ct)
	       {
	    	   ct.setAttribute("msg", msg);
	    	   ct.setAttribute("from", from);
		   ct.setAttribute("to",to);	    	   
	       }
	      
	        session.setAttribute("msg",msg);
		 session.setAttribute("from",from);
	        
	        
	        
	        
	        
	        
	        
	        /*  PreparedStatement ps = con.prepareStatement("insert into message values(?)");
	        ps.setString(1, msg);
	        
	        ps.executeQuery();
	        ///////////////////////////
	        //response.setIntHeader("Refresh", 5);
	        */
	        
	        
	        
	        /*
	        while(true)
	        {
	        	String sw = (String) s.getAttribute("msg");
	        	if(sw==msg)
	        	{
	        		
	        	}
	        	else
	        	{	s1=s1+sw;
	        		servletoutputstream.println("<p>"+s1+"</p>");
	        	}
	        }
	        */
	        //String s1=msg1.msg;
	        
	        
            //servletoutputstream.println("<input type=submit name=submit1 value='SEND'><br>");
            
	        //s.setAttribute("msg", msg);
	        //servletoutputstream.println("<table><tr><td><a href=Upload.html><font face=verdana><h5>Attach Files</h5></font></a></td><td><input type=text name=file value=" + s1 + "/></td></tr></table>");
	        //servletoutputstream.println("<textarea name=maildata rows=10 cols=60></textarea><br>");
	        //servletoutputstream.println("<input type=submit name=submit1 value='SEND'><br>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         */
	}
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		try {
			Serv_Chat.class.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		try {
			Serv_Chat.class.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
