// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_List.java

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_List extends HttpServlet
{

    public Serv_List()
    {
        scon = null;
        newfol = null;
        f1 = null;
        uname = null;
        sub = null;
        from = null;
        date1 = null;
        folder = null;
    }
    
    public void init()
	{
		con = ConnectionProvider.getConnection();
		try {
			st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {	//st.executeQuery("delete from message");
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
           // uname=(String) httpsession.getAttribute("uname");
            httpsession.setAttribute("uname", uname);
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            servletoutputstream.println("<html><head><title>Welcome To Intranet Mailing System</title><!--mstheme--><link rel=stylesheet href='copy1111.css'>");
            servletoutputstream.println("<meta name=Microsoft Theme content=copy-of-industrial 1111>");
            servletoutputstream.println("</head><body leftmargin=0 background='INDTEXTB.JPG' Topmargin=0><!--table cellpadding=16 align=center-->");
           
            servletoutputstream.println("<center><pre><i><b><a href=Serv_Inbox target=in2><img src='ims%20images/INBOX.JPG' border=0 width=108 height=40></a>");
            servletoutputstream.println("<a href=Serv_Compose target=in2><img src='ims%20images/COMPOSE.JPG' border=0 width=108 height=40></a></b></i><font color=#FFFFFF>1</font><i><b>");
            servletoutputstream.println("<a href=Serv_Option target=in2><img src='ims%20images/Options.jpg' border=0 width=108 height=40 lowsrc=Images%20Raj/Options.jpg></a><font color=#FFFFFF>1</font>");
            servletoutputstream.println("<pre><i><b><a href=Serv_Logout target=_parent><img src='ims%20images/Logout.jpg' border=0 width=108 height=36></a></b></i></pre><center>");
            servletoutputstream.println("<a href=Serv_C1 target=in1><img src='ims%20images/startchat.jpg' border=0 width=100 height=100></a>");
            servletoutputstream.println("<a href=Serv_C target=in2><img src='ims%20images/chatbox.jpg' border=0 width=100 height=100></a>");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    ServletContext scon;
    String newfol;
    String f1;
    String uname;
    String sub;
    String from;
    String date1;
    String folder;
    Connection con;
    Statement st;
}
