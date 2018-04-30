// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_MoveMessage.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_MoveMessage extends HttpServlet
{

    public Serv_MoveMessage()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
            int j = 0;
           // Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();

            st = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            String s = httpservletrequest.getParameter("se");
            int k = Integer.parseInt(httpservletrequest.getParameter("hid"));
            for(int l = 1; l <= k; l++)
            {
                String s1 = httpservletrequest.getParameter("b" + l);
                if(s1 != null)
                {
                    int i1 = Integer.parseInt(s1);
                    int i = st.executeUpdate("update newcompose set folder='" + s + "'  where mailid=" + i1);
                    if(i > 0)
                    {
                        j++;
                        servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue>");
                        servletoutputstream.println("</body></html>");
                        httpservletresponse.setHeader("Refresh", "2;URL='Serv_Inbox'");
                    }
                }
            }

            servletoutputstream.println("<h3><i>" + j + " Message(s) moved into " + s + " </i></h3>");
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
    String fl;
}
