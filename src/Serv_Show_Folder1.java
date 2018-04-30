// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_Show_Folder1.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_Show_Folder1 extends HttpServlet
{

    public Serv_Show_Folder1()
    {
        sos = null;
        col = 1;
        myfolder = "";
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
            boolean flag = false;
          //  Class.forName("oracle.jdbc.driver.OracleDriver");
          //  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");


	con=ConnectionProvider.getConnection();

            st = con.createStatement();
            st1 = con.createStatement();
            scon = getServletContext();
            sos = httpservletresponse.getOutputStream();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            myfolder = httpservletrequest.getParameter("foldval");
            ResultSet resultset = st.executeQuery("select mid,mfrom,sub,mcc,mailst,maildate from newfolder where folder='" + myfolder + "'");
            sos.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue>");
            sos.println("<script>function d()");
            sos.println("{document.f.action='Serv_DeleteCkd1';");
            sos.println("document.f.submit();}");
            sos.println("</script></head>");
            sos.println("<h2><i>Contents of folder&nbsp;&nbsp;</i>" + myfolder + "</h2><br><br><br><br>");
            sos.println("<form name=f>");
            sos.println("<input type=button name=delete value='Delete' onClick='d()' style=\"width:100\">");
            sos.println("<input type=reset name=deselect value='DeSelect' style=\"width:100\"><br><br>");
            sos.println("<br><table border=2 cellpadding=4 cellspacing=5 width=80%><TR bgcolor=#00FFFF><th><font color=red>Status</th><TH><font color=red>From</TH><TH><font color=red>Subject</Th><TH><font color=red>Date</TH></TR>");
            int i = 0;
            while(resultset.next()) 
            {
                if(col % 2 == 0)
                    color = "violet";
                else
                    color = "pink";
                col++;
                i++;
                m = resultset.getInt(1);
                from = resultset.getString(2);
                sub = resultset.getString(3);
                cc = resultset.getString(4);
                sta = resultset.getInt(5);
                date1 = resultset.getString(6);
                if(sub == null)
                    sub = "[NONE]";
                if(sta == 1)
                {
                    sos.println("<tr><td><center><img src='BUTTON.GIF' height=20 width=25 ></td><td>");
                    sos.println("<input type=checkbox name=b" + i + " value=" + m + "></td><td><a href='Serv_FolderMessage?id=" + m + "'>" + from + "</td></a><td>" + sub + "</td><td>" + date1 + "</td></tr>");
                } else
                {
                    sos.println("<tr><td width=6%><input type=checkbox name=b" + i + " value=" + m + "></td><td width=24%><a href='Serv_FolderMessage?id=" + m + "'>" + from + "</td></a><td width=23%>" + sub + "</td><td width=47%>" + date1 + "</td></tr>");
                }
            }
            sos.println("<script language=javascript>");
            sos.println("function chkit(){");
            for(int j = 1; j <= i; j++)
                sos.println("document.f.b" + j + ".checked=document.f.chkall.checked;");

            sos.println("}</script>");
            sos.println("<input type=hidden name=hid  value=" + i + ">");
            sos.println("</form>");
            sos.println("</table></body></html>");
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
    ServletOutputStream sos;
    String fold;
    String uname;
    String newfol;
    String from;
    String sub;
    String date1;
    String cc;
    int m;
    int y;
    int sta;
    int col;
    String color;
    String myfolder;
}
