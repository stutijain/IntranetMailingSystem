// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_Logout.java

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class Serv_Logout extends HttpServlet
{

    public Serv_Logout()
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            name = httpsession.getValue("name").toString();
            httpsession.removeValue("name");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
        servletoutputstream.println("<html><title>Welcome To Intranet Mail System</title>");
        servletoutputstream.println("<body bgcolor='Ivory' text=blue><center>");
        
        
        servletoutputstream.println("<table background='INDTEXTB.JPG' width='70%' height='100%'>");
        servletoutputstream.println("<tr><td background='Images/IMS.JPG' width='100%' height='80'></td></td>");
        servletoutputstream.println("<tr><td><center><img src='Images/THANKS.GIF'></center></td><tr>");
        servletoutputstream.println("<tr><td align='center'><h2><i>'" + name + "'</i></h2>");
        servletoutputstream.println("<h2><i>For Visiting Intranet Mailing System</i></h2>");
        servletoutputstream.println("<a href='loginsc.html' target=_parent><h3>Login to Intranet Mail System</h3></a></td></tr>");
        servletoutputstream.println("<tr><td align='center'><img src='Images/logout.gif' width='240' height='160'></td></tr>");
        servletoutputstream.println("</table>");
        
        servletoutputstream.println("</body></html>");
    }

    ServletContext scon;
    String name;
}
