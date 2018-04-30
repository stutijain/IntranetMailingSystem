
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*; 
public class Serv_Download extends HttpServlet
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
{


OutputStream out=response.getOutputStream();
String downPath=this.getServletContext().getRealPath("attachment");

String name=request.getParameter("from");

response.setHeader("content-disposition","attachment;filename='"+name+"'");
response.setContentType("aplication/octet-stream");

downPath=downPath+"\\"+name;

System.out.println("path from where file to be downloaded..........."+downPath);


FileInputStream fin=new FileInputStream(downPath);
byte b[]=new byte[fin.available()];
fin.read(b);
out.write(b);
out.close();
fin.close();
}
}

