// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_SentMessages.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_SentMessages extends HttpServlet
{

    public Serv_SentMessages()
    {
        st = null;
        con = null;
        scon = null;
        rs = null;
        s = "";
        d = "";
        mt = "";
        mcc = "";
        col = 1;
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
         //   Class.forName("oracle.jdbc.driver.OracleDriver");
         //   con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();

            st = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            servletoutputstream.println("<html><head><title>Welcome To Intranet Mailing System</title>");
            servletoutputstream.println("<script>function d1(){");
            servletoutputstream.println("document.ff.action='Serv_DeleteMessage';");
            servletoutputstream.println("document.ff.submit();");
            servletoutputstream.println("}</script></head>");
            servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=blue><h3><i>Welcome '" + uname + "' @Intranet Mailing System</i></h3>");
            servletoutputstream.println("<form name=ff><input type=button name=del value='Delete' onClick='d1()'> <input type=reset name=des value='DeSelect'>");
            servletoutputstream.println("<h3><i>Messages sent by " + uname + " till now</i></h3><br><br>");
            servletoutputstream.println("<table border=2 cellpadding=4 cellspacing=5 width=80%><tr bgcolor=brown><th>X</th><th>To</th><th>Subject</th><th>Date</th></tr>");
            rs = st.executeQuery("select mailid,mailto,subject,mailcc,maildate from newcompose where mailfrom='" + uname + "'");
            int i = 0;
            for(; rs.next(); servletoutputstream.println("<tr><td><input type=checkbox name=cb" + i + " value=" + m + "></td><td><a href='Serv_SeeMessage1?id=" + m + "'>" + mt + "</a></td><td>" + s + "</td><td>" + d + "</td></tr>"))
            {
                i++;
                m = rs.getInt(1);
                mt = rs.getString(2);
                s = rs.getString(3);
                mcc = rs.getString(4);
                d = rs.getString(5);
                if(col % 2 == 0)
                    color = "pink";
                else
                    color = "violet";
                bgcolor = "+color+";
                col++;
            }

            servletoutputstream.println("<input type=hidden name=h value=" + i + ">");
            servletoutputstream.println("</table></form></body></html>");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    Statement st;
    Connection con;
    ServletContext scon;
    ResultSet rs;
    String uname;
    String s;
    String d;
    String mt;
    String mcc;
    int m;
    int col;
    String color;
    String bgcolor;
}
