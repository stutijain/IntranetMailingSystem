// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_AddFolder.java

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_AddFolder extends HttpServlet
{

    public Serv_AddFolder()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
     //       Class.forName("oracle.jdbc.driver.OracleDriver");
      //      con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");


	con=ConnectionProvider.getConnection();

            st = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            newf = httpservletrequest.getParameter("fname");
            httpsession.putValue("foldername", newf);
            String s = httpsession.getValue("foldername").toString();
            System.out.println("newf: " + s);
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            int i = st.executeUpdate("insert into folders values('" + uname + "','" + newf + "')");
            System.out.println("i:" + i);
            servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue><h2><i>Folder " + newf + " Created Successfully</i></h2></body></html>");
            httpservletresponse.setHeader("Refresh", "2;URL='Serv_Inbox'");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    Statement st;
    Connection con;
    ServletContext scon;
    String uname;
    String newf;
}
