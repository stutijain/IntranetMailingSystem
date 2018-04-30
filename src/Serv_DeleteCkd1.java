// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_DeleteCkd1.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_DeleteCkd1 extends HttpServlet
{

    public Serv_DeleteCkd1()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
            int i = 0;
           // Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();

            st = con.createStatement();
            st1 = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            int j = Integer.parseInt(httpservletrequest.getParameter("hid"));
            for(int k = 1; k <= j; k++)
            {
                String s = httpservletrequest.getParameter("b" + k);
                if(s != null)
                {
                    int l = Integer.parseInt(s);
                    y = st1.executeUpdate("delete from newfolder where mid=" + l);
                    if(y > 0)
                    {
                        i++;
                        servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue>");
                        servletoutputstream.println("</body></html>");
                        httpservletresponse.setHeader("Refresh", "2;URL=Serv_Show_Folder");
                    }
                }
            }

            servletoutputstream.println("<h3><i>" + i + "Record deleted </i></h3>");
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
}
