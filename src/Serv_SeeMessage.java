// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_SeeMessage.java

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_SeeMessage extends HttpServlet
{

    public Serv_SeeMessage()
    {
        st = null;
        st1 = null;
        rs = null;
        con = null;
        scon = null;
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();


            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            st = con.createStatement();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            System.out.println("id:" + httpservletrequest.getParameter("id"));
            int i = 0;
            try
            {
                i = Integer.parseInt(httpservletrequest.getParameter("id"));
            }
            catch(Exception exception1) { }
            System.out.println("mi:" + i);
            for(rs = st.executeQuery("select mailfrom,mailto,subject,maildate,mailcc,mailbcc,maildata,mailst,mailid,mailexch from newcompose where mailto='" + uname + "' and mailid=" + i); rs.next(); servletoutputstream.println("</form></body></html>"))
            {
                String s = rs.getString(1);
                String s1 = rs.getString(2);
                String s2 = rs.getString(3);
                String s3 = rs.getString(4);
                String s4 = rs.getString(5);
                String s5 = rs.getString(6);
                String s6 = rs.getString(7);
	String s10=rs.getString(10);	

		
		System.out.println("attacchment got into the serv_seeMass"+s10);


	
                int j = rs.getInt(8);
                int k = rs.getInt(9);
                servletoutputstream.println("<html><head><title>Welcome to Intranet Mailing System</title></head>");
                servletoutputstream.println("<form name=fo>");
                servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=blue><pre>");
                servletoutputstream.println("<img src=adv2.gif>");
                servletoutputstream.println("<table cellpadding=15 ><tr><td><a href=Serv_Compose1?from=" + s + " & subj=" + s2 + ">Reply</a>");
                servletoutputstream.println("</td><td><a href=Serv_Inbox>Inbox</a></td></tr></table><hr>");
                servletoutputstream.println("<pre><br>" + s + " wrote a mail on " + s3 + "<br>");
                servletoutputstream.println("<p>" + s6 + "</pre>");
                servletoutputstream.println("<hr><table cellpadding=15><tr><td><a href=Serv_Compose1?from=" + s + "&subj=" + s2 + ">Reply</a>");

	System.out.println(s10.equals("null"));

	if(s10.equals("null"))
	{
		s10=null;
		System.out.println("vale in s10 is set to "+s10);
	}
	if(s10 != null)
	{
	servletoutputstream.println("<hr><table cellpadding=15><tr><td><a href=Serv_Download?from=" + s10 + "&subj=" + s2 + "+fname>attachment</a>");
	}


                servletoutputstream.println("</td><td><a href=Serv_Inbox>Inbox</a></td></tr></table>");
                servletoutputstream.println("<center><img src=adv1.gif>");
                st1 = con.createStatement();
                int l = st1.executeUpdate("update newcompose set mailst=0 where mailid=" + k);
                st1.close();
            }

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    Statement st;
    Statement st1;
    ResultSet rs;
    Connection con;
    ServletContext scon;
    String uname;
}
