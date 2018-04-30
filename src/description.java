// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   description.java

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class description extends HttpServlet
{

    public description()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        PrintWriter printwriter = httpservletresponse.getWriter();
        httpservletresponse.setContentType("Text/Html");
        HttpSession httpsession = httpservletrequest.getSession(true);
        String s = httpsession.getValue("name").toString();
        printwriter.println("<html>");
        printwriter.println("<body bgcolor='#00FA9A' text=blue width='70%' align='center'>");
        printwriter.println("<br>");
        printwriter.println("<center><h1><font color=brown> Hi " + s + "<h2><font color=blue>");
        printwriter.println("<font color=red>GoTo <a href='Serv_Inbox'> Inbox</a>");
        printwriter.println("<h2><b><i></i></b></h2><br><br><br><br>");
        printwriter.println("<img src='Images/MOVED.GIF' border=0>");
        printwriter.println("</body></html>");
    }
}
