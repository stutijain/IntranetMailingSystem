// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_ModifyAddress.java

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_ModifyAddress extends HttpServlet
{

    public Serv_ModifyAddress()
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
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            String s = httpservletrequest.getParameter("nam");
            System.out.println("str:" + s);
            newfraddress = httpservletrequest.getParameter("add1");
            System.out.println("new address:" + newfraddress);
            int i = Integer.parseInt(httpservletrequest.getParameter("pno1"));
            System.out.println("new phone: " + i);
            newfrmid = httpservletrequest.getParameter("mid1");
            System.out.println("new frm id:" + newfrmid);
            System.out.println("str=" + s + "add=" + newfraddress + "ph=" + i + "id=" + newfrmid);
            int j = st.executeUpdate("update address set addresses='" + newfraddress + "',phone=" + i + ",emailid='" + newfrmid + "' where uname='" + s + "'");
            if(j > 0)
            {
                servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue><h2><i>" + s + "'s Address Updated Successfully</i></h2></body></html>");
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
    String newfraddress;
    String newfrmid;
    int newfrphone;
}
