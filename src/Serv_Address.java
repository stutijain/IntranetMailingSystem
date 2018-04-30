// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_Address.java

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_Address extends HttpServlet
{

    public Serv_Address()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
    //        Class.forName("oracle.jdbc.driver.OracleDriver");
     //       con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();

            st = con.createStatement();
            scon = getServletContext();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            rs = st.executeQuery("select uname,nickname,emailid,addresses,phone from address where actname='" + uname + "'");
            servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue><pre>");
            servletoutputstream.println("<blink><h1>Address Screen</h1></blink><br><br>");
            servletoutputstream.println("<h2><i>Directory of '" + uname + "' :</i></h2><br><br>");
            servletoutputstream.println("<form name=f10 action='Serv_NewAddress'>");
            servletoutputstream.println("<input type=submit value='ADD'></form>");
            servletoutputstream.println("<a href='listoptions.html' target=_parent><b>BACK</b></a>");
            servletoutputstream.println("<table border=2 cellpadding=2 cellspacing=3 width=70%><tr bgcolor=brown><th>Name</th><th>Nick Name</th>");
            servletoutputstream.println("<th>E-Mailid</th><th>Address</th><th>Phone</th><th>Options</th></tr>");
            String s;
            for(; rs.next(); servletoutputstream.println("<a href='Serv_DeleteAddress?name=" + s + "'>DELETE</a></td></tr>"))
            {
                s = rs.getString(1);
                String s1 = rs.getString(2);
                String s2 = rs.getString(3);
                System.out.println("em:" + s2);
                String s3 = rs.getString(4);
                int i = rs.getInt(5);
                servletoutputstream.println("<tr><td>" + s + "</td><td>" + s1 + "</td><td>" + s2 + "</td><td>" + s3 + "</td><td>" + i + "</td><td>");
                servletoutputstream.println("<a href='Serv_EditAddress?name=" + s + "&add=" + s3 + "&pno=" + i + "&mid=" + s2 + "'>EDIT</a>");
            }

            servletoutputstream.println("</table></body></html>");
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
}
