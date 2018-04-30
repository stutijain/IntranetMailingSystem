// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_Inbox1.java

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import connect.ConnectionProvider;

public class Serv_Inbox1 extends HttpServlet
{

    public Serv_Inbox1()
    {
        st = null;
        st1 = null;
        con = null;
        scon = null;
        rs = null;
        rs1 = null;
        newfol = null;
        f1 = null;
        uname = null;
        sub = null;
        from = null;
        date1 = null;
        folder = null;
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
           // Class.forName("oracle.jdbc.driver.OracleDriver");
           // con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();


            st = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            uname = httpsession.getValue("name").toString();
            folder = httpsession.getValue("newfold").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            servletoutputstream.println("<html><head><title>Welcome To Intranet Mailing System</title>");
            servletoutputstream.println("<script>function d()");
            servletoutputstream.println("{document.f.action='Serv_DeleteCkd';");
            servletoutputstream.println("document.f.submit();}");
            servletoutputstream.println("function e()");
            servletoutputstream.println("{document.f.action='Serv_Enter';");
            servletoutputstream.println("document.f.submit();}");
            servletoutputstream.println("</script></head>");
            servletoutputstream.println("<body bgcolor=white background='INDTEXTB.JPG' text=blue>");
            servletoutputstream.println("<blink><h1><b><i>Inbox Screen of " + uname + " </i></b></h1></blink><br>");
            servletoutputstream.println("<b><pre>                                                         <i>Folders</i></b></pre>");
            servletoutputstream.println("<div align=right><table border=1 cellpadding=4 cellspacing=5 width=96 height=111>");
            servletoutputstream.println("<tr><td><a href='Serv_Show_Folder?foldname=" + folder + "'>'" + folder + "'</a></td></tr></table></div>");
            int i = 0;
            for(ResultSet resultset = st.executeQuery("select mailid from newcompose where mailto='" + uname + "' and folder='inbox'"); resultset.next();)
                i++;

            servletoutputstream.println("<b>Total Messages:</b> " + i + "<br><br>");
            servletoutputstream.println("<br><a href=www.monsterindia.com target=new><img src=jobsearch1.gif border=0></a><br><br>");
            servletoutputstream.println("<form name=f action='Serv_MoveFolder'>");
            servletoutputstream.println("<input type=button name=delete value='Delete' onClick='d()' style=\"width:100\">");
            servletoutputstream.println("<input type=reset name=deselect value='DeSelect' style=\"width:100\">");
            servletoutputstream.println("&nbsp;&nbsp;&nbsp;&nbsp;<input type=submit name=move value='Move To' style=\"width:100\">");
            ResultSet resultset1 = st.executeQuery("select folder from folders where uname='" + uname + "'");
            servletoutputstream.println("<select name=se style=\"width:150\">");
            for(; resultset1.next(); servletoutputstream.println("<option value=" + f1 + ">" + f1 + "</option>"))
                f1 = resultset1.getString(1);

            servletoutputstream.println("</select><br>");
            servletoutputstream.println("<pre>                              <font face=verdana color=red>select a folder then go for MoveTo</font></pre>");
            resultset1.close();
            st.close();
            servletoutputstream.println("<br><table border=2 cellpadding=4 cellspacing=5 width=80%><TR bgcolor=#00FFFF><th><font color=red>Status</th><TH><font color=red>From</TH><TH><font color=red>Subject</Th><TH><font color=red>Date</TH></TR>");
            st1 = con.createStatement();
            rs1 = st1.executeQuery("select mailid,mailfrom,subject,maildate,mailst from newcompose where mailto='" + uname + "'");
            int j = 0;
            while(rs1.next()) 
            {
                j++;
                m = rs1.getInt(1);
                from = rs1.getString(2);
                sub = rs1.getString(3);
                date1 = rs1.getString(4);
                sta = rs1.getInt(5);
                if(sub == null)
                    sub = "[NONE]";
                if(sta == 1)
                {
                    servletoutputstream.println("<tr><td><center><img src='button.gif' height=20 width=25 ></td><td>");
                    servletoutputstream.println("<input type=checkbox name=b" + j + " value=" + m + "></td><td><a href='Serv_SeeMessage?id=" + m + "'>" + from + "</td></a><td>" + sub + "</td><td>" + date1 + "</td></tr>");
                } else
                {
                    servletoutputstream.println("<tr><td width=6%><input type=checkbox name=b" + j + " value=" + m + "></td><td width=24%><a href='Serv_SeeMessage?id=" + m + "'>" + from + "</td></a><td width=23%>" + sub + "</td><td width=47%>" + date1 + "</td></tr>");
                }
            }
            servletoutputstream.println("<script language=javascript>");
            servletoutputstream.println("function chkit(){");
            for(int k = 1; k <= j; k++)
                servletoutputstream.println("document.f.b" + k + ".checked=document.f.chkall.checked;");

            servletoutputstream.println("}</script>");
            servletoutputstream.println("<input type=hidden name=hid  value=" + j + ">");
            servletoutputstream.println("</form>");
            servletoutputstream.println("</table></body></html>");
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
    String newfol;
    String f1;
    String uname;
    String sub;
    String from;
    String date1;
    String folder;
    int sta;
    int m;
}
