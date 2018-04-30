// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_Enter.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_Enter extends HttpServlet
{

    public Serv_Enter()
    {
        col = 1;
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
          //  Class.forName("oracle.jdbc.driver.OracleDriver");
         //   con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");


	con=ConnectionProvider.getConnection();

            st = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            String s = httpservletrequest.getParameter("se");
            servletoutputstream.println("<html><head><title>Welcome To Intranet Mailing System</title></head>");
            servletoutputstream.println("<script>function d()");
            servletoutputstream.println("{document.f.action='Serv_DeleteCkd';");
            servletoutputstream.println("document.f.submit();}");
            servletoutputstream.println("function e()");
            servletoutputstream.println("{document.f.action='Serv_Enter';");
            servletoutputstream.println("document.f.submit();}");
            servletoutputstream.println("</script></head>");
            servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=blue>");
            servletoutputstream.println("<center><blink><h1><b>" + s + "Screen</b></h1></blink></center><br>");
            servletoutputstream.println("<h3><i>" + s + " of " + uname + " </i></h3>");
            servletoutputstream.println("<form name=f action='Serv_MoveMessage'>");
            servletoutputstream.println("<input type=button name=delete value='Delete' onClick='d()' >");
            servletoutputstream.println("<input type=reset name=deselect value='DeSelect'><br><br>");
            servletoutputstream.println("<h4><i>Messages in " + s + "</i></h4>");
            servletoutputstream.println("<table border=2>");
            servletoutputstream.println("<TR bgcolor=yellow><th>Status</th><TH>X</TH><TH>From</TH><TH>Subject</Th><TH>Date</TH></TR>");
            st1 = con.createStatement();
            rs1 = st1.executeQuery("select mailid,mailfrom,subject,maildate,mailst from newcompose where mailto='" + uname + "' and folder='" + s + "'");
            int i = 0;
            for(; rs1.next(); servletoutputstream.println("<tr bgcolor=" + color + "><td>" + sta + "</td><td><input type=checkbox name=b" + i + " value=" + m + "></td><td><a href='http://localhost:8080/servlet/Serv_SeeMessage?id=" + m + "'>" + from + "</td></a><td>" + sub + "</td><td>" + date1 + "</td></tr>"))
            {
                i++;
                m = rs1.getInt(1);
                from = rs1.getString(2);
                sub = rs1.getString(3);
                date1 = rs1.getString(4);
                sta = rs1.getInt(5);
                if(col % 2 == 0)
                    color = "pink";
                else
                    color = "violet";
                col++;
            }

            servletoutputstream.println("<input type=hidden name=hid  value=" + i + ">");
            servletoutputstream.println("</form></table></body></html>");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    Statement st;
    Statement st1;
    Connection con;
    ServletContext scon;
    ResultSet rs;
    ResultSet rs1;
    String uname;
    String sub;
    String date1;
    String from;
    int m;
    int sta;
    int col;
    String color;
}
