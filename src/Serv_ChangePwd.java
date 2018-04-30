// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_ChangePwd.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_ChangePwd extends HttpServlet
{

    public Serv_ChangePwd()
    {
    }

    public void init(ServletConfig servletconfig)
    {
        try
        {
            super.init(servletconfig);
      //      Class.forName("oracle.jdbc.driver.OracleDriver");
       //     con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();


            st = con.createStatement();
            scon = getServletContext();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
            HttpSession httpsession = httpservletrequest.getSession(true);
            un = httpsession.getValue("name").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            String s = httpservletrequest.getParameter("np");
            int i = st.executeUpdate("Update signupdetails set passwd='" + s + "' where uname='" + un + "'");
            servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue><h2><i>password is changed</i></h2></body></html>");
            httpservletresponse.setHeader("Refresh", "2;URL=Serv_Inbox");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    Statement st;
    Connection con;
    ResultSet rs;
    ServletContext scon;
    String un;
}
