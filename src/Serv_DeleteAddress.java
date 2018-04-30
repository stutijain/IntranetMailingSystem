// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_DeleteAddress.java

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_DeleteAddress extends HttpServlet
{

    public Serv_DeleteAddress()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");


	con=ConnectionProvider.getConnection();

            st = con.createStatement();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            String s = httpservletrequest.getParameter("name");
            System.out.println("nm: " + s);
            int i = st.executeUpdate("delete from address where uname='" + s + "'");
            if(i > 0)
            {
                servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue><h2><i>Address of  " + s + "  deleted successfully</i></h2></body></html>");
                httpservletresponse.setHeader("Refresh", "2;URL='Serv_Address'");
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    Statement st;
    Connection con;
    ServletContext scon;
    String frname;
}
