// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_FolderMessage.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Serv_FolderMessage extends HttpServlet
{

    public Serv_FolderMessage()
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
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");


            scon = getServletContext();
            session = httpservletrequest.getSession(true);
            uname = session.getValue("name").toString();
            int i = Integer.parseInt(httpservletrequest.getParameter("id"));
            st = con.createStatement();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            for(rs = st.executeQuery("select mfrom,mto,sub,mcc,mdata,folder,mailst,maildate from newfolder where mid=" + i); rs.next(); servletoutputstream.println("</form></body></html>"))
            {
                String s = rs.getString(1);
                String s1 = rs.getString(2);
                String s2 = rs.getString(3);
                String s3 = rs.getString(4);
                String s4 = rs.getString(5);
                String s5 = rs.getString(6);
                String s6 = rs.getString(8);
                servletoutputstream.println("<html><head><title>Welcome to Intranet Mailing System</title></head>");
                servletoutputstream.println("<form name=fo>");
                servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=blue><pre>");
                servletoutputstream.println("<img src=adv2.gif>");
                servletoutputstream.println("</td><td><a href=Serv_Show_Folder>Folder</a></td></tr></table><hr>");
                servletoutputstream.println("<pre><br>" + s + " wrote a mail on " + s6 + "<br>");
                servletoutputstream.println("<p>" + s4 + "</pre><hr>");
                servletoutputstream.println("</td><td><a href=Serv_Show_Folder>Folder</a></td></tr></table>");
                servletoutputstream.println("<center><img src=adv1.gif>");
                st1 = con.createStatement();
                x = st1.executeUpdate("update newfolder set mailst=0");
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
