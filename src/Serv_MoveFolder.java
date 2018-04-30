// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_MoveFolder.java

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_MoveFolder extends HttpServlet
{

    public Serv_MoveFolder()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
            boolean flag = false;
          //  Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("driver loaded");
           // con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");


	con=ConnectionProvider.getConnection();


            System.out.println("connection created");
            st = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            String s = httpservletrequest.getParameter("se");
            httpsession.putValue("newfold", s);
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            String s1 = httpservletrequest.getParameter("hid");
            System.out.println("hidden: " + s1);
            int i = Integer.parseInt(s1);
            System.out.println("cnt: " + i);
            for(int j = 1; j <= i; j++)
            {
                String s2 = httpservletrequest.getParameter("b" + j);
                System.out.println("chk: " + s2);
                if(s2 != null)
                {
                    int k = Integer.parseInt(s2);
                    for(rs = st.executeQuery("select mailid,mailfrom,mailto,subject,mailcc,maildata,maildate,mailst from newcompose where mailid=" + k); rs.next();)
                    {
                        id = rs.getInt(1);
                        from = rs.getString(2);
                        System.out.println("from: " + from);
                        to = rs.getString(3);
                        System.out.println("to :" + to);
                        subj = rs.getString(4);
                        mcc = rs.getString(5);
                        data = rs.getString(6);
                        mdate = rs.getString(7);
                        status = rs.getInt(8);
                    }

                    int l = st.executeUpdate("insert into newfolder values(" + k + ",'" + from + "','" + to + "','" + subj + "','" + mcc + "','" + data + "','" + s + "',"+status+",'" + mdate + "')");
                    int i1 = st.executeUpdate("delete from newcompose where mailid=" + k);
                    if(l > 0)
                    {
                        servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue>");
                        servletoutputstream.println("<h2><i>Informations successfully saved in " + s + "</i></h2><br><br>");
                        httpservletresponse.setHeader("Refresh", "2;URL=Serv_Folder");
                    }
                }
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
    ResultSet rs1;
    Connection con;
    ServletContext scon;
    String uname;
    String newfol;
    String from;
    String to;
    String subj;
    String data;
    String date;
    String mdate;
    String mcc;
    int id;
    int status;
}
