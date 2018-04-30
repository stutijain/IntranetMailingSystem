// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_NewFolder.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_NewFolder extends HttpServlet
{

    public Serv_NewFolder()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
          //  Class.forName("oracle.jdbc.driver.OracleDriver");
          //  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();

            st = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            servletoutputstream.println("<html><head><title>Welcome to Intranet Mailing System</title></head>");
            servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=blue> <pre>");
            servletoutputstream.println("<h2><center><blink> New Folder Screen</blink></center></h2>");
            servletoutputstream.println("<form name=f11 action='Serv_AddFolder'");
            servletoutputstream.println("<b> Enter Folder Name : </b><input type=text name=fname value=''><br>");
            servletoutputstream.println("                            <input type=submit name=sub11  value='Add-Folder' <br><br>");
            servletoutputstream.println("</form></body></html>");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    Connection con;
    Statement st;
    ServletContext scon;
    String uname;
}
