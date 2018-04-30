// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_DeleteMessage.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_DeleteMessage extends HttpServlet
{

    public Serv_DeleteMessage()
    {
        st = null;
        st1 = null;
        rs = null;
        rs1 = null;
        con = null;
        scon = null;
        rcnt = 0;
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
            st1 = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            int i = Integer.parseInt(httpservletrequest.getParameter("h"));
            for(int j = 1; j <= i; j++)
            {
                String s = httpservletrequest.getParameter("cb" + j);
                if(s != null)
                {
                    int k = Integer.parseInt(s);
                    y = st1.executeUpdate("delete from newcompose where mailid=" + k);
                    if(y > 0)
                    {
                        rcnt++;
                        servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue>");
                        servletoutputstream.println("</body></html>");
                        httpservletresponse.setHeader("Refresh", "2;URL=Serv_SentMessages");
                    }
                }
            }

            servletoutputstream.println("<h3><i>" + rcnt + "Record(s) deleted </i></h3>");
            rcnt = 0;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    Statement st;
    Statement st1;
    ResultSet rs;
    ResultSet rs1;
    Connection con;
    ServletContext scon;
    String uname;
    int y;
    int rcnt;
}
