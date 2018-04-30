// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_Option.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_Option extends HttpServlet
{

    public Serv_Option()
    {
    }

    public void init(ServletConfig servletconfig)
    {
        try
        {
            super.init(servletconfig);
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

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
            String s = httpsession.getValue("name").toString();
            rs = st.executeQuery("select passwd from signupdetails where uname='" + s + "'");
            String s1 = "";
            try
            {
                rs.next();
                s1 = rs.getString(1);
            }
            catch(Exception exception1) { }
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            servletoutputstream.println("<html><head><title>Welcome to Intranet Mailing System></title></head>");
            servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=blue><h1><center><blink>Change PassWord Screen</blink></center></h1>");
            servletoutputstream.println("<form name=form5 method=post action='Serv_ChangePwd'>");
            servletoutputstream.println("<pre><b><h4><center>Allows To Change PassWord</center></h4></b><BR><BR>");
            servletoutputstream.println("<b>Old PassWord       </b>:<input type=password name=op value=" + s1 + "><br>");
            servletoutputstream.println("<b>New PassWord </b>      :<input type=password name=np value=''><br><br>");
            servletoutputstream.println("<input type=submit name=s3 value='CHANGE'></form></body></HTML>");
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
}
