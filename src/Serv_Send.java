// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Serv_Send.java

import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.util.Date;
import java.util.StringTokenizer;
import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;

import connect.ConnectionProvider;

public class Serv_Send extends HttpServlet
{

    public Serv_Send()
    {
        stmt = null;
        st1 = null;
        st2 = null;
        st3 = null;
        con = null;
        rs = null;
        rs1 = null;
        scon = null;
        sos = null;
        mfrom = null;
        flag = true;
        str1 = null;
        mdate = "";
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        try
        {
          //  Class.forName("oracle.jdbc.driver.OracleDriver");
           System.out.println("Driver Loaded");
          //  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "ojasvi");

	con=ConnectionProvider.getConnection();


            System.out.println("Connection created");
            stmt = con.createStatement();
            scon = getServletContext();
            HttpSession httpsession = httpservletrequest.getSession(true);
            mfrom = httpsession.getValue("name").toString();
            ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
            
            String path=scon.getRealPath("attachment");
            
            MultipartRequest mpr=new MultipartRequest(httpservletrequest,path,500*1024*1024);
           
			System.out.println("File uploaded successffully");

			String fname=mpr.getOriginalFileName("file");

			path =path+"\\"+fname;

			System.out.println("Attached file "+path);
			
			httpsession.setAttribute("attach",fname);

			System.out.println("attached file setted into the session");
        
            String s = mpr.getParameter("to");
            String s1 = mpr.getParameter("subject");
            String s2 = mpr.getParameter("cc");
            String s3 = mpr.getParameter("bcc");
            String s4 = mpr.getParameter("s1");
            String s5 = mpr.getParameter("maildata");
            StringTokenizer stringtokenizer = new StringTokenizer(s, ",");
            
            mdate = String.valueOf(new Date());
            while(stringtokenizer.hasMoreTokens()) 
            {
                str1 = stringtokenizer.nextToken();
                st1 = con.createStatement();
                rs1 = st1.executeQuery("select * from signupdetails where uname='" + str1 + "'");
                if(rs1.next())
                {
                    bool = true;
                } else
                {
                    bool = false;
                    servletoutputstream.println("<html><head><script>{alert('Invalid Mail-to address - He is an unregistered user');window.history.go(-1);}</script></head></html>");
                }
                rs1.close();
                st1.close();
                if(bool && flag)
                {
                    rs = stmt.executeQuery("Select max(mailid) from newcompose");
                    rs.next();
                    if(rs == null)
                    {
                        i = 1;
                    } else
                    {
                        i = rs.getInt(1);
                        i++;
                    }
                    rs.close();

		session= httpservletrequest.getSession(false);

		mch=(String)session.getAttribute("attach");

		System.out.println("name of the file into serv_send>>>>>>>>>>>>>>>>>"+mch);




                    st2 = con.createStatement();
                    System.out.println("St2 successful");
                    int j = st2.executeUpdate("insert into newcompose values(" + i + ",'" + mfrom + "','" + str1 + "','" + s1 + "','" + s2 + "','" + s3 + "','"+mch+"','" + s5 + "','inbox','" + mdate + "',"+ml+")");
                    if(j > 0)
                        servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue><font color=blue><h3><i>Message has been sent to " + str1 + " </i></h3></font>");
                    st2.close();
                }
            }
            str1 = "";
            i++;
            st3 = con.createStatement();
            System.out.println("St3 successful");
            int k = st3.executeUpdate("insert into newcompose values(" + i + ",'" + mfrom + "','" + s2 + "','" + s1 + "','','" + s3 + "','"+mch+"','" + s5 + "','inbox','" + mdate + "',"+ml+")");
            if(k > 0)
                servletoutputstream.println("<html><body bgcolor=white background='INDTEXTB.JPG' text=blue><font color=blue><h3><i>Message has been sent to " + s2 + " </i></h3></font>");
            st3.close();
            servletoutputstream.println("<form action=Serv_NewAddress><center>");
            servletoutputstream.println("<h3><a href=Serv_Compose>Compose</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=Serv_Inbox>Goto Inbox</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=Serv_NewAddress>Add Address</a>");
            servletoutputstream.println("</form></body></html>");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
int ml=0;
		String mch=null;

	HttpSession session;
    Statement stmt;
    Statement st1;
    Statement st2;
    Statement st3;
    Connection con;
    ResultSet rs;
    ResultSet rs1;
    int i;
    ServletContext scon;
    ServletOutputStream sos;
    boolean bool;
    String mfrom;
    boolean flag;
    String str1;
    String mdate;
}
