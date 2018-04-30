// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_ModifyFolder.java

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_ModifyFolder extends HttpServlet
{

    public Serv_ModifyFolder()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();


            st = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            nf = httpservletrequest.getParameter("newfol");
            System.out.println("new folder:" + nf);
            String s = httpservletrequest.getParameter("hid");
            System.out.println("old folder:" + s);
            int i = st.executeUpdate("update folders set folder='" + nf + "' where uname='" + uname + "' and folder='" + s + "'");
            System.out.println("r :" + i);
            if(i > 0)
            {
                servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue><h2><i>One record updated with latest modifications</i></h2></body></html>");
                httpservletresponse.setHeader("Refresh", "2;URL='Serv_Folder'");
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
    String uname;
    String nf;
}
