import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.oreilly.servlet.*;

public class Serv_Att extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response)
	{
		PrintWriter out=null;

		try
		{

			out=response.getWriter();

			String name=getServletContext().getRealPath("attachment");
			out.println("name "+name);

			MultipartRequest mpr=new MultipartRequest(request,name,500*1024*1024);

			out.println("File uploaded successffully");

			String fname=mpr.getOriginalFileName("fname");

			name =name+"\\"+fname;

			System.out.println("Attached file "+name);

		HttpSession s=request.getSession(false);

			s.setAttribute("attach",fname);

			System.out.println("attached file setted into the session");

			

			

		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
}