// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_DeleteFolder.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_DeleteFolder extends HttpServlet
{

    public Serv_DeleteFolder()
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
            f = httpservletrequest.getParameter("fol");
            int i = st.executeUpdate("delete from folders where uname='" + uname + "'and folder='" + f + "'");
            if(i != 0)
            {
                servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue><h2><i>  Folder " + f + " Deleted successfully.</i></h2></body></html>");
                httpservletresponse.setHeader("Refresh", "2;URL='Serv_Folder'");
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
    String f;
}
