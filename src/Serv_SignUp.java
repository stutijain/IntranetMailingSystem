// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_SignUp.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_SignUp extends HttpServlet
{

    public Serv_SignUp()
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
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            p = httpservletrequest.getParameter("pwd1");
            HttpSession httpsession = httpservletrequest.getSession(true);
            httpsession.setAttribute("uname", httpservletrequest.getParameter("uname"));
            httpsession.putValue("name", httpservletrequest.getParameter("uname1"));
            u = httpsession.getValue("name").toString();
            rs = st.executeQuery("select * from signupdetails where uname='" + u + "' and  passwd='" + p + "'");
            if(rs.next())
            {
                httpservletresponse.sendRedirect("listoptions.html");
            } else
            {
                servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=red><h2><i><b>ur not a valid user! Try again using correct Loginname & Password or try registering </b></i></h2></body></html>");
                httpservletresponse.setHeader("Refresh", "3;URL=loginsc.html");
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    Connection con;
    Statement st;
    static String u;
    static String p;
    ResultSet rs;
}
