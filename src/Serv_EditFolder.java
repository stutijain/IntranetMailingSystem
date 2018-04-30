// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_EditFolder.java

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_EditFolder extends HttpServlet
{

    public Serv_EditFolder()
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
            of = httpservletrequest.getParameter("fol");
            System.out.println("of: " + of);
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue>");
            servletoutputstream.println("<form name=for method=post action='Serv_ModifyFolder'>");
            servletoutputstream.println("<h3>Changing the name of '" + of + "'</h3><br>");
            servletoutputstream.println("");
            servletoutputstream.println("<b>Folders New Name</b>  :   <input type=text name=newfol value=''><br><br>");
            servletoutputstream.println("<input type=submit name=su value='MODIFY-FOLDER'>");
            servletoutputstream.println("<input type=hidden name=hid value='" + of + "'>");
            servletoutputstream.println("</form></body></HTML>");
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
    String uname;
    String of;
}
