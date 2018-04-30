// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_AddAddress.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_AddAddress extends HttpServlet
{

    public Serv_AddAddress()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
     //       Class.forName("oracle.jdbc.driver.OracleDriver");
     //       con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();

            st = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            name = httpservletrequest.getParameter("nam");
            nick = httpservletrequest.getParameter("nname");
            mailid = httpservletrequest.getParameter("mid");
            address = httpservletrequest.getParameter("add");
            String s = httpservletrequest.getParameter("pno");
            phone = Integer.parseInt(s);
            int i = st.executeUpdate("insert into address values('" + uname + "','" + name + "','" + nick + "','" + mailid + "','" + address + "'," + phone + ")");
            servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue><h2><i>" + name + "'s Address added successfully</i></h2></body></html>");
            httpservletresponse.setHeader("Refresh", "2;URL='Serv_Address'");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public String frdname()
    {
        return name;
    }

    public String frdnname()
    {
        return nick;
    }

    public String frdmid()
    {
        return mailid;
    }

    public String frdaddress()
    {
        return address;
    }

    public int frdphone()
    {
        return phone;
    }

    Statement st;
    Connection con;
    ServletContext scon;
    String name;
    String nick;
    String mailid;
    String address;
    String uname;
    int phone;
}
