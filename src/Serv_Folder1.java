// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_Folder1.java

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_Folder1 extends HttpServlet
{

    public Serv_Folder1()
    {
        con = null;
        rs = null;
        scon = null;
        fol = new String[25];
        t = new int[25];
        n = new int[25];
        col = 1;
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();

            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            Statement statement = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            String s = httpsession.getValue("newfold").toString();
            rs = statement.executeQuery("select folder from folders where uname='" + uname + "'");
            System.out.println("rs: " + rs);
            int i;
            for(i = 0; rs.next(); i++)
            {
                f1 = rs.getString(1);
                fol[i] = f1;
                System.out.println("fol[i]: " + fol[i]);
            }

            statement.close();
            servletoutputstream.println("<html><head><title>Welcome To Intranet Mailing System</title></head>");
            servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=#00FFFF>");
            servletoutputstream.println("<center><blink><h1><b><font color=blue>Folder Screen</font></b></h1></blink></center><br>");
            servletoutputstream.println("<form><table border=2>");
            servletoutputstream.println("<tr bgcolor=brown><th>Folder Name</th><th>New Mails</th><th>Total Mails</th><th>Options</th></tr>");
            for(int j = 0; j < i; j++)
            {
                int k = 0;
                int l = 0;
                Statement statement1 = con.createStatement();
                ResultSet resultset = statement1.executeQuery("select mailst from newcompose where mailto='" + uname + "' and folder='" + fol[j] + "'");
                if(col % 2 == 0)
                    color = "pink";
                else
                    color = "violet";
                col++;
                while(resultset.next()) 
                {
                    int i1 = resultset.getInt(1);
                    k++;
                    if(i1 == 1)
                        l++;
                }
                t[j] = k;
                n[j] = l;
                servletoutputstream.println("<tr bgcolor=" + color + "><td><a href=Serv_Show_Folder1?foldval=" + fol[j] + ">" + fol[j] + "</td><td>" + n[j] + "</td><td>" + t[j] + "</td>");
                statement1.close();
            }

            servletoutputstream.println("</table></form></body></html>");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    Connection con;
    ResultSet rs;
    ServletContext scon;
    String uname;
    String f1;
    String fol[];
    int t[];
    int n[];
    int col;
    String color;
}
