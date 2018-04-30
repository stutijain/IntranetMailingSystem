// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_SeeMessage1.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_SeeMessage1 extends HttpServlet
{

    public Serv_SeeMessage1()
    {
        st = null;
        st1 = null;
        rs = null;
        con = null;
        scon = null;
        session = null;
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
      //      Class.forName("oracle.jdbc.driver.OracleDriver");
         //   con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();


            scon = getServletContext();
            session = httpservletrequest.getSession(true);
            uname = session.getValue("name").toString();
            st = con.createStatement();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            int i = Integer.parseInt(httpservletrequest.getParameter("id"));
            for(rs = st.executeQuery("select mailfrom,mailto,subject,maildate,mailcc,mailbcc,maildata,mailst from newcompose where mailfrom='" + uname + "' and mailid=" + i); rs.next(); servletoutputstream.println("</form></body></html>"))
            {
                String s = rs.getString(1);
                session.putValue("mfrom", s);
                String s1 = rs.getString(2);
                String s2 = rs.getString(3);
                String s3 = rs.getString(4);
                String s4 = rs.getString(5);
                String s5 = rs.getString(6);
                String s6 = rs.getString(7);
                servletoutputstream.println("<html><head><title>Welcome to Intranet Mailing System</title></head>");
                servletoutputstream.println("<form name=fo>");
                servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=blue><pre>");
                servletoutputstream.println("From             :  " + s + "<br>");
                servletoutputstream.println("To               :  " + s1 + "<br>");
                servletoutputstream.println("Cc               :  " + s4 + "<br>");
                servletoutputstream.println("Bcc              :  " + s5 + "<br>");
                servletoutputstream.println("Subject          :  " + s2 + "<br><br>");
                servletoutputstream.println("<textarea rows=10 cols=60 name=t6 readonly>" + s6 + "</textarea>");
                st1 = con.createStatement();
                x = st1.executeUpdate("update newcompose set mailst=0");
                st1.close();
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
    int x;
    HttpSession session;
}
